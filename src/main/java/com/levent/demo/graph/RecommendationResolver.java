package com.levent.demo.graph;

import com.levent.demo.models.Events;
import com.levent.demo.models.Users;
import com.levent.demo.repository.EventRepository;
import com.levent.demo.repository.UsersRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class RecommendationResolver {
    private final LeidenAlgorithm leidenAlgorithm;
    private final EventRepository eventRepository;
    private final UsersRepository userRepository;

    public RecommendationResolver(LeidenAlgorithm leidenAlgorithm, EventRepository eventRepository, UsersRepository userRepository) {
        this.leidenAlgorithm = leidenAlgorithm;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }
    @QueryMapping
    public List<Events> recommendedEvents(@Argument String idUser) {
        Map<String, Integer> communities = leidenAlgorithm.findCommunities();
        int userCommunity = communities.get(idUser);
        return eventRepository.findByCommunity(Set.of(userCommunity));
    }

    @QueryMapping
    public List<Users> recommendedUsers(@Argument String idUser) {
        Map<String, Integer> communities = leidenAlgorithm.findCommunities();
        if (communities.isEmpty()) {
            throw new IllegalStateException("Communities are empty. Check if the graph is populated.");
        }

        Integer communityId = communities.get(idUser.toString()); // Приводим userId к String
        if (communityId == null) {
            throw new IllegalArgumentException("User with ID " + idUser + " not found in communities.");
        }

        System.out.println("Communities: " + communities);
        System.out.println("Community ID: " + communityId);
        System.out.println("Recommended users: " + userRepository.findByCommunity(Set.of(communityId)));

        return userRepository.findByCommunity(Set.of(communityId));
    }

}
