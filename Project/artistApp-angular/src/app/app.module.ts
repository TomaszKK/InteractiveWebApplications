import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
<<<<<<< Updated upstream
import { ArtistComponent } from './artist/artist.component';
import { PoemComponent } from './poem/poem.component';
=======
import { ArtistsListComponent } from './artists-list/artists-list.component';
import { ArtistComponent } from './artist/artist.component';
import { PoemComponent } from './poem/poem.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { SearchComponent } from './search/search.component';
import { VisitorComponent } from './visitor/visitor.component';
>>>>>>> Stashed changes

@NgModule({
  declarations: [
    AppComponent,
<<<<<<< Updated upstream
    ArtistComponent,
    PoemComponent
=======
    ArtistsListComponent,
    ArtistComponent,
    PoemComponent,
    DashboardComponent,
    SearchComponent,
    VisitorComponent
>>>>>>> Stashed changes
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
