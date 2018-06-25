import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { ActivatedRoute, Router } from '@angular/router';
import { TweetsService } from '../shared/tweets/tweets.service';
import { NgForm } from '@angular/forms';
import { AuthService } from '../auth.service'

@Component({
  selector: 'app-tweets-add',
  templateUrl: './tweets-add.component.html',
  styleUrls: ['./tweets-add.component.css']
})
export class TweetsAddComponent implements OnInit {

  tweet: any = {};

  constructor(private route: ActivatedRoute,
              private router: Router,
              private tweetsService: TweetsService,
              private authService: AuthService) { }

  ngOnInit() {
  }

  gotoList() {
    this.authService.checkCredentials();
    this.router.navigate(['/tweets-list']);
  }

  save(form: NgForm) {
    this.authService.checkCredentials();
    this.tweetsService.save(form).subscribe(result => {
      this.gotoList();
    }, error => console.error(error));
  }

}
