import { Component } from '@angular/core';

@Component({
  selector: 'app-change-color-enter',
  templateUrl: './change-color-enter.component.html',
  styleUrls: ['./change-color-enter.component.css']
})
export class ChangeColorEnterComponent {
  rectColor = 'red';

  changeColor() {
    if (this.rectColor === 'red') {
      this.rectColor = 'blue';
    } else {
      this.rectColor = 'red';
    }
  }
}
