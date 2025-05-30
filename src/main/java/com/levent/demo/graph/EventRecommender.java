package com.levent.demo.graph;

import com.levent.demo.models.Attended;
import com.levent.demo.models.CategoryEvent;
import com.levent.demo.models.Events;
import com.levent.demo.models.Users;
import com.levent.demo.repository.EventRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class EventRecommender {
    private final EventRepository eventRepository;

    public EventRecommender(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Events> recommendEvents(Users user, Map<String, Integer> communities) {
        int userCommunity = communities.get("user" + user.getIdUser());
        //получаем мероприятия из сообщества пользователя
        List<Events> communityEvents = eventRepository.findByCommunity(Set.of(userCommunity));

        //получаем мероприятия из категорий, с которыми взаимодействовал пользователь
        Set<CategoryEvent> userCategories = user.getEventsAttended().stream()
                .map(Attended::getEvent)
                .map(Events::getCategoryEvent)
                .collect(Collectors.toSet());

        List<Events> categoryEvents = userCategories.stream()
                .flatMap(categoryEvent -> eventRepository.findByCategoryEvent(categoryEvent)
                        .stream())
                .toList();

        List<Events> allEvents = new ArrayList<>();
        allEvents.addAll(communityEvents);
        allEvents.addAll(categoryEvents);


        return allEvents.stream()
                .distinct()
                .filter(e -> !user.getEventsAttended().contains(e))
                .toList();
    }
}
