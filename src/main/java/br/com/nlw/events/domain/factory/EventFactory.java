package br.com.nlw.events.domain.factory;

import br.com.nlw.events.domain.dto.event.CreateEventDTO;
import br.com.nlw.events.domain.dto.event.EventResponseDTO;
import br.com.nlw.events.domain.models.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventFactory {
    Event toEvent(CreateEventDTO eventDTO);

    EventResponseDTO toEventResponseDTO(Event event);
}
