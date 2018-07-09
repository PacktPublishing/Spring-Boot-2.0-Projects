package com.packtpub.spring.boot.email.formatter.consumer.service;

import com.packtpub.spring.boot.email.formatter.model.ResetPasswordRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class EmailSenderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailTemplateService.class);

    public static final String RESET_PASSWORD_EN_HTML = "reset_password_en.html";

    private final EmailTemplateService emailTemplateService;
    private final JavaMailSender javaMailSender;

    public EmailSenderService(EmailTemplateService emailTemplateService, JavaMailSender javaMailSender) {
        this.emailTemplateService = emailTemplateService;
        this.javaMailSender = javaMailSender;
    }

    public boolean sendResetPasswordEmail(ResetPasswordRequest resetPasswordRequest) {
        boolean sent = false;
        try {
            Map<String, Object> data = new LinkedHashMap<>();
            data.put("USERNAME", resetPasswordRequest.getUsername());
            data.put("NEW_PASSWORD", resetPasswordRequest.getNewPassword());

            String resetPasswordEmailContent = emailTemplateService.getResetPasswordEmail(data, RESET_PASSWORD_EN_HTML);

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

            mimeMessageHelper.setTo(resetPasswordRequest.getEmail());
            mimeMessageHelper.setText(resetPasswordEmailContent, true);
            mimeMessageHelper.setSubject("Password Reset");

            javaMailSender.send(mimeMessage);
            sent = true;
        } catch (Exception e) {
            LOGGER.error("Error while sending email", e);
        }
        return sent;
    }
}
