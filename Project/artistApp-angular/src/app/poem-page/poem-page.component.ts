import {Component, OnInit} from '@angular/core';
import {PoemModel} from "../poem/poem.model";
import {ActivatedRoute} from "@angular/router";
import {PoemService} from "../poem/poem.service";

@Component({
  selector: 'app-poem-page',
  templateUrl: './poem-page.component.html',
  styleUrls: ['./poem-page.component.css']
})
export class PoemPageComponent implements OnInit{
  poem: PoemModel | undefined;

  constructor(private route: ActivatedRoute, private poemService: PoemService) { }

  ngOnInit(): void {
    this.getPoem();
  }

  getPoem(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.poemService.get(Number(id)).subscribe(
        poem => {
          this.poem = poem;
        },
        error => {
          console.log('Failed to get poem.', error);
        }
      );
    }
  }
  ratePoem(star: number): void {
    if(this.poem == null) return;
    if (this.poem.visitorRating !== undefined) {
      // Visitor has already rated, update the rating instead of adding a new one
      this.updatePoemRating(star);
    } else {
      // Visitor is rating for the first time, add a new rating
      this.addPoemRating(star);
    }
  }


  private addPoemRating(star: number): void {
    // Update the poem's rating and number of ratings
    if(this.poem == null) return;
    this.poem.rating = ((this.poem.rating * this.poem.numberOfRatings) + star) / (this.poem.numberOfRatings + 1);
    this.poem.numberOfRatings++;

    // Set the visitor's rating for the poem
    this.poem.visitorRating = star;

    this.poemService.patchPoem(this.poem, this.poem.id).subscribe(
      response => {
        console.log('Poem rated successfully.');
        window.location.reload();
      },
      error => {
        console.error('Failed to rate the poem.');
      }
    );
  }

  private updatePoemRating(star: number): void {
    if(this.poem == null) return;
    if(this.poem.visitorRating === undefined) return;
    this.poem.rating = ((this.poem.rating * this.poem.numberOfRatings) - this.poem.visitorRating + star) / this.poem.numberOfRatings;

    // Set the visitor's updated rating for the poem
    this.poem.visitorRating = star;
    this.poemService.patchPoem(this.poem, this.poem.id).subscribe(
      response => {
        console.log('Poem rated successfully.');
        window.location.reload();
      },
      error => {
        console.error('Failed to rate the poem.');
      }
    );
  }

  isRatingDisabled(): boolean {
    if(this.poem == null) return true;
    return this.poem?.visitorRating !== undefined;
  }
}
