package com.dongbin.algorithm.db20190219;

/**
 * String convert Integer
 */
public class StringToInteger {

    public static Integer stringToInteger(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }

        char[] chars = str.toCharArray();

        if (!isValid(chars)) {
            return null;
        }


        boolean negative = chars[0] == '-';

        int minq = Integer.MIN_VALUE / 10;
        int minr = Integer.MIN_VALUE % 10;

        int res = 0;
        int cur;
        for (int i = negative ? 1 : 0; i < chars.length; i++) {
            cur = '0' - chars[i];
            if (res < minq || (res == minq && cur < minr)) {
                return null;
            }
            res = res * 10 + cur;
        }

        if (!negative && res == Integer.MIN_VALUE) {
            return null;
        }

        return negative ? res : -res;

    }

    private static boolean isValid(char[] chars) {

        //先判断首位
        if (chars[0] == '-' && (chars.length == 1 || chars[1] == 0)) {
            return false;
        }

        if (chars[0] != '-' && (chars[0] < '0' || chars[0] > '9')) {
            return false;
        }

        if (chars[0] == '0' && chars.length > 1) {
            return false;
        }

        for (int i = 1; i < chars.length; i++) {
            if (chars[i] < '0' || chars[i] > '9') {
                return false;
            }
        }
        return true;
    }
}
