package com.dongbin.algorithm.db20190208;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 源节点 到 所有节点的最路径值  Dijkstra算法
 */
public class Dijkstra {

    public static Map<Node, Integer> dijsktra(Node node) {
        Map<Node, Integer> distanceMap = new HashMap<>();
        Set<Node> nodeSet = new HashSet<>();

        distanceMap.put(node, 0);
        nodeSet.add(node);

        Node minNode = getMinDistanceNode(distanceMap, nodeSet);

        while (minNode != null) {
            int distance = distanceMap.get(minNode);
            for (Edge edge : minNode.edges) {
                Node toNode = edge.to;
                if (!distanceMap.containsKey(toNode)) {
                    distanceMap.put(toNode, distance + edge.weight);
                }
                distanceMap.put(toNode, Math.min(distanceMap.get(toNode), distance + edge.weight));
            }
            nodeSet.add(minNode);
            minNode = getMinDistanceNode(distanceMap, nodeSet);
        }
        return distanceMap;
    }

    private static Node getMinDistanceNode(Map<Node, Integer> distanceMap, Set<Node> nodeSet) {
        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            int distance = entry.getValue();
            Node node = entry.getKey();
            if (!nodeSet.contains(node) && distance < minDistance) {
                minNode = node;
                minDistance = distance;
            }
        }

        return minNode;
    }
}
