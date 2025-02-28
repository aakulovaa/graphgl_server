package com.levent.demo.graph;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * LeidenConfig настраивает бины для работы с графом и алгоритмом Лейдена
 */
@Configuration
public class LeidenConfig {

    @Bean
    public InteractionGraph interactionGraph() {
        InteractionGraph graph = new InteractionGraph();

        graph.addEdge("1","2",1.0);
        graph.addEdge("3","4",1.0);
        graph.addEdge("2","5",1.0);

        System.out.println(graph.getGraph());
        return graph;
    }

    @Bean
    public LeidenAlgorithm leidenAlgorithm(InteractionGraph graph) {
        return new LeidenAlgorithm(graph);
    }
}
