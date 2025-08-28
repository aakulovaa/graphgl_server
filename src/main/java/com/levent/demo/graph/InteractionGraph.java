package com.levent.demo.graph;

import java.util.*;

/**
 * InteractionGraph отвечает за создание и управление графом взаимодействий
 */
public class InteractionGraph {
    private final Map<String, Set<String>> graph = new HashMap<>();
    private final Map<String, Double> nodeWeights = new HashMap<>();
    private double totalEdgeWeight = 0.0;

    public void addEdge(String source, String target, double weight) {
        graph.computeIfAbsent(source, k -> new HashSet<>()).add(target);
        graph.computeIfAbsent(target, k -> new HashSet<>()).add(source);

        nodeWeights.put(source, nodeWeights.getOrDefault(source, 0.0) + weight);
        nodeWeights.put(target, nodeWeights.getOrDefault(target, 0.0) + weight);

        totalEdgeWeight += weight;
    }

    public Set<String> getNodes() {
        return graph.keySet();
    }

    public Set<String> getNeighbors(String node) {
        return graph.getOrDefault(node, Collections.emptySet());
    }

    public double getNodeWeight(String node) {
        return nodeWeights.getOrDefault(node, 0.0);
    }

    public double getTotalEdgeWeight() {
        return totalEdgeWeight;
    }

    public Map<String, Set<String>> getAdjacencyList() {
        return Collections.unmodifiableMap(graph);
    }

    public Map<String, Double> getNodeWeights() {
        return Collections.unmodifiableMap(nodeWeights);
    }

}
