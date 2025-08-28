package com.levent.demo.graph;

import com.levent.demo.repository.EventRepository;
import com.levent.demo.repository.UsersRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * LeidenConfig настраивает бины для работы с графом и алгоритмом Лейдена
 */
@Configuration
public class LeidenConfig {

    @Bean
    public InteractionGraph interactionGraph(GraphBuilderService graphBuilderService) {
        return graphBuilderService.buildGraph();
    }

    @Bean
    public LeidenAlgorithm leidenAlgorithm(InteractionGraph graph) {
        return new LeidenAlgorithm(graph);
    }

    @Bean
    public Modularity modularity() {
        return new Modularity();
    }
    @Bean
    public EventRecommender eventRecommender(EventRepository eventRepository) {
        return new EventRecommender(eventRepository);
    }

    @Bean
    public UserRecommender userRecommender(UsersRepository usersRepository) {
        return new UserRecommender(usersRepository);
    }
}
