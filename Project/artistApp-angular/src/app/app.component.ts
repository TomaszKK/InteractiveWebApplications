import { Component } from '@angular/core';
import {TokenStorageService} from "./auth/token-storage.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Artist App';
  private roles?: string[];
  authority?: string;

  constructor(private tokenStorage: TokenStorageService) {  }

  ngOnInit() {
    console.log("init");
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
        this.authority = 'visitor';
        return true;
      });
    }
  }
}
