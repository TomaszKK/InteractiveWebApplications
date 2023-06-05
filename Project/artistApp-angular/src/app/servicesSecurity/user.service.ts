import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {ArtistModel} from "../artist/artist.model";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private artistUrl = 'http://localhost:8080/artist';
  private visitorUrl = 'http://localhost:8080/visitor';
  private adminUrl = 'http://localhost:8080/admin';

  constructor(private http: HttpClient) { }

  getArtistPage(): Observable<any>{
    return this.http.get(this.artistUrl, { responseType: 'text' });
  }

  getVisitorPage(): Observable<any>{
    return this.http.get(this.visitorUrl, { responseType: 'text' });
  }

  getAdminPage(): Observable<any>{
    return this.http.get(this.adminUrl, { responseType: 'text' });
  }

  getLoggedInArtistId(): Observable<number> {
    return this.http.get<any>('http://localhost:8080/artist/id')
      .pipe(
        map(response => response.id)
      );
  }

  getLoggedInArtist(): Observable<ArtistModel> {
    return this.http.get<ArtistModel>('http://localhost:8080/artist');
  }
}
