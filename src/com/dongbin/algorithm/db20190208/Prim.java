package com.dongbin.algorithm.db20190208;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 最小生成树 prim 以节点考虑
 */
public class Prim {

    public static Set<Edge> primMST(Graph graph) {

        Set<Edge> result = new HashSet<>();
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
        Set<Node> nodeSet = new HashSet<>();
        for (Node node : graph.nodes.values()) {
            if (nodeSet.add(node)) {
                node.edges.forEach(edge -> priorityQueue.add(edge));
            }
            while (!priorityQueue.isEmpty()) {
                Edge edge = priorityQueue.peek();
                result.add(edge);
                if (nodeSet.add(edge.to)) {
                    edge.to.edges.forEach(e -> priorityQueue.add(e));
                }
            }
        }
        return result;
    }
}
