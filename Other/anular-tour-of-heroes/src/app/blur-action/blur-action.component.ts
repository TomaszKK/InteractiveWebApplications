import { Component } from '@angular/core';

@Component({
  selector: 'app-blur-action',
  templateUrl: './blur-action.component.html',
  styleUrls: ['./blur-action.component.css']
})
export class BlurActionComponent {
  rectColor = 'red';
  originalColor = 'red';

  changeColor() {
    if (this.rectColor === 'red') {
      this.rectColor = 'blue';
    } else {
      this.rectColor = 'red';
    }
  }

  resetColor() {
    this.rectColor = this.originalColor;
  }

}
