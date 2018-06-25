import { Component, OnInit } from '@angular/core';
import { TweetsService } from '../shared/tweets/tweets.service';
import { AuthService } from '../auth.service'

@Component({
  selector: 'app-tweets-list',
  templateUrl: './tweets-list.component.html',
  styleUrls: ['./tweets-list.component.css']
})
export class TweetsListComponent implements OnInit {

  tweets: Array<any>;

  constructor(private tweetsService: TweetsService, private authService: AuthService) { }

  ngOnInit() {
    this.authService.checkCredentials();
    this.tweetsService.getAll().subscribe(data => {
      this.tweets = data;
    });
  }

}
