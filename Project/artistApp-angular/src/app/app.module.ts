import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { ArtistComponent } from './artist/artist.component';
import { PoemComponent } from './poem/poem.component';
import { VisitorComponent } from './visitor/visitor.component';
import { AdminComponent } from './admin/admin.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import {FormsModule} from "@angular/forms";
import {RouterLink, RouterOutlet} from "@angular/router";


@NgModule({
  declarations: [
    AppComponent,
    ArtistComponent,
    PoemComponent,
    VisitorComponent,
    AdminComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterLink,
    RouterOutlet
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
