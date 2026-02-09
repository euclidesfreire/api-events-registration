package br.com.nlw.events.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import br.com.nlw.events.domain.dto.subscription.SubscriptionResponseDTO;
import br.com.nlw.events.domain.models.User;
import br.com.nlw.events.services.RankingService;
import br.com.nlw.events.services.SubscriptionService;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private RankingService rankingService;

    /**
     * Post Subscription
     * 
     * @param prettyName
     * @param user
     * @return SubscriptionResponseDTO
     */
    @PostMapping("/subscription/{prettyName}")
    public ResponseEntity<?> postSubscription(
        @PathVariable String prettyName, 
        @RequestBody User user
    ) {

        SubscriptionResponseDTO subscriptionNew = subscriptionService.add(prettyName, user);

        return ResponseEntity.ok().body(subscriptionNew);
    }

    /**
     * Post subscription by subscriptionIndicationId
     * 
     * @param prettyName
     * @param subscriptionIndicationId
     * @param user
     * @return
     */
    @PostMapping("/subscription/{prettyName}/{subscriptionIndicationId}")
    public ResponseEntity<?> postSubscriptionIndication(
        @PathVariable String prettyName, 
        @PathVariable Integer subscriptionIndicationId, 
        @RequestBody User user
    ) {

        // Add new subscription by subscription indication
        SubscriptionResponseDTO subscriptionNew = subscriptionService
        .addByIndication(prettyName, user, subscriptionIndicationId);

        return ResponseEntity.ok().body(subscriptionNew);
    }

    /**
     * Get subscription
     * 
     * @param prettyName
     * @return List<Subscription>
     */
    @GetMapping("/subscription/{prettyName}")
    public ResponseEntity<?>  getSubscription(@PathVariable String prettyName){
        return ResponseEntity.ok().body(subscriptionService.findAllByEvent(prettyName));
    }

    /**
     * Get Ranking Complete 
     * 
     * @param prettyName
     * @return List<Subscription>
     */
    @GetMapping("/subscription/{prettyName}/ranking")
    public ResponseEntity<?>  getRanking(@PathVariable String prettyName){
        return ResponseEntity.ok().body(rankingService.findRanking(prettyName));
    }

    /**
     * Get Ranking by Subscription
     * 
     * @param prettyName
     * @param subscriptionId
     * @return Subscription
     */
    @GetMapping("/subscription/{prettyName}/{subscriptionId}/ranking")
    public ResponseEntity<?> getRankingBySubscription(
            @PathVariable String prettyName,
            @PathVariable Integer subscriptionId
    ){
        return ResponseEntity.ok().body(rankingService.findRankingBySubscription(prettyName, subscriptionId));
    }
}
