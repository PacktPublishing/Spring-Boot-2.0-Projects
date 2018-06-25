import { TestBed, inject } from '@angular/core/testing';

import { TweetsService } from './tweets.service';

describe('TweetsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TweetsService]
    });
  });

  it('should be created', inject([TweetsService], (service: TweetsService) => {
    expect(service).toBeTruthy();
  }));
});
