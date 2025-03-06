package br.com.nlw.events.dto.event;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateEventDTO(
    
    @NotBlank
    @Size(max=255)
    String title,

    @NotBlank
    @Size(max=255)
    String location,

    @NotBlank
    Double price,

    @NotBlank
    LocalDate startDate,

    @NotBlank
    LocalDate endDate,

    @NotBlank
    LocalTime startTime,

    @NotBlank
    LocalTime endTime
) {}