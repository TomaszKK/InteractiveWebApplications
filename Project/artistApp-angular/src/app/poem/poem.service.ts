import { Injectable } from '@angular/core';
import {PoemModel} from "./poem.model";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {BehaviorSubject, catchError, Observable, tap} from "rxjs";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})

export class PoemService {
  private poemUrl = 'http://localhost:8080/poem';
  constructor(private http: HttpClient) { }

  getPoems(): Observable<PoemModel[]> {
    return this.http.get<PoemModel[]>(this.poemUrl);
  }

  getArtistPoems(): Observable<PoemModel[]> {
    return this.http.get<PoemModel[]>(`${this.poemUrl}/getArtistPoems`).pipe(
      catchError((error) => {
        console.error('Error getting artist poems:', error);
        throw error; // Rethrow the error to propagate it to the caller
      })
    );
  }

  addPoem(poem: PoemModel): Observable<PoemModel> {
    return this.http.post<PoemModel>(`${this.poemUrl}/addPoem`, poem, httpOptions).pipe(
      catchError((error) => {
        console.error('Error adding poem:', error);
        throw error; // Rethrow the error to propagate it to the caller
      })
    );
  }

  patchPoem(poem: PoemModel, id: number | undefined): Observable<PoemModel> {
    console.log(poem);
    return this.http.patch<PoemModel>(`${this.poemUrl}/${id}`, poem, httpOptions).pipe(
      tap((poemPatched: PoemModel) => console.log(`patched poem id=${poemPatched.id}`)),
      catchError((error) => {
        console.error('Error patching poem:', error);
        throw error; // Rethrow the error to propagate it to the caller
      })
    );
  }

  totalItems = new BehaviorSubject<number>(0);
}
