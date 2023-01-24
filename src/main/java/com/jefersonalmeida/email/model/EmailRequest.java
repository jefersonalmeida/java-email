package com.jefersonalmeida.email.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jefersonalmeida.email.entity.EmailEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailRequest(
        @JsonProperty("owner_ref") @NotBlank String ownerRef,
        @JsonProperty("email_from") @NotBlank @Email String emailFrom,
        @JsonProperty("email_to") @NotBlank @Email String emailTo,
        @JsonProperty("subject") @NotBlank String subject,
        @JsonProperty("body") @NotBlank String body
) {
    public EmailEntity toEntity() {
        return EmailEntity.of(ownerRef(), emailFrom(), emailTo(), subject(), body());
    }
}
