import {Component, OnInit} from '@angular/core';
import {ArtistService} from "./artist.service";
import {ArtistModel} from "./artist.model";
import {PoemModel} from "../poem/poem.model";

@Component({
  selector: 'app-artist',
  templateUrl: './artist.component.html',
  styleUrls: ['./artist.component.css']
})
export class ArtistComponent implements OnInit{
  artist?: ArtistModel;
  artistList?: ArtistModel[];

  poemList?: PoemModel[];

  ngOnInit(): void {
    this.getArtists();
  }

  constructor(private ArtistService: ArtistService) {
  }

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

  patch(name: string, secondName: string, bio: string, mediaLinks: string, location: string, type: string, age: number, chosenToPatchArtist: ArtistModel): void {
    let id = chosenToPatchArtist.id;
    if (name == "") name = chosenToPatchArtist.name;
    if (secondName == "") secondName = chosenToPatchArtist.secondName;
    if (bio == "") bio = chosenToPatchArtist.bio;
    if (mediaLinks == "") mediaLinks = chosenToPatchArtist.mediaLinks;
    if (location == "") location = chosenToPatchArtist.location;
    name = name.trim();
    secondName = secondName.trim();
    bio = bio.trim();
    mediaLinks = mediaLinks.trim();
    location = location.trim();

    console.log(id);
    if (id != undefined) {
      this.ArtistService.patchArtist({
        name,
        secondName,
        bio,
        mediaLinks,
        location,
        type,
        age
      } as ArtistModel, id)
        .subscribe({
          next: (artist: ArtistModel) => {
            if (this.artistList != undefined) {
              let index = this.artistList?.indexOf(chosenToPatchArtist);
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


}
