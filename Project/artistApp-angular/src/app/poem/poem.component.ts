import {Component, OnInit} from '@angular/core';
import {PoemModel} from "./poem.model";
import {PoemService} from "./poem.service";
import {Genre} from "./genre";

@Component({
  selector: 'app-poem',
  templateUrl: './poem.component.html',
  styleUrls: ['./poem.component.css']
})
export class PoemComponent implements OnInit{
  poem?: PoemModel;
  poemsList?: PoemModel[];
  filteredPoemsList?: PoemModel[];

  genres: Genre[] = Object.values(Genre);
  selectedSortOption?: string;
  selectedGenreFilter?: string;
  selectedPoem: PoemModel | undefined = undefined;

  ngOnInit(): void {
    this.getArtistPoems(); // Fetch poems first
    this.selectedSortOption = 'creationDate';
    this.selectedGenreFilter = '';
  }
  constructor(private PoemService: PoemService) { }

  getPoems(): void {
    this.PoemService.getPoems()
      .subscribe(poems => this.poemsList = poems);
  }

  getArtistPoems(): void {
    this.PoemService.getArtistPoems().subscribe(poems => {
      this.poemsList = poems; // Set poemsList with fetched data
      this.sortPoems(); // Sort the poems
      this.filterPoems(); // Apply initial filter
    });
  }

  add(title: string, text: string, genre: string): void {
    let creationDate = new Date();
    this.PoemService.addPoem({ title, text, genre, creationDate } as PoemModel).subscribe(
      poem => {
        this.poemsList?.push(poem);
        if (this.poemsList != undefined) {
          this.PoemService.totalItems.next(this.poemsList.length);
          console.log(this.poemsList?.length);
        }
        window.location.reload();
        },
      error => {
        console.error('Error adding poem:', error);
      }
    );
  }

  sortPoems(): void {
    this.poemsList?.sort((a: PoemModel, b: PoemModel):any => {
      if (this.selectedSortOption === 'creationDate') {
        return new Date(b.creationDate).getTime() - new Date(a.creationDate).getTime();
      } else if (this.selectedSortOption === 'title') {
        return a.title.localeCompare(b.title);
      } else if(this.selectedSortOption === 'genre') {
        return a.genre.localeCompare(b.genre);
      }

    });
  }

  filterPoems(): void {
    if (this.selectedGenreFilter) {
      this.filteredPoemsList = this.poemsList?.filter(poem =>
        poem?.genre?.toLowerCase() === this.selectedGenreFilter?.toLowerCase()
      );
    } else {
      this.filteredPoemsList = this.poemsList; // Reset filter if no genre selected
    }
  }

  selectPoemForUpdate(poem: PoemModel) {
    this.selectedPoem = poem;
  }

  updatePoem(title: string, text: string, genre: string, updatedPoem: PoemModel): void {
    if (updatedPoem) {
      let id: number | undefined = updatedPoem.id;
      title = title.trim();
      text = text.trim();
      genre = genre.trim();
      this.PoemService.patchPoem({ title, text, genre } as PoemModel, id).subscribe({
        next: (poem: PoemModel) => {
          const index = this.poemsList?.findIndex((p) => p.id === poem.id);
          if (index !== undefined && index !== -1) {
            this.poemsList?.splice(index, 1, poem);
          }
          this.selectedPoem = undefined;
        },
        error: (error) => {
          console.error('Error updating poem:', error);
        },
      });
    }
  }

}
