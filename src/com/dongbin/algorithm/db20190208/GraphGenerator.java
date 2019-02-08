package com.dongbin.algorithm.db20190208;

public class GraphGenerator {

    public static Graph initGraph(int[][] matrix) {

        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++) {
            Integer weight = matrix[i][0];
            Integer from = matrix[i][1];
            Integer to = matrix[i][2];

            Node fromNode = graph.nodes.putIfAbsent(from, new Node(from));
            Node toNode = graph.nodes.putIfAbsent(to, new Node(to));

            Edge edge = new Edge(weight, fromNode, toNode);
            fromNode.nexts.add(toNode);
            fromNode.edges.add(edge);
            fromNode.out++;
            toNode.in++;
            graph.edges.add(edge);
        }
        return graph;
    }
}
