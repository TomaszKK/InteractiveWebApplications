import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from "../auth/token-storage.service";
import {PoemService} from "../poem/poem.service";
import {PoemModel} from "../poem/poem.model";
import {PoemComponent} from "../poem/poem.component";
import {Genre} from "../poem/genre";
import {ArtistModel} from "../artist/artist.model";
import {ArtistService} from "../artist/artist.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  poemList?: PoemModel[];
  filteredPoemsList?: PoemModel[];
  searchText: string = '';
  selectedGenre: string = '';
  genres: Genre[] = Object.values(Genre);
  selectedArtist: string = '';
  artistSearchText: string = '';
  artistList?: ArtistModel[];

  info: any;

  constructor(private token: TokenStorageService, private poemService: PoemService, private artistService: ArtistService, private router: Router) { }

  ngOnInit() {
    this.getPoemList();
    this.loadArtistList();
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    };
    this.filterPoems();
  }

  goToPoemPage(poem: PoemModel): void {
    this.router.navigate(['/poem-page', poem.id]);
  }

  getPoemList() {
    this.poemService.getPoems().subscribe(data => {
      this.poemList = data;
      this.filterPoems();
    });
  }

  loadArtistList() {
    this.artistService.getArtists().subscribe(data => {
      this.artistList = data;
    });
  }

  searchPoems() {
    if (this.poemList) {
      // Sort poems by creation date in descending order
      this.poemList.sort((a, b) => {
        const dateA = new Date(a.creationDate).getTime();
        const dateB = new Date(b.creationDate).getTime();
        return dateB - dateA;
      });

      if (this.searchText.trim() === '') {
        // If the search text is empty, display the 10 newest poems
        this.filteredPoemsList = this.poemList.slice(0, 10);
      } else {
        // Filter poems based on similarity to the search text
        this.filteredPoemsList = this.poemList.filter(poem => {
          const searchTerm = this.searchText.toLowerCase();
          const poemTitle = poem.title.toLowerCase();
          // Check if the poem title contains the search term
          return poemTitle.includes(searchTerm) || poemTitle.startsWith(searchTerm);
        });
      }
    }
  }

  filterPoems() {
    this.filteredPoemsList = this.poemList?.filter(poem => {
      const titleMatches = poem.title.toLowerCase().includes(this.searchText.toLowerCase());
      const genreMatches = this.selectedGenre === '' || poem.genre === this.selectedGenre;
      const artistMatches = this.selectedArtist === '' || poem.artist === this.selectedArtist;
      return titleMatches && genreMatches && artistMatches;
    });
  }

  searchArtists() {
    if (this.artistList) {
      this.artistList = this.artistList.filter(artist => {
        const searchTerm = this.artistSearchText.toLowerCase();
        const artistName = (artist.name || '').toLowerCase();
        return artistName.includes(searchTerm) || artistName.startsWith(searchTerm);
      });
    }
  }
}

