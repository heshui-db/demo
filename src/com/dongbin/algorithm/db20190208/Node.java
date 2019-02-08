package com.dongbin.algorithm.db20190208;

import java.util.ArrayList;
import java.util.List;

public class Node {

    public int value;

    //入度
    public int in;

    //出度
    public int out;

    public List<Edge> edges;

    public List<Node> nexts;

    public Node(int value) {
        this.value = value;
        this.in = 0;
        this.out = 0;
        this.edges = new ArrayList<>();
        this.nexts = new ArrayList<>();
    }
}
