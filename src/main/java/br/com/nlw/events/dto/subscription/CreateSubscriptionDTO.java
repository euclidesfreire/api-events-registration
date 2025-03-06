package br.com.nlw.events.dto.subscription;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateSubscriptionDTO(
    @NotBlank
    @Size(max=255)
    String name,

    @NotBlank
    @Size(max=255)
    String email
) {}
