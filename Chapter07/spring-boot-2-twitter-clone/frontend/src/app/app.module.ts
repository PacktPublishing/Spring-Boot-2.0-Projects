import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { TweetsService } from './shared/tweets/tweets.service';
import { UsersService } from './shared/users/users.service';
import { AuthService } from './auth.service';
import { HttpClientModule } from '@angular/common/http';
import { HttpModule } from '@angular/http';
import { MatButtonModule, MatCardModule, MatInputModule, MatListModule, MatToolbarModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppComponent } from './app.component';
import { TweetsListComponent } from './tweets-list/tweets-list.component';
import { TweetsAddComponent } from './tweets-add/tweets-add.component';
import { LoginComponent } from './login.component';

import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { UserProfileComponent } from './user-profile/user-profile.component';

const appRoutes: Routes = [
  { path: '', redirectTo: '/tweets-list', pathMatch: 'full' },
  {
    path: 'tweets-list',
    component: TweetsListComponent
  },
  {
    path: 'tweets-add',
    component: TweetsAddComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'profile/:screenName',
    component: UserProfileComponent
  }
];


@NgModule({
  declarations: [
    AppComponent,
    TweetsListComponent,
    TweetsAddComponent,
    LoginComponent,
    UserProfileComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    HttpModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatListModule,
    MatToolbarModule,
    FormsModule,
    RouterModule.forRoot(appRoutes)
  ],
  providers: [TweetsService, UsersService, AuthService],
  bootstrap: [AppComponent]
})
export class AppModule { }
