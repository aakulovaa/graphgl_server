package com.levent.demo.graph;

import com.levent.demo.models.Events;
import com.levent.demo.models.Users;
import com.levent.demo.repository.EventRepository;
import com.levent.demo.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GraphBuilderService {
    private final UsersRepository userRepository;
    private final EventRepository eventRepository;

    public GraphBuilderService(UsersRepository userRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    public InteractionGraph buildGraph() {
        InteractionGraph graph = new InteractionGraph();

        // Загружаем пользователей с их взаимодействиями
        List<Users> users = userRepository.findAllWithInteractions();
        for (Users user : users) {
            String userId = "user" + user.getIdUser(); // Префикс "user" для пользователей

            // Добавляем рёбра для избранных товаров
            for (Events favorite : user.getAttendedEvents()) {
                String productId = "event" + favorite.getIdEvent(); // Префикс "event" для мероприятий
                graph.addEdge(userId, productId, 1.5); // Вес для избранного
            }

            // Добавляем рёбра для подписок на других пользователей
            for (Users followee : user.getFollows()) {
                String followeeId = "user" + followee.getIdUser(); // Префикс "user" для пользователей
                graph.addEdge(userId, followeeId, 1.0); // Вес для подписки
            }
            // Добавляем рёбра для посещенных мероприятий
            for (Events purchase : user.getAttendedEvents()) {
                String productId = "event" + purchase.getIdEvent(); // Префикс "event" для мероприятий
                graph.addEdge(userId, productId, 2.0); // Вес для мероприятия
            }
        }

        return graph;
    }
}

