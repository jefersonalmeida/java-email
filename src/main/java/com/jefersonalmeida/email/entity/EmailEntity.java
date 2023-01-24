package com.jefersonalmeida.email.entity;

import com.jefersonalmeida.email.enums.StatusEmail;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "TB_EMAIL")
public class EmailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "EMAIL_ID", nullable = false)
    private Long emailId;
    @Column(name = "OWNER_REF", nullable = false)
    private String ownerRef;
    @Column(name = "EMAIL_FROM", nullable = false)
    private String emailFrom;
    @Column(name = "EMAIL_TO", nullable = false)
    private String emailTo;
    @Column(name = "SUBJECT", nullable = false)
    private String subject;
    @Column(name = "BODY", columnDefinition = "TEXT")
    private String body;
    @Column(name = "SEND_AT", nullable = false)
    private Instant sentAt;
    @Column(name = "STATUS", nullable = false)
    private StatusEmail status;

    public EmailEntity() {
    }

    public EmailEntity(
            String ownerRef,
            String emailFrom,
            String emailTo,
            String subject,
            String body
    ) {
        this.ownerRef = ownerRef;
        this.emailFrom = emailFrom;
        this.emailTo = emailTo;
        this.subject = subject;
        this.body = body;
    }

    public static EmailEntity of(
            final String ownerRef,
            final String emailFrom,
            final String emailTo,
            final String subject,
            final String body
    ) {
        return new EmailEntity(ownerRef, emailFrom, emailTo, subject, body);
    }

    public Long getEmailId() {
        return emailId;
    }

    public void setEmailId(Long emailId) {
        this.emailId = emailId;
    }

    public String getOwnerRef() {
        return ownerRef;
    }

    public void setOwnerRef(String ownerRef) {
        this.ownerRef = ownerRef;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Instant getSentAt() {
        return sentAt;
    }

    public void setSentAt(Instant sendAt) {
        this.sentAt = sendAt;
    }

    public StatusEmail getStatus() {
        return status;
    }

    public void setStatus(StatusEmail status) {
        this.status = status;
    }
}
