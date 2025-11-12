package br.com.nlw.events.dto.event;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record CreateEventDTO(
    
    @NotBlank
    @Size(max=255)
    String title,

    @NotBlank
    @Size(max=255)
    String location,

    @NotNull
    @PositiveOrZero
    Double price,

    @NotNull
    @Future
    LocalDate startDate,

    @NotNull
    @Future
    LocalDate endDate,

    @NotNull
    LocalTime startTime,

    @NotNull
    LocalTime endTime
) {}