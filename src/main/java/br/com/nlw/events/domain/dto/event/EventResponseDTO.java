package br.com.nlw.events.domain.dto.event;

import java.time.LocalDate;
import java.time.LocalTime;

public record EventResponseDTO(
    Integer id,
    String title,
    String prettyName,
    String location,
    Double price,
    LocalDate startDate,
    LocalDate endDate,
    LocalTime startTime,
    LocalTime endTime
) {
}
