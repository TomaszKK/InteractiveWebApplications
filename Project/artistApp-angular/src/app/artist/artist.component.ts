import {Component, OnInit} from '@angular/core';
import {ArtistService} from "./artist.service";
import {ArtistModel} from "./artist.model";
import {PoemModel} from "../poem/poem.model";
import {UserService} from "../servicesSecurity/user.service";
import {Observable} from "rxjs";
import {TokenStorageService} from "../auth/token-storage.service";

@Component({
  selector: 'app-artist',
  templateUrl: './artist.component.html',
  styleUrls: ['./artist.component.css']
})
export class ArtistComponent implements OnInit{
  artist?: ArtistModel;
  artistList?: ArtistModel[];

  poemList?: PoemModel[];
  info: any;

  ngOnInit(): void {
    this.getArtists();
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    };
  }

  constructor(
    private ArtistService: ArtistService,
    private userService: UserService,
    private token: TokenStorageService
) { }

  getArtists(): void {
    this.ArtistService.getArtists()
      .subscribe(artistList => this.artistList = artistList);
  }

  add(name: string, secondName: string, bio: string, mediaLinks: string, location: string, type: string, age: number ): void {
    name = name.trim();
    secondName = secondName.trim();
    bio = bio.trim();
    mediaLinks = mediaLinks.trim();
    location = location.trim();
    type = type.trim();
    this.ArtistService.addArtist({name, secondName, bio, mediaLinks, location, type, age} as ArtistModel)
      .subscribe(artist => {
        this.artistList?.push(artist);
        // for automatic update of number of students in parent component
        if (this.artistList != undefined) {
          this.ArtistService.totalItems.next(this.artistList.length);
          console.log(this.artistList.length);
        }
      });
  }

  delete(artist: ArtistModel): void {
    this.artistList = this.artistList?.filter(c => c !== artist);
    this.ArtistService.deleteArtist(artist).subscribe(() => {
        // for automatic update of number of students in parent component
        if (this.artistList != undefined) {
          this.ArtistService.totalItems.next(this.artistList.length);
          console.log(this.artistList.length);
        }
      }
    );
  }

  deleteAll(): void {
    this.ArtistService.deleteAllArtists().subscribe(() => {
        // for automatic update of number of students in parent component
        if (this.artistList != undefined) {
          this.ArtistService.totalItems.next(this.artistList.length);
          console.log(this.artistList.length);
        }
      }
    );
  }

  update(name: string, secondName: string, bio: string, mediaLinks: string, location: string, type: string, age: number, chosenToUpdateArtist: ArtistModel): void {
    let id = chosenToUpdateArtist.id;
    name = name.trim();
    secondName = secondName.trim();
    bio = bio.trim();
    mediaLinks = mediaLinks.trim();
    location = location.trim();

    console.log(id);
    if (id != undefined) {
      this.ArtistService.updateArtist({
        name,
        secondName,
        bio,
        mediaLinks,
        location,
        type,
        age,
      } as ArtistModel, id)
        .subscribe({
          next: (artist: ArtistModel) => {
            if (this.artistList != undefined) {
              let index = this.artistList?.indexOf(chosenToUpdateArtist);
              this.artistList[index] = artist;
            }
          },
          error: () => {
          },
          complete: () => {
            if (this.artistList != undefined) {
              this.ArtistService.totalItems.next(this.artistList.length);
              console.log(this.artistList.length);
            }
          }
        });
    }
  }

  patch(
    name: string,
    secondName: string,
    bio: string,
    mediaLinks: string,
    location: string,
    type: string,
    age: number,
    username: string
  ): void {
    name = name.trim();
    secondName = secondName.trim();
    bio = bio.trim();
    mediaLinks = mediaLinks.trim();
    location = location.trim();
    console.log(username);
    if (username != undefined) {
      this.ArtistService.patchArtist({
        name,
        secondName,
        bio,
        mediaLinks,
        location,
        type,
        age,
      } as ArtistModel, username).subscribe({
        next: (artist: ArtistModel) => {
          if (this.artistList !== undefined) {
            const index = this.artistList.findIndex(a => a.id === artist.id);
            if (index !== -1) {
              this.artistList[index] = artist;
            }
          }
        },
        error: () => {},
        complete: () => {
          if (this.artistList !== undefined) {
            this.ArtistService.totalItems.next(this.artistList.length);
            console.log(this.artistList.length);
          }
        },
      });
    }
  }


}
