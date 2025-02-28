package com.levent.demo.graph;

import com.levent.demo.models.Events;
import com.levent.demo.models.Users;
import com.levent.demo.repository.EventRepository;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class EventRecommender {
    private final EventRepository eventRepository;

    public EventRecommender(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Events> recommendProducts(Users user, Map<String, Integer> communities) {
        int userCommunity = communities.get(user.getIdUser());
        return eventRepository.findByCommunity(Set.of(userCommunity))
                .stream()
                .filter(p -> !user.getAttendedEvents().contains(p))
                .collect(Collectors.toList());
    }
}
