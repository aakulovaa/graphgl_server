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
    private final EventRecommender eventRecommender;
    private final UserRecommender userRecommender;

    public RecommendationResolver(LeidenAlgorithm leidenAlgorithm, EventRepository eventRepository, UsersRepository userRepository, EventRecommender eventRecommender, UserRecommender userRecommender) {
        this.leidenAlgorithm = leidenAlgorithm;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.eventRecommender = eventRecommender;
        this.userRecommender = userRecommender;
    }
    @QueryMapping
    public List<Events> recommendedEvents(@Argument Integer idUser) {
        Users user = userRepository.findById(idUser)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Map<String, Integer> communities = leidenAlgorithm.findCommunities();
        return eventRecommender.recommendEvents(user, communities);
    }

    
    @QueryMapping
    public List<Users> recommendedUsers(@Argument Integer idUser) {
        Users user = userRepository.findById(idUser)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Map<String, Integer> communities = leidenAlgorithm.findCommunities();
        return userRecommender.recommendUsers(user,communities);
    }

}
