import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {BehaviorSubject, Observable} from "rxjs";
import {VisitorModel} from "./visitor.model";
import {PoemModel} from "../poem/poem.model";

const httpPtions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
}

@Injectable({
  providedIn: 'root'
})

export class VisitorService {
  private visitorUrl = 'http://localhost:8080/visitor';

  constructor(private http: HttpClient) { }

  getVisitors():Observable<VisitorModel[]> {
    return this.http.get<VisitorModel[]>(this.visitorUrl);
  }

  getCurrentVisitor():Observable<VisitorModel> {
    return this.http.get<VisitorModel>(`${this.visitorUrl}/currentVisitor`);
  }


  patchVisitor(visitor: VisitorModel):Observable<VisitorModel> {
    return this.http.patch<VisitorModel>(`${this.visitorUrl}/currentVisitor`, visitor, httpPtions);
  }

  likePoem(poemId: number):Observable<VisitorModel> {
    return this.http.patch<VisitorModel>(`${this.visitorUrl}/likePoem/${poemId}`, httpPtions);
  }

  getLikedPoems():Observable<PoemModel[]> {
    return this.http.get<PoemModel[]>(`${this.visitorUrl}/likedPoem`);
  }

  unlikePoem(poemId: number):Observable<VisitorModel> {
    return this.http.patch<VisitorModel>(`${this.visitorUrl}/unlikePoem/${poemId}`, httpPtions);
  }

  public totalItems: BehaviorSubject<number> = new BehaviorSubject<number>(0);
}
