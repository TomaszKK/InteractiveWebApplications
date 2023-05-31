import {Component, OnInit} from '@angular/core';
import {PoemModel} from "./poem.model";
import {PoemService} from "./poem.service";

@Component({
  selector: 'app-poem',
  templateUrl: './poem.component.html',
  styleUrls: ['./poem.component.css']
})
export class PoemComponent implements OnInit{
  poem?: PoemModel;
  poemsList?: PoemModel[];

  ngOnInit(): void {
    this.getPoems();
  }

  constructor(private PoemService: PoemService) { }

  getPoems(): void {
    this.PoemService.getPoems()
      .subscribe(poems => this.poemsList = poems);
  }

}
