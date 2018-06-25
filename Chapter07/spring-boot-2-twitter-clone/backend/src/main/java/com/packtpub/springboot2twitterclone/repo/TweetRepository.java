package com.packtpub.springboot2twitterclone.repo;

import com.packtpub.springboot2twitterclone.model.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet, Integer> {

    List<Tweet> findByTweetUser_ScreenNameOrContentContains(String screenName, String mention);

}
