import { Component } from '@angular/core';

@Component({
  selector: 'app-double-click',
  templateUrl: './double-click.component.html',
  styleUrls: ['./double-click.component.css']
})
export class DoubleClickComponent {
  clickMessage = '';

  onDoubleClick() {
    const messages = [
      'Metallica',
      'Rammstein',
      'The Rolling Stones',
      'System of a Down'
    ];
    const randomIndex = Math.floor(Math.random() * messages.length);
    this.clickMessage = messages[randomIndex];
  }
}
