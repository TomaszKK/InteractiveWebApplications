import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {VisitorModel} from "./visitor.model";

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

}
