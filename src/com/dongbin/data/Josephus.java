package com.dongbin.data;

/**
 * Josephus问题
 */
public class Josephus {


    public Node josephusKill(Node head, int m) {
        if (head == null || head.next == null || m < 1) {
            return head;
        }

        Node last = head;
        while (last.next != head) {
            last = last.next;
        }

        int count = 0;

        while (last != head) {
            if (++count == m) {
                count = 0;
                last.next = head.next;
            } else {
                last = last.next;
            }
            head = last.next;
        }

        return head;
    }
}
