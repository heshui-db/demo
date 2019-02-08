package com.dongbin.algorithm.db20190208;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class GraphAlgorithm {

    /**
     * 图的层次遍历 广度优先
     *
     * @param node
     */
    public static void bfs(Node node) {
        if (node == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        Set<Node> nodeSet = new HashSet<>();

        queue.add(node);

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.value);
            for (Node next : node.nexts) {
                if (nodeSet.add(next)) {
                    queue.add(next);
                }
            }
        }
    }

    /**
     * 图的深度优先
     *
     * @param node
     */
    public static void dfs(Node node) {
        if (node == null) {
            return;
        }

        Stack<Node> nodeStack = new Stack<>();

        Set<Node> nodeSet = new HashSet<>();

        nodeStack.push(node);
        nodeSet.add(node);
        System.out.println(node.value);

        while (!nodeStack.isEmpty()) {
            Node cur = nodeStack.pop();

            for (Node next : cur.nexts) {
                if (nodeSet.add(next)) {
                    nodeStack.push(cur);
                    nodeStack.push(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }

    }


    /**
     * 图拓扑排序 有向无环图
     *
     * @param graph
     * @return
     */
    public static List<Node> sortedTopology(Graph graph) {
        List<Node> result = new ArrayList<>();

        Map<Node, Integer> inNodeMap = new HashMap<>();
        Queue<Node> zeroNodeQueue = new LinkedList<>();

        for (Node node : graph.nodes.values()) {
            inNodeMap.put(node, node.in);
            zeroNodeQueue.add(node);
        }

        while (!zeroNodeQueue.isEmpty()) {
            Node cur = zeroNodeQueue.poll();
            result.add(cur);
            for (Node next : cur.nexts) {
                inNodeMap.put(next, inNodeMap.get(next) - 1);
                if (inNodeMap.get(next) == 0) {
                    zeroNodeQueue.add(next);
                }
            }
        }
        return result;
    }
}
