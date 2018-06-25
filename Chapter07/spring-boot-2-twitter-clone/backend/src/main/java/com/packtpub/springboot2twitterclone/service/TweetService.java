package com.packtpub.springboot2twitterclone.service;

import com.packtpub.springboot2twitterclone.model.Tweet;
import com.packtpub.springboot2twitterclone.repo.TweetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@Service
@Transactional(readOnly = true)
public class TweetService {

    private final TweetRepository tweetRepository;
    private final Scheduler dbScheduler;

    public TweetService(TweetRepository tweetRepository, Scheduler dbScheduler) {
        this.tweetRepository = tweetRepository;
        this.dbScheduler = dbScheduler;
    }

    @Transactional(rollbackFor = Exception.class)
    public Mono<Tweet> save(Tweet tweet) {
        return Mono.fromCallable(() -> tweetRepository.save(tweet)).publishOn(dbScheduler);
    }

    public Flux<Tweet> getTweets() {
        return Flux.fromIterable(tweetRepository.findAll()).publishOn(dbScheduler);
    }

    public Flux<Tweet> getRelevantTweets(String screenName) {
        return Flux.fromIterable(tweetRepository.findByTweetUser_ScreenNameOrContentContains(screenName, "@"+screenName)).publishOn(dbScheduler);
    }
}
