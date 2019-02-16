package com.dongbin.algorithm.db20190216;

/**
 * kmp算法 是否包含有子串
 */
public class KMP {

    public int kmp(String s, String m) {
        if (s == null || m == null || s.length() < m.length()) {
            return -1;
        }

        char[] chars1 = s.toCharArray();
        char[] chars2 = m.toCharArray();

        int[] next = getNext(chars2);

        int i1 = 0, i2 = 0;

        while (i1 < chars1.length && i2 < chars2.length) {
            if (chars1[i1] == chars2[i2]) {
                i1++;
                i2++;
            } else if (next[i2] == -1) {
                i1++;
            } else {
                i2 = next[i2];
            }
        }

        return i2 == chars2.length ? i1 - i2 : -1;
    }

    private int[] getNext(char[] chars2) {
        int[] next = new int[chars2.length];

        next[0] = -1;
        if (chars2.length == 1) {
            return next;
        }

        next[1] = 0;

        int i = 2, cn = 0;

        while (i < chars2.length) {
            if (chars2[i - 1] == chars2[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        KMP kmp = new KMP();

        System.out.println(kmp.kmp("abcabcaaa","abcaa"));
    }
}
