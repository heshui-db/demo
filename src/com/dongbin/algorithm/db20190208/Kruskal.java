package com.dongbin.algorithm.db20190208;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

/**
 * 最小生成树 的kruskal算法  以edge考虑
 */
public class Kruskal {

    public static class UnionFind {

        private Map<Node, Node> fatherMap;
        private Map<Node, Integer> rankMap;

        public UnionFind() {
            this.fatherMap = new HashMap<>();
            this.rankMap = new HashMap<>();
        }

        public void init(Collection<Node> nodes) {
            fatherMap.clear();
            rankMap.clear();
            if (nodes != null) {
                nodes.forEach(node -> {
                    fatherMap.put(node, node);
                    rankMap.put(node, 1);
                });
            }
        }

        private Node findFather(Node node) {

            Stack<Node> stack = new Stack<>();
            while (node != fatherMap.get(node)) {
                stack.push(node);
                node = fatherMap.get(node);
            }

            while (!stack.isEmpty()) {
                fatherMap.put(stack.pop(), node);
            }
            return node;
        }

        public boolean isSameSet(Node a, Node b) {
            return findFather(a) == findFather(b);
        }

        public void union(Node a, Node b) {
            if (a == null || b == null) {
                return;
            }

            Node aFather = findFather(a);
            Node bFather = findFather(b);

            if (aFather != bFather) {
                int aRank = rankMap.get(aFather);
                int bRank = rankMap.get(bFather);
                if (aRank <= bRank) {
                    fatherMap.put(aFather, bFather);
                    rankMap.put(bFather, aRank + bRank);
                } else {
                    fatherMap.put(bFather, aFather);
                    rankMap.put(aFather, aRank + bRank);
                }
            }
        }
    }

    public static class EdgeComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static Set<Edge> kruskalMST(Graph graph) {
        UnionFind unionFind = new UnionFind();
        unionFind.init(graph.nodes.values());

        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());

        priorityQueue.addAll(graph.edges);

        Set<Edge> edges = new HashSet<>();

        while (!priorityQueue.isEmpty()) {
            Edge peek = priorityQueue.peek();
            if (!unionFind.isSameSet(peek.from, peek.to)) {
                edges.add(peek);
                unionFind.union(peek.from, peek.to);
            }
        }

        return edges;
    }
}
