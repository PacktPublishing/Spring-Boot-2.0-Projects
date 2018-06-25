package com.packtpub.springboot2twitterclone.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Executors;

@Configuration
public class DbConfig {

    private Integer connectionPoolSize;

    public DbConfig(@Value("${spring.datasource.maximum-pool-size:10}") Integer connectionPoolSize) {
        this.connectionPoolSize = connectionPoolSize;
    }

    @Bean
    public Scheduler dbScheduler() {
        return Schedulers.fromExecutor(Executors.newFixedThreadPool(connectionPoolSize));
    }
}
