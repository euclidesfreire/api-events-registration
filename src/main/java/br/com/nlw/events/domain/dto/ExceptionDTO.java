package br.com.nlw.events.domain.dto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for exception 
 */
public record ExceptionDTO(
    Integer code,
    String status,
    List<String> message,
    LocalDateTime timestamp
) {}
