import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { AuthService } from '../../auth.service';

@Injectable()
export class TweetsService {
  public API = '//localhost:8080';
  public TWEETS_API = this.API + '/tweets';
  private authService;

  constructor(private http: HttpClient, authService: AuthService) {
    this.authService = authService;
  }

  getAll(): Observable<any> {
    let headers = new HttpHeaders().set('Authorization', 'Bearer '+this.authService.getToken());
    return this.http.get(this.TWEETS_API, {headers: headers});
  }

  save(tweet: any): Observable<any> {
    let headers = new HttpHeaders().set('Authorization', 'Bearer '+this.authService.getToken());
    let result: Observable<Object>;
    result = this.http.post(this.TWEETS_API, tweet, {headers: headers});
    return result;
  }

}
