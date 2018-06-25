import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import 'rxjs/add/operator/map';
import { AuthService } from '../../auth.service';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class UsersService {

  public API = '//localhost:8080';
  public USERS_API = this.API + '/users';

  constructor(public http: HttpClient, private authService: AuthService) {
  }

  getByScreenName(screenName): Observable<any> {
    const apiLink = this.USERS_API + '/' + screenName;
    let headers = new HttpHeaders().set('Authorization', 'Bearer '+this.authService.getToken());
    return this.http.get(apiLink, {headers: headers});
  }

  follow(userId): Observable<any> {
    const apiLink = this.USERS_API + '/' + userId + '/follow';
    let headers = new HttpHeaders().set('Authorization', 'Bearer '+this.authService.getToken());
    return this.http.put(apiLink, {}, {headers: headers});
  }
}
