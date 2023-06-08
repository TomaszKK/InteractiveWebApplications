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
import {RouterLink, RouterModule, RouterOutlet, Routes} from "@angular/router";
import {httpInterceptorProviders} from "./auth/auth-interceptor";
import {HttpClientModule} from "@angular/common/http";
import {RoleGuard} from "./guard/role.guard";
import { LogoutComponent } from './logout/logout.component';
import {AuthGuard} from "./guard/auth.guard";

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'artist', component: ArtistComponent, canActivate: [AuthGuard], data: { roles: ['ROLE_ARTIST', 'ROLE_ADMIN'] },},
  { path: 'poem', component: PoemComponent, canActivate: [AuthGuard], data: { roles: ['ROLE_ADMIN'] },},
  { path: 'visitor', component: VisitorComponent, canActivate: [AuthGuard], data: { roles: ['ROLE_VISITOR', 'ROLE_ADMIN'] }, },
  { path: 'admin', component: AdminComponent },
  { path: 'auth/login', component: LoginComponent },
  { path: 'signup', component: RegisterComponent },
  { path: 'logout', component: LogoutComponent},
  { path: '', redirectTo: 'home', pathMatch: 'full' }
];

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
    LogoutComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterLink,
    HttpClientModule,
    RouterModule.forRoot(routes),
    RouterOutlet
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
