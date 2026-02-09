package br.com.nlw.events.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.nlw.events.domain.models.Indication;
import br.com.nlw.events.domain.models.Subscription;
import br.com.nlw.events.domain.models.User;

public interface IndicationRepository extends CrudRepository<Indication, Integer> {
        public Optional<Indication> findBySubscriptionIndicationAndUser(Subscription subscription, User user);

}
