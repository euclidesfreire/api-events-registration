package br.com.nlw.events.dto;

import br.com.nlw.events.models.User;

public record SubscriptionResponseDTO (
    Integer id, 
    User user,
    String indicationUrl
) {}
