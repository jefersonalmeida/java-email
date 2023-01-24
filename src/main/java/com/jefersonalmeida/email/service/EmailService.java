package com.jefersonalmeida.email.service;

import com.jefersonalmeida.email.enums.StatusEmail;
import com.jefersonalmeida.email.model.EmailRequest;
import com.jefersonalmeida.email.model.EmailResponse;
import com.jefersonalmeida.email.repository.EmailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    private final EmailRepository emailRepository;
    private final JavaMailSender mailSender;

    public EmailService(final EmailRepository emailRepository, final JavaMailSender mailSender) {
        this.emailRepository = emailRepository;
        this.mailSender = mailSender;
    }

    public EmailResponse send(final EmailRequest request) {
        final var entity = request.toEntity();
        entity.setStatus(StatusEmail.PENDING);
        entity.setSentAt(Instant.now());

        try {

            final var message = new SimpleMailMessage();
            message.setFrom(entity.getEmailFrom());
            message.setTo(entity.getEmailTo());
            message.setSubject(entity.getSubject());
            message.setText(entity.getBody());

            mailSender.send(message);

            entity.setStatus(StatusEmail.SENT);

            log.info("Email send success: {}", entity);
        } catch (MailException e) {
            entity.setStatus(StatusEmail.ERROR);

            log.error("Email send failed: {} - {}", entity, e.getMessage(), e);
        }

        return EmailResponse.of(this.emailRepository.save(entity));
    }
}
