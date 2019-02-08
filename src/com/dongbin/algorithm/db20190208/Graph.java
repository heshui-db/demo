package com.dongbin.algorithm.db20190208;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 图结构
 */
public class Graph {
    public Map<Integer, Node> nodes;
    public Set<Edge> edges;

    public Graph() {
        this.nodes = new HashMap<>();
        this.edges = new HashSet<>();
    }
}
