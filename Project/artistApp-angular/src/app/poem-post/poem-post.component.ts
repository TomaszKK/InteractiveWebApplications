import {Component, Input, OnInit} from '@angular/core';
import {PoemModel} from "../poem/poem.model";
import {TokenStorageService} from "../auth/token-storage.service";
import {VisitorModel} from "../visitor/visitor.model";
import {VisitorService} from "../visitor/visitor.service";

@Component({
  selector: 'app-poem-post',
  templateUrl: './poem-post.component.html',
  styleUrls: ['./poem-post.component.css']
})
export class PoemPostComponent implements OnInit{
  @Input() poem?: PoemModel;
  @Input() likedPoems: PoemModel[] = [];
  authority?: string;
  private roles?: string[];

  constructor(private tokenStorage: TokenStorageService, private visitorService: VisitorService) { }

  ngOnInit() {
    if (this.tokenStorage.getToken()) {
      console.log(this.tokenStorage.getToken());
      this.roles = this.tokenStorage.getAuthorities();
      this.roles.every(role => {
        if (role === 'ROLE_ADMIN') {
          this.authority = 'admin';
          return false;
        }
        if(role === 'ROLE_ARTIST'){
          this.authority = 'artist';
          return false;
        }
        if(role === 'ROLE_VISITOR'){
          this.authority = 'visitor';
          return false;
        }
        return true;
      });
      this.getLikedPoems();
    }
  }

  likePoem(id: number | undefined): void {
    if (id != null) {
      this.visitorService.likePoem(id).subscribe(
        response => {
          console.log('Poem liked successfully.');

          window.location.reload()
        },
        error => {
          console.error('Failed to like the poem.');
        }
      );
    }
  }

  getLikedPoems(): void {
    this.visitorService.getLikedPoems().subscribe(
      response => {
        this.likedPoems = response;
      },
      error => {
        console.log('Failed to get liked poems.');
      }
    );
  }

  isPoemLiked(id: number | undefined): boolean {
    if (id != null) {
      return this.likedPoems?.some(poem => poem.id === id) ?? false;
    }
    return false;
  }
}
