package br.com.nlw.events.domain.dto.subscription;

import br.com.nlw.events.domain.models.User;

public record SubscriptionResponseDTO (
    Integer id, 
    User user,
    String indicationUrl
) {}
