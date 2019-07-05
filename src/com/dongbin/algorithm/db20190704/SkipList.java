package com.dongbin.algorithm.db20190704;


public class SkipList {

    private int maxLevel;
    private Node head;
    private int size;
    private int levelCount;

    public SkipList() {
        this(16);
    }

    public SkipList(int maxLevel) {
        if (maxLevel <= 0) {
            throw new RuntimeException("maxLevel >0");
        }
        this.maxLevel = maxLevel;
        this.head = new Node(-1, maxLevel);
        this.levelCount = 1;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public Node get(int value) {
        Node tmp = head;
        for (int i = levelCount - 1; i >= 0; i--) {
            while (tmp.next[i] != null && tmp.next[i].value < value) {
                tmp = tmp.next[i];
            }
        }

        if (tmp.next[0] != null && tmp.next[0].value == value) {
            return tmp.next[0];
        }

        return null;
    }

    public void add(int value) {
        int level = getLevel();
        Node newNode = new Node(value, level);

        //插入节点的前驱节点
        Node[] update = getPreNode(value, level);

        for (int i = 0; i < level; i++) {
            newNode.next[i] = update[i].next[i];
            update[i].next[i] = newNode.next[i];
        }

        if (level > levelCount) {
            levelCount = level;
        }

        size++;
    }

    public void delete(int value) {

        Node current = get(value);

        if (current == null) {
            return;
        }

        Node[] update = getPreNode(value, levelCount);

        if (current.next[0] != null && current.next[0].value == value) {
            size--;
            for (int i = 0; i < levelCount; i++) {
                if (update[i].next[i] != null && update[i].next[i].value == value) {
                    update[i].next[i] = update[i].next[i].next[i];
                }
            }
        }

    }

    private Node[] getPreNode(int value, int level) {
        Node[] update = new Node[level];
        Node tmp = head;

        for (int i = level - 1; i >= 0; i--) {
            while (tmp.next[i] != null && tmp.next[i].value < value) {
                tmp = tmp.next[i];
            }
            update[i] = tmp;
        }
        return update;
    }

    private int getLevel() {
        int level = 1;
        while (true) {
            int t = (int) (Math.random() * 100);
            if (t % 2 == 0 && level < maxLevel) {
                level++;
            } else {
                break;
            }
        }
        return level;
    }
}


class Node {
    int value;
    int level;
    Node[] next;

    public Node(int value, int level) {
        this.value = value;
        this.level = level;
        this.next = new Node[level];
    }
}
