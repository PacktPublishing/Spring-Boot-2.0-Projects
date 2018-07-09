package com.packtpub.spring.boot.user.registration.producer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.packtpub.spring.boot.email.formatter.model.ResetPasswordRequest;
import com.packtpub.spring.boot.email.formatter.model.domain.User;
import org.apache.commons.text.RandomStringGenerator;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Transactional(readOnly = true)
public class AsyncService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final UserService userService;
    private final ObjectMapper objectMapper;
    private final PasswordEncoder passwordEncoder;


    public AsyncService(KafkaTemplate<String, String> kafkaTemplate, UserService userService, ObjectMapper objectMapper, PasswordEncoder passwordEncoder) {
        this.kafkaTemplate = kafkaTemplate;
        this.userService = userService;
        this.objectMapper = objectMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(rollbackFor = Exception.class)
    public void sendResetPassword(String email) throws IOException {
        User user = userService.getByEmail(email);

        if (user != null) {
            ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest();
            resetPasswordRequest.setEmail(user.getEmail());

            RandomStringGenerator generator = new RandomStringGenerator.Builder()
                    .withinRange('a', 'z').build();
            String newPassword = generator.generate(10);
            resetPasswordRequest.setNewPassword(newPassword);
            resetPasswordRequest.setUsername(user.getUsername());
            ProducerRecord<String, String> record = new ProducerRecord<>("resetPasswordRequests", objectMapper.writeValueAsString(resetPasswordRequest));
            kafkaTemplate.send(record);

            user.setPassword(passwordEncoder.encode(newPassword));
            userService.save(user);
        }
    }
}
