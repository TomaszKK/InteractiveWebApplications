import {Component, OnInit} from '@angular/core';
import {PoemModel} from "../poem/poem.model";
import {VisitorService} from "../visitor/visitor.service";

@Component({
  selector: 'app-liked-poems',
  templateUrl: './liked-poems.component.html',
  styleUrls: ['./liked-poems.component.css']
})
export class LikedPoemsComponent implements OnInit{
  likedPoems: PoemModel[] = [];

  constructor(private visitorService: VisitorService) {}

  ngOnInit(): void {
    this.getLikedPoems();
  }

  getLikedPoems(): void {
    this.visitorService.getLikedPoems().subscribe(
      response => {
        this.likedPoems = response;
      } ,
        error => { console.log('Failed to get liked poems.');
      }
    );
  }

  unlikePoem(poemId: number | undefined): void {
    if(poemId == null) return;
    this.visitorService.unlikePoem(poemId).subscribe(
      response => {
        console.log('Poem unliked successfully.');
        // Remove the poem from the likedPoems array
        this.likedPoems = this.likedPoems.filter(poem => poem.id !== poemId);
        window.location.reload();
      },
      error => {
        console.error('Failed to unlike the poem.');
      }
    );
  }
}
