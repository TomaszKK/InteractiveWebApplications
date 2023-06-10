import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {BehaviorSubject, catchError, Observable, of, tap} from "rxjs";
import {ArtistModel} from "./artist.model";

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class ArtistService {
  private artistsUrl = 'http://localhost:8080/artist';
  constructor(private http: HttpClient) { }

  getCurrentArtist(): Observable<ArtistModel> {
    return this.http.get<ArtistModel>(`${this.artistsUrl}/currentArtist`);
  }

  getArtists(): Observable<ArtistModel[]> {
    return this.http.get<ArtistModel[]>(this.artistsUrl)
      .pipe(
        tap(artists => console.log('fetched artists')),
        catchError(this.handleError('getArtists', []))
      );
  }

  getArtist(id: number): Observable<ArtistModel> {
    const url = `${this.artistsUrl}/${id}`;
    return this.http.get<ArtistModel>(url).pipe(
      tap(_ => console.log(`fetched artist id=${id}`)),
      catchError(this.handleError<ArtistModel>(`getArtist id=${id}`))
    )
  }

  addArtist(artist: ArtistModel): Observable<ArtistModel> {
    return this.http.post<ArtistModel>(this.artistsUrl, artist, httpOptions).pipe(
      tap((artistAdded: ArtistModel) => console.log(`added artist id=${artistAdded.id}`)),
      catchError(this.handleError<ArtistModel>('addArtist'))
    );
  }

  deleteArtist(artist: ArtistModel | number): Observable<ArtistModel> {
    return this.http.delete<ArtistModel>(this.artistsUrl, httpOptions).pipe(
      tap(_ => console.log(`deleted artist id=${artist}`)),
      catchError(this.handleError<ArtistModel>('deleteArtist'))
    );
  }

  updateArtist(artist: ArtistModel, id:number): Observable<ArtistModel> {
    return this.http.put<ArtistModel>(this.artistsUrl, artist, httpOptions).pipe(
      tap((artistUpdated: ArtistModel) => console.log(`updated artist id=${artistUpdated.id}`)),
      catchError(this.handleError<ArtistModel>('updateArtist'))
    );
  }

  updateArtists(artists: ArtistModel[]): Observable<ArtistModel[]> {
    return this.http.put<ArtistModel[]>(this.artistsUrl, artists, httpOptions).pipe(
      tap((artistsUpdated: ArtistModel[]) => console.log(`updated artists`)),
      catchError(this.handleError<ArtistModel[]>('updateArtists'))
    );
  }


  patchArtist(artist: ArtistModel, username:string): Observable<ArtistModel> {
    return this.http.patch<ArtistModel>(`${this.artistsUrl}/${username}`, artist, httpOptions).pipe(
      tap((artistUpdated: ArtistModel) => console.log(`patched artist -> ${username}`)),
      catchError(this.handleError<ArtistModel>('patchArtist'))
    );
  }


  putAllArtists(artists: ArtistModel[]): Observable<ArtistModel[]> {
    return this.http.put<ArtistModel[]>(this.artistsUrl, artists, httpOptions).pipe(
      tap((artistsUpdated: ArtistModel[]) => console.log(`put all artists`)),
      catchError(this.handleError<ArtistModel[]>('putAllArtists'))
    );
  }

  deleteAllArtists(): Observable<ArtistModel[]> {
    return this.http.delete<ArtistModel[]>(this.artistsUrl, httpOptions).pipe(
      tap((artistsUpdated: ArtistModel[]) => console.log(`deleted all artists`)),
      catchError(this.handleError<ArtistModel[]>('deleteAllArtists'))
    );
  }



  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }


  private log(message: string) {
    console.log('ArtistService: ' + message);
  }


  public totalItems: BehaviorSubject<number> = new BehaviorSubject<number>(0);

}
