import { Injectable } from '@angular/core';
import {PoemModel} from "./poem.model";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})

export class PoemService {
  private poemsUrl = 'http://localhost:8080/poem';
  constructor(private http: HttpClient) { }

  getPoems(): Observable<PoemModel[]> {
    return this.http.get<PoemModel[]>(this.poemsUrl);
  }
}
