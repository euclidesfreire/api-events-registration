package br.com.nlw.events.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nlw.events.dto.SubscriptionResponseDTO;
import br.com.nlw.events.exceptions.AlreadyExistsException;
import br.com.nlw.events.exceptions.NotFoundException;
import br.com.nlw.events.models.Event;
import br.com.nlw.events.models.Subscription;
import br.com.nlw.events.models.User;
import br.com.nlw.events.repositories.SubscriptionRepository;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @Autowired
    private IndicationService indicationService;

    /**
     * Add Subscription New
     * 
     * @param eventPrettyName
     * @param userNew
     * @return SubscriptionResponseDTO
     */
    public SubscriptionResponseDTO add(String eventPrettyName, User userNew) {

        //check e find event
        Event event = eventService.findByPrettyName(eventPrettyName);

        //Create user or if Already Exists, get user
        User user = userService.add(userNew);

        if(subscriptionRepository.findByEventAndUser(event, user).isPresent()){
            throw new AlreadyExistsException("Subscription Already Exists.");
        }

        Subscription subscriptionNew = new Subscription();
        subscriptionNew.setEvent(event);
        subscriptionNew.setUser(user);

        Subscription subscriptionSave = subscriptionRepository.save(subscriptionNew);

        String indicationUrl = indicationService.getUrl(eventPrettyName, subscriptionSave.getId());

        return new SubscriptionResponseDTO(subscriptionSave.getId(), user, indicationUrl);
    }

    /**
     * Add Subscription New by Indication
     * 
     * @param eventPrettyName
     * @param user
     * @param subscriptionIndicationId
     * @return SubscriptionResponseDTO
     */
    public SubscriptionResponseDTO addByIndication(
        String eventPrettyName, 
        User user, 
        Integer subscriptionIndicationId
    ){
        //Get subscription indication
        Subscription subscriptionIndication = subscriptionRepository
        .findById(subscriptionIndicationId)
        .orElseThrow(() -> new NotFoundException("Subscription indication not found."));

        //If eventPrettyName equal event of subscriptionIndicationId
        if(!subscriptionIndication.getEvent().getPrettyName().equals(eventPrettyName)){
            throw new NotFoundException("Subscription indication not found in event: " + eventPrettyName);
        }

        //Add Subscription New 
        SubscriptionResponseDTO subscriptionNew = add(eventPrettyName, user);

        //indicationCount + 1
        Integer indicationCount = subscriptionIndication.getIndicationCount() + 1;

        subscriptionIndication.setIndicationCount(indicationCount);

        //Update subscription for add indicationCount
        save(subscriptionIndication);

        indicationService.add(subscriptionIndication, subscriptionNew.user());

        return subscriptionNew;
    }

    public Subscription save(Subscription subscriptionNew) {
        return subscriptionRepository.save(subscriptionNew);
    }

    public Subscription findByIdAndEvent(Event event, User user) {

        Subscription subscription = subscriptionRepository
                .findByEventAndUser(event, user)
                .orElseThrow(() -> new NotFoundException("Subscription not found."));

        return subscription;
    }

    public Subscription findByEventAndUser(Event event, User user) {

        Subscription subscription = subscriptionRepository
                .findByEventAndUser(event, user)
                .orElseThrow(() -> new NotFoundException("Subscription not found."));

        return subscription;
    }

    public Optional<Subscription> findById(Integer id) {
        return subscriptionRepository.findById(id);
    }

    public List<Subscription> findAllByEvent(String prettyName) {

        Event event = eventService.findByPrettyName(prettyName);
        return (List<Subscription>) subscriptionRepository.findAllByEvent(event);
    }
}
