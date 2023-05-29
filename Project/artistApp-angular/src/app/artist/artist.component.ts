<<<<<<< Updated upstream
import {Component, OnInit} from '@angular/core';
import {Artist} from "./artist";
import {ArtistService} from "./artist.service";

=======
import { Component, OnInit } from '@angular/core';
import { Artist } from './artist.model';
import { ArtistService } from './artist.service';
>>>>>>> Stashed changes
@Component({
  selector: 'app-artist',
  templateUrl: './artist.component.html',
  styleUrls: ['./artist.component.css']
})
<<<<<<< Updated upstream
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
=======
export class ArtistComponent implements OnInit {

  ngOnInit() {

>>>>>>> Stashed changes
  }
}
