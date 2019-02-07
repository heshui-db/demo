package com.dongbin.algorithm.db20190106;

public class Main {

    public static void main(String[] args) {

    }

    //反转单向链表
    public static Node reverseNode(Node head) {
        Node pre = null, next;

        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static DoubleNode reverseDouleNode(DoubleNode head) {
        DoubleNode pre = null, next;

        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;

            pre = head;
            head = next;
        }

        return pre;
    }


    /**
     * 反转单链表
     *
     * @param head
     * @return
     */
    public static Node reverseNode1(Node head) {
        Node pre = null, next;

        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }

        return pre;
    }

    //反转双链表
    public static DoubleNode reverseDouble2(DoubleNode head) {

        DoubleNode pre = null, next;

        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }
}
