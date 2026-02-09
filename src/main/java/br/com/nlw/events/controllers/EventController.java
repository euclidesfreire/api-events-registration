package br.com.nlw.events.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import br.com.nlw.events.domain.dto.event.CreateEventDTO;
import br.com.nlw.events.domain.dto.event.EventResponseDTO;
import br.com.nlw.events.domain.models.Event;
import br.com.nlw.events.services.EventService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    /**
     * 
     * @param CreateEventDTO
     * 
     * @return EventResponseDTO
     */
    @PostMapping("/events")
    public ResponseEntity<EventResponseDTO> postEvent(
        @RequestBody @Valid CreateEventDTO event
    ){
        return ResponseEntity
                .ok()
                .body(eventService.add(event));
    }

    @GetMapping("/events")
    public List<Event> getAllEvents() {
        return (List<Event>) this.eventService.findAll();
    }

    /**
     * 
     * @param id
     * @return Ev
     */
    @GetMapping("/events/{id}")
    public ResponseEntity<?> getEventById(@PathVariable Integer id) {
        Event event = this.eventService.findById(id);

        return ResponseEntity.ok().body(event);
    }

    /**
     * 
     * @param prettyName
     * @return Event
     */
    @GetMapping("/events/{prettyName}")
    public ResponseEntity<?> getEventByPrettyName(@PathVariable String prettyName) {

        Event event = this.eventService.findByPrettyName(prettyName);

        return ResponseEntity.ok().body(event);

    }
}
