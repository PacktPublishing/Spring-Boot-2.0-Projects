package com.packtpub.springboot2twitterclone.repo;

import com.packtpub.springboot2twitterclone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    User findByScreenName(String screenName);

}
