import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { ArtistComponent } from './artist/artist.component';
import { PoemComponent } from './poem/poem.component';
import { VisitorComponent } from './visitor/visitor.component';


@NgModule({
  declarations: [
    AppComponent,
    ArtistComponent,
    PoemComponent,
    VisitorComponent,
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
