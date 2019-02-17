package com.dongbin.algorithm.db20190217;

/**
 * 求最大的回文子串
 */
public class Manacher {

    public static void main(String[] args) {
        String s = "abc1234321ab";
        System.out.println(maxBackTextLength(s));
        System.out.println(maxBackTextLength2(s));
    }

    public static int maxBackTextLength(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = toCharArrayString(str);
        int[] p = new int[chars.length];
        int C = -1, R = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < chars.length; i++) {
            p[i] = R > i ? Math.min(p[2 * C - i], R - i) : 1;
            while (i + p[i] < chars.length && i - p[i] > -1) {
                if (chars[i + p[i]] == chars[i - p[i]]) {
                    p[i]++;
                } else {
                    break;
                }
            }

            if (i + p[i] > R) {
                R = i + p[i];
                C = i;
            }

            max = Math.max(p[i], max);
        }

        return max - 1;

    }

    private static char[] toCharArrayString(String str) {
        char[] chars = str.toCharArray();
        char[] res = new char[2 * chars.length + 1];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = i % 2 == 0 ? '#' : chars[index++];
        }
        return res;
    }

    /**
     * 暴力运算
     *
     * @param str
     * @return
     */
    public static int maxBackTextLength2(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = toCharArrayString(str);
        int res = 1, index;
        for (int i = 0; i < chars.length; i++) {
            index = 1;
            while (i + index < chars.length && i - index >= 0) {
                if (chars[i + index] == chars[i - index]) {
                    index++;
                } else {
                    break;
                }
            }

            res = Math.max(res, index);
        }

        return res - 1;
    }
}
