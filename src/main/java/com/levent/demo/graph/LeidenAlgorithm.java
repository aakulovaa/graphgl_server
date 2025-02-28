package com.levent.demo.graph;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * LeidenAlgorithm реализует алгоритм Лейдена для поиска сообществ
 */
public class LeidenAlgorithm {
    private final InteractionGraph graph;
    private Map<String, Integer> communities;

    public LeidenAlgorithm(InteractionGraph graph) {
        this.graph = graph;
        this.communities = new HashMap<>();
        // Инициализация: каждый узел в своем сообществе
        int communityId = 0;
        for (String node : graph.getGraph().keySet()) {
            communities.put(node, communityId++);
        }
    }

    public Map<String, Integer> findCommunities() {
        boolean changed;
        do {
            changed = false;
            for (String node : graph.getGraph().keySet()) {
                int bestCommunity = findBestCommunity(node);
                if (!communities.get(node).equals(bestCommunity)) {
                    communities.put(node, bestCommunity);
                    changed = true;
                }
            }
        } while (changed);
        return communities;
    }

    private int findBestCommunity(String node) {
        Map<Integer, Double> communityWeights = new HashMap<>();
        for (String neighbor : graph.getGraph().get(node)) {
            int neighborCommunity = communities.get(neighbor);
            communityWeights.put(neighborCommunity, communityWeights.getOrDefault(neighborCommunity, 0.0) + 1.0);
        }
        return Collections.max(communityWeights.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
}
