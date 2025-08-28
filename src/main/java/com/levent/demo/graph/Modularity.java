package com.levent.demo.graph;

import java.util.Map;
import java.util.Set;

public class Modularity {
    public double calculate(InteractionGraph graph, Map<String, Integer> communities) {
        double modularity = 0.0;
        double totalWeight = graph.getTotalEdgeWeight();

        if (totalWeight == 0) {
            return 0.0;
        }

        for (String node : graph.getNodes()) {
            for (String neighbor : graph.getNeighbors(node)) {
                if (communities.get(node).equals(communities.get(neighbor))) {
                    double nodeWeight = graph.getNodeWeight(node);
                    double neighborWeight = graph.getNodeWeight(neighbor);
                    modularity += 1.0 - (nodeWeight * neighborWeight) / (2.0 * totalWeight);
                }
            }
        }

        return modularity / (2.0 * totalWeight);
    }
}
