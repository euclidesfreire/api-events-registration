package br.com.nlw.events.services;

import java.util.List;

import br.com.nlw.events.domain.factory.EventFactory;
import br.com.nlw.events.exceptions.AlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nlw.events.domain.dto.event.CreateEventDTO;
import br.com.nlw.events.domain.dto.event.EventResponseDTO;
import br.com.nlw.events.exceptions.NotFoundException;
import br.com.nlw.events.domain.models.Event;
import br.com.nlw.events.repositories.EventRepository;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventFactory eventFactory;

    /**
     * Add new event
     * 
     * @param CreateEventDTO 
     * 
     * @return EventResponseDTO 
    */
    public EventResponseDTO add(CreateEventDTO eventDTO){
        Event eventNew = this.eventFactory.toEvent(eventDTO);

        //Create PrettyName sep "-"
        eventNew.setPrettyName(eventNew.getTitle().toLowerCase().replaceAll(" ", "-"));

        //If Event Already Exists
        eventRepository.findByPrettyName(eventNew.getPrettyName())
        .ifPresent(event -> { throw new AlreadyExistsException("Event Already Exists: " + event.getPrettyName()); });

        Event eventSaved = eventRepository.save(eventNew);

        return this.eventFactory.toEventResponseDTO(eventSaved);
    }

    /**
     * Get all events
     * 
     * @return List Event
    */
    public List<Event> findAll(){
        return (List<Event>) this.eventRepository.findAll();
    }

    /**
     * Get Event By Id
     * 
     * @param String prettyName
     * 
     * @return Event
    */
    public Event findById(Integer id){
        return eventRepository.findById(id)
         .orElseThrow(() -> new NotFoundException("Event not found by id: " + id));
    }

    /**
     * Get Event By Pretty Name
     * 
     * @param String prettyName
     * 
     * @return Event
    */
    public Event findByPrettyName(String prettyName) {
        return eventRepository.findByPrettyName(prettyName)
        .orElseThrow(() -> new NotFoundException("Event not found by prettyName: "  + prettyName));
    }

    
}
