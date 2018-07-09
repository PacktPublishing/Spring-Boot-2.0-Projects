package com.packtpub.spring.boot.email.formatter.model.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.packtpub.spring.boot.email.formatter.model.repo")
@EnableTransactionManagement
@EntityScan(basePackages = "com.packtpub.spring.boot.email.formatter.model.domain")
public class RepoConfig {
}
