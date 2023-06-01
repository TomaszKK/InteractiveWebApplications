import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

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
}
