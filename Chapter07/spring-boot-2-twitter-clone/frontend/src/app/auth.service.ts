import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import { Cookie } from 'ng2-cookies';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

@Injectable()
export class AuthService {
  constructor(
    private router: Router, private http: Http){}

  getAndSaveAccessToken(loginData){
    let params = new URLSearchParams();
    params.append('username',loginData.username);
    params.append('password',loginData.password);
    params.append('grant_type','password');

    let headers = new Headers({'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
                               'Authorization': 'Basic '+btoa("angularjsapp:angularjs123")});
    let options = new RequestOptions({ headers: headers });
     this.http.post('http://localhost:8080/oauth/token', params.toString(), options)
    .map(res => res.json())
    .subscribe(
      data => this.saveToken(data),
      err => console.log(err.body)
    );
  }


  saveToken(token){
    var expireDate = new Date().getTime() + (1000 * token.expires_in);
    Cookie.set("access_token", token.access_token, expireDate);
    this.router.navigate(['/']);
  }

  getToken() {
    return Cookie.get('access_token');
  }

  checkCredentials(){
    if (!Cookie.check('access_token')){
      this.router.navigate(['/login']);
    } else {
     let headers = new Headers({'Content-type': 'application/x-www-form-urlencoded; charset=utf-8',
                               'Authorization': 'Bearer '+Cookie.get('access_token')});
     let options = new RequestOptions({ headers: headers });
      this.http.get('http://localhost:8080/tweets', options)
          .map(res => res.json())
          .subscribe(
            data => console.log(data),
            err => {
              console.log(err);
              this.router.navigate(['/login']); }
          );
    }
  }

  logout() {
    Cookie.delete('access_token');
    this.router.navigate(['/login']);
  }
}
