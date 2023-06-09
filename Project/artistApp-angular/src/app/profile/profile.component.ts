import {Component, OnInit} from '@angular/core';
import {ArtistModel} from "../artist/artist.model";
import {PoemModel} from "../poem/poem.model";
import {UserService} from "../servicesSecurity/user.service";
import {TokenStorageService} from "../auth/token-storage.service";
import {ArtistService} from "../artist/artist.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit{
  artist?: ArtistModel;
  artistList?: ArtistModel[];
  info: any;
  showUpdateForm = false;

  private roles?: string[];
  authority?: string;

  ngOnInit(): void {
    this.findArtist(this.token.getUsername());
    this.info = {
      token: this.token.getToken(),
      username: this.token.getUsername(),
      authorities: this.token.getAuthorities()
    };
    if (this.token.getToken()) {
      console.log(this.token.getToken());
      this.roles = this.token.getAuthorities();
      this.roles.every(role => {
        if (role === 'ROLE_ADMIN') {
          this.authority = 'admin';
          return false;
        }
        if(role === 'ROLE_ARTIST'){
          this.authority = 'artist';
          return false;
        }
        this.authority = 'visitor';
        return true;
      });
    }
  }
  constructor(
    private ArtistService: ArtistService,
    private userService: UserService,
    private token: TokenStorageService
  ) { }

  findArtist(username: String): void {
    this.ArtistService.getCurrentArtist(username).subscribe(
      (result: ArtistModel) => {
        this.artist = result;
      },
      (error) => {
        console.error('Error retrieving artist:', error);
      }
    );
  }

  updateProfileDetails(): void {
    // Perform any necessary actions before showing the update form

    // Set the flag to true to show the update form
    this.showUpdateForm = true;
  }

  patchArtist(
    name: string,
    secondName: string,
    bio: string,
    mediaLinks: string,
    location: string,
    type: string,
    age: number,
    username: string
  ): void {
    const updatedArtist: ArtistModel = {
      name: name.trim() || undefined,
      secondName: secondName.trim() || undefined,
      bio: bio.trim() || undefined,
      mediaLinks: mediaLinks.trim() || undefined,
      location: location.trim() || undefined,
      type: type.trim() || undefined,
      age: age || undefined,
    };

    console.log(username);
    if (username != undefined) {
      this.ArtistService.patchArtist(updatedArtist, username).subscribe({
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
          window.location.reload(); // Reload the page
          this.showUpdateForm = false;
        },
      });
    }
  }


}
