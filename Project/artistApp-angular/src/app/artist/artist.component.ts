import {Component, OnInit} from '@angular/core';
import {Artist} from "./artist";
import {ArtistService} from "./artist.service";

@Component({
  selector: 'app-artist',
  templateUrl: './artist.component.html',
  styleUrls: ['./artist.component.css']
})
export class ArtistComponent implements OnInit{

  artistList?: Artist[];
  artist?: Artist;

  constructor(private artistService: ArtistService) {}

  ngOnInit() {
    this.getArtists();
  }


  private getArtists() {
    this.artistService.getArtists()
      .subscribe(artistList => this.artistList = artistList);
  }
}
