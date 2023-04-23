import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HeroDetailsComponent } from './hero-details/hero-details.component';
import { HeroesComponent } from './heroes/heroes.component';
import { MessagesComponent } from './messages/messages.component';

import { AppRoutingModule } from './app-routing.module';
import { EventsComponent } from './events/events.component';
import { ClickMeComponent } from './click-me/click-me.component';
import { DoubleClickComponent } from './double-click/double-click.component';
import { ChangeColorEnterComponent } from './change-color-enter/change-color-enter.component';
import { BlurActionComponent } from './blur-action/blur-action.component';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule
  ],
  declarations: [
    AppComponent,
    DashboardComponent,
    HeroesComponent,
    HeroDetailsComponent,
    MessagesComponent,
    EventsComponent,
    ClickMeComponent,
    DoubleClickComponent,
    ChangeColorEnterComponent,
    BlurActionComponent
  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
