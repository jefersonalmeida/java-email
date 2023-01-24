package com.jefersonalmeida.email.controller;

import com.jefersonalmeida.email.model.EmailRequest;
import com.jefersonalmeida.email.model.EmailResponse;
import com.jefersonalmeida.email.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Email")
@RequestMapping(value = "email")
@RestController
public class EmailController {

    private final EmailService emailService;

    public EmailController(final EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping(
            value = "sending",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Send email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Send successfully"),
            @ApiResponse(responseCode = "422", description = "Validation error was thrown"),
            @ApiResponse(responseCode = "500", description = "Internal server error was thrown")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EmailResponse> sending(@RequestBody @Valid final EmailRequest request) {
        final var response = this.emailService.send(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
