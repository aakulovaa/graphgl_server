package com.levent.demo.graph;

import java.util.*;

/**
 * LeidenAlgorithm реализует алгоритм Лейдена для поиска сообществ
 */
public class LeidenAlgorithm {
    private final InteractionGraph graph;
    private Map<String, Integer> communities;

    private final Random random = new Random();
    private static final double RESOLUTION = 1.0;

    public LeidenAlgorithm(InteractionGraph graph) {
        this.graph = graph;
        this.communities = new HashMap<>();
        // Инициализация: каждый узел в своем сообществе
        initializeCommunities();
    }

    private void initializeCommunities() {
        int communityId = 0;
        for (String node : graph.getNodes()) {
            communities.put(node, communityId++);
        }
    }

    public Map<String, Integer> findCommunities() {
        boolean improved;
        do {
            improved = refineCommunities();
        } while (improved);

        return Collections.unmodifiableMap(communities);
    }

    private boolean refineCommunities() {
        boolean changed = false;
        List<String> nodes = new ArrayList<>(graph.getNodes());
        Collections.shuffle(nodes);

        for (String node : nodes) {
            int bestCommunity = findBestCommunity(node);
            if (!communities.get(node).equals(bestCommunity)) {
                communities.put(node, bestCommunity);
                changed = true;
            }
        }
        return changed;
    }

    private int findBestCommunity(String node) {
        Map<Integer, Double> communityScores = new HashMap<>();
        double nodeWeight = graph.getNodeWeight(node);
        int currentCommunity = communities.get(node);

        for (String neighbor : graph.getNeighbors(node)) {
            int neighborCommunity = communities.get(neighbor);
            double neighborWeight = graph.getNodeWeight(neighbor);
            double edgeWeight = 1.0; // Simplified - can use actual weights

            double deltaQ = edgeWeight - (nodeWeight * neighborWeight) /
                    (2 * graph.getTotalEdgeWeight()) * RESOLUTION;

            communityScores.merge(neighborCommunity, deltaQ, Double::sum);
        }
        // Ensure current community is considered
        communityScores.putIfAbsent(currentCommunity, 0.0);

        return communityScores.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(currentCommunity);
    }
}
