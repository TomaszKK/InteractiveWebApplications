import { Component } from '@angular/core';

@Component({
  selector: 'app-click-me',
  templateUrl: './click-me.component.html',
  styleUrls: ['./click-me.component.css']
})

export class ClickMeComponent {
  clickMessage = "You haven't clicked yet.";

  onMouseDown() {
    this.clickMessage = 'You are clicking me!';
  }

  onMouseUp() {
    this.clickMessage = "You haven't clicked yet.";
  }
}
