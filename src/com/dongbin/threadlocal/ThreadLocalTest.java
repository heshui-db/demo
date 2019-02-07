package com.dongbin.threadlocal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ThreadLocalTest extends Thread {

    private static Node a = new Node("A", "AA");
    private static Node b = new Node("B", "BB");
    private static List<Node> nodes = new ArrayList<>();

    private static final ThreadLocal<List<Node>> LIST_THREAD_LOCAL = ThreadLocal.withInitial(() -> {



        nodes.add(a);
        nodes.add(b);
        return nodes;
    });

    @Override
    public void run() {
        LIST_THREAD_LOCAL.get().forEach(node -> {
            System.out.println(node.getName());
            node.setName(Thread.currentThread().getName());
        });

        LIST_THREAD_LOCAL.set(LIST_THREAD_LOCAL.get().stream().map(node -> {
            node.setName(Thread.currentThread().getName());
            return node;
        }).collect(Collectors.toList()));
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new ThreadLocalTest().start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
