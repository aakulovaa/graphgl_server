package com.levent.demo.graph;

import com.levent.demo.models.*;
import com.levent.demo.repository.AttendedRepository;
import com.levent.demo.repository.EventRepository;
import com.levent.demo.repository.FollowRepository;
import com.levent.demo.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GraphBuilderService {
    private final UsersRepository userRepository;
    private final EventRepository eventRepository;
    private final FollowRepository followRepository;
    private final AttendedRepository attendedRepository;

    public GraphBuilderService(UsersRepository userRepository, EventRepository eventRepository, FollowRepository followRepository, AttendedRepository attendedRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.followRepository = followRepository;
        this.attendedRepository = attendedRepository;
    }

    public InteractionGraph buildGraph() {
        InteractionGraph graph = new InteractionGraph();


        List<Follow> follows = followRepository.findAll();
        for (Follow follow : follows){
            String idFollower = "user" + follow.getFollower().getIdUser();
            String idFollowing = "currentUser" + follow.getFollowing().getIdUser();
            graph.addEdge(idFollower, idFollowing, 1.0);
        }

        List<Attended> attendedEvents = attendedRepository.findAll();
        for (Attended attended : attendedEvents){
            String idUser = STR."user\{attended.getUser().getIdUser()}";
            String idEvent = STR."event\{attended.getEvent().getIdEvent()}";
            graph.addEdge(idUser, idEvent, 1.5);

            // Добавляем связь с категорией
            CategoryEvent categoryEvent = attended.getEvent().getCategoryEvent();
            String idCategory = STR."category\{categoryEvent.getIdCategory()}";
            graph.addEdge(idUser, idCategory, 1.2);
            graph.addEdge(idEvent, idCategory, 1.0);
        }

        return graph;
    }
}

