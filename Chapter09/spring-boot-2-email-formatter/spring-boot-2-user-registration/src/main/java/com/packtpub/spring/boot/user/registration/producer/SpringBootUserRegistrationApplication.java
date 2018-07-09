package com.packtpub.spring.boot.user.registration.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.packtpub.spring.boot.email.formatter.model.config.RepoConfig;
import com.packtpub.spring.boot.email.formatter.model.domain.User;
import com.packtpub.spring.boot.email.formatter.model.repo.UserRepository;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@Import(RepoConfig.class)
public class SpringBootUserRegistrationApplication {

    public static void main(String... args) {
        SpringApplication.run(SpringBootUserRegistrationApplication.class, args);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(
            ProducerFactory<String, String> pf) {
        return new KafkaTemplate<>(pf);
    }

    @Bean
    public NewTopic resetPasswordRequests() {
        return new NewTopic("resetPasswordRequests", 10, (short) 2);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public ApplicationRunner applicationRunner(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            userRepository.save(new User(null, "shazin", "shazin.sadakath@gmail.com", passwordEncoder.encode("abc123")));
        };
    }

}
