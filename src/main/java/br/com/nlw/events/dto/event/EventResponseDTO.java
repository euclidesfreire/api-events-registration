package br.com.nlw.events.dto.event;

import java.time.LocalDate;
import java.time.LocalTime;

public record EventResponseDTO(
    Integer id,
    String title,
    String location,
    Double price,
    LocalDate startDate,
    LocalDate endDate,
    LocalTime startTime,
    LocalTime endTime
) {
}
