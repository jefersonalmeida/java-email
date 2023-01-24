package com.jefersonalmeida.email.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jefersonalmeida.email.entity.EmailEntity;
import com.jefersonalmeida.email.enums.StatusEmail;

import java.time.Instant;

public record EmailResponse(
        @JsonProperty("email_id") Long emailId,
        @JsonProperty("owner_ref") String ownerRef,
        @JsonProperty("email_from") String emailFrom,
        @JsonProperty("email_to") String emailTo,
        @JsonProperty("subject") String subject,
        @JsonProperty("body") String body,
        @JsonProperty("sent_at") Instant sentAt,
        @JsonProperty("status") StatusEmail status
) {
    public static EmailResponse of(final EmailEntity entity) {
        return new EmailResponse(
                entity.getEmailId(),
                entity.getOwnerRef(),
                entity.getEmailFrom(),
                entity.getEmailTo(),
                entity.getSubject(),
                entity.getBody(),
                entity.getSentAt(),
                entity.getStatus()
        );
    }
}
