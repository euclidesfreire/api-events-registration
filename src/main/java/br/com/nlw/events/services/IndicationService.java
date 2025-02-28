package br.com.nlw.events.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nlw.events.models.Indication;
import br.com.nlw.events.models.Subscription;
import br.com.nlw.events.models.User;
import br.com.nlw.events.repositories.IndicationRepository;

@Service
public class IndicationService {

    @Autowired
    private IndicationRepository indicationRepository;

    /**
     * Add Indication New
     * 
     * @param subscription
     * @param user
     */
    public void add(Subscription subscription, User user){
        Indication indication = new Indication();
        indication.setSubscriptionIndication(subscription);
        indication.setUser(user);

        indicationRepository.save(indication);
    }

    public String getUrl(String eventPrettyName, Integer subscriptionId){
        String url = "/subscription/" + eventPrettyName + "/" + subscriptionId;

        return url;
    }
}
