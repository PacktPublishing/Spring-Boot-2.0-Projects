package com.packtpub.spring.boot.email.formatter.model.repo;

import com.packtpub.spring.boot.email.formatter.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    User findByUsername(String username);

}
