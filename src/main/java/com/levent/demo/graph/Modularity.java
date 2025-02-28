package com.levent.demo.graph;

import java.util.Map;
import java.util.Set;

public class Modularity {
    public double calculate(InteractionGraph graph, Map<String, Integer> communities) {
        double modularity = 0.0;
        int totalEdges = graph.getGraph().values().stream().mapToInt(Set::size).sum();
        for (String node : graph.getGraph().keySet()) {
            for (String neighbor : graph.getGraph().get(node)) {
                if (communities.get(node).equals(communities.get(neighbor))) {
                    modularity += 1.0 - (graph.getNodeWeights().get(node) * graph.getNodeWeights().get(neighbor)) / (2.0 * totalEdges);
                }
            }
        }
        return modularity / (2.0 * totalEdges);
    }
}
