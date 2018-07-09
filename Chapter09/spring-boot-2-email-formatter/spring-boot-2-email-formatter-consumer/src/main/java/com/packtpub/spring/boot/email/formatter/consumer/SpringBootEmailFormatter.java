package com.packtpub.spring.boot.email.formatter.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.packtpub.spring.boot.email.formatter.consumer.service.EmailSenderService;
import com.packtpub.spring.boot.email.formatter.model.ResetPasswordRequest;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;

import java.io.IOException;

@SpringBootApplication
public class SpringBootEmailFormatter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootEmailFormatter.class);

    @Autowired
    private EmailSenderService emailSenderService;

    public static void main(String... args) {
        new SpringApplicationBuilder(SpringBootEmailFormatter.class).web(WebApplicationType.NONE).build().run(args);
    }

    @KafkaListener(id="resetPasswordRequests", topics = "resetPasswordRequests")
    public String listen(String in) {
        try {
            ResetPasswordRequest resetPasswordRequest = objectMapper().readValue(in, ResetPasswordRequest.class);
            emailSenderService.sendResetPasswordEmail(resetPasswordRequest);
        } catch (IOException e) {
            LOGGER.error("Error while sending Reset Password Email", e);
        }
        return in;
    }

    @Bean
    public NewTopic resetPasswordRequests() {
        return new NewTopic("resetPasswordRequests", 10, (short) 2);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
