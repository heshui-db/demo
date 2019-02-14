package com.dongbin.algorithm;

public class LRU<T> {

    /**
     * 最大容量
     */
    private int capacity;

    /**
     * 当前链表大小
     */
    private int size;

    /**
     * 头结点
     */
    private Node<T> head;

    /**
     * 尾节点
     */
    private Node<T> tail;


    public LRU(int capacity) {
        this.size = 0;
        this.capacity = capacity;
    }

    public Node<T> get(T t) {
        Node<T> node = findNode(t);
        if (node != null) {
            moveExistNodeToHead(node);
        }
        return node;
    }

    public void put(T t) {
        if (t == null) {
            throw new NullPointerException("t is null");
        }

        Node<T> node = findNode(t);

        if (node == null) {
            node = new Node<>(t);
            if (this.size >= capacity) {
                removeLast();
            }
            addNodeToHead(node);
        } else {
            moveExistNodeToHead(node);
        }
    }

    private void addNodeToHead(Node<T> node) {
        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.prev = null;
            node.next = head;
            head.prev = node;
            head = node;
        }
        this.size++;
    }

    private void removeLast() {
        Node<T> node = tail.prev;
        node.next = null;
        tail.prev = null;
        tail = node;
    }

    private void moveExistNodeToHead(Node<T> node) {
        if (node == head) {
            return;
        } else if (node == tail) {
            Node<T> prev = node.prev;
            prev.next = null;
            tail.prev = null;
            tail = prev;
        } else {
            Node<T> prev = node.prev;
            prev.next = node.next;
            node.next.prev = prev;
        }

        node.prev = null;
        node.next = head;
        head = node;
    }

    private Node<T> findNode(T t) {
        Node<T> node = head;
        while (node != null) {
            if (node.value == t || node.value.equals(t)) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    /**
     * 双向链表结点
     *
     * @param <T>
     */
    private static class Node<T> {
        Node<T> prev;
        Node<T> next;
        T value;

        public Node(T value) {
            this.value = value;
        }
    }

}
