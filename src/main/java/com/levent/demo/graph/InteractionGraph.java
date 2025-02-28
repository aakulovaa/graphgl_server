package com.levent.demo.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * InteractionGraph отвечает за создание и управление графом взаимодействий
 */
public class InteractionGraph {
    private final Map<String, Set<String>> graph = new HashMap<>();
    private final Map<String, Double> nodeWeights = new HashMap<>();

    public void addEdge(String source, String target, double weight) {
        graph.computeIfAbsent(source, k -> new HashSet<>()).add(target);
        graph.computeIfAbsent(target, k -> new HashSet<>()).add(source);
        nodeWeights.put(source, nodeWeights.getOrDefault(source, 0.0) + weight);
        nodeWeights.put(target, nodeWeights.getOrDefault(target, 0.0) + weight);
    }

    public Map<String, Set<String>> getGraph() {
        return graph;
    }

    public Map<String, Double> getNodeWeights() {
        return nodeWeights;
    }

}
