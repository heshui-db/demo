package com.dongbin.algorithm.db20190204;


/**
 * 动态规划 机器人问题
 * 总共有n个位置,在from位置 走k步到达to,有多少种走法
 */

public class Robot {

    public static void main(String[] args) {
        System.out.println(ways(10, 3, 5, 6));
        System.out.println(dpWays(10, 3, 5, 6));
    }

    public static int ways(int n, int to, int from, int k) {
        return process1(n, to, from, k);
    }

    /**
     * 暴力尝试
     *
     * @param n    总共位置
     * @param to   最终位置
     * @param cur  当前位置
     * @param rest 还剩多少歩
     */
    public static int process1(int n, int to, int cur, int rest) {

        if (rest == 0) {
            return to == cur ? 1 : 0;
        }

        /**
         *  当位置为1时
         */
        if (cur == 1) {
            return process1(n, to, 2, rest - 1);
        }

        /**
         * 当位置为n时
         */
        if (cur == n) {
            return process1(n, to, n - 1, rest - 1);
        }

        /**
         * 中间位置
         */

        return process1(n, to, cur + 1, rest - 1) + process1(n, to, cur - 1, rest - 1);
    }

    /**
     * 动态规划求解
     *
     * @param n
     * @param from
     * @param to
     * @param k
     * @return
     */
    public static int dpWays(int n, int to, int from, int k) {

        int[][] dp = new int[k + 1][n + 1];

        //初始化数据
        dp[0][from] = 1;

        for (int row = 1; row <= k; row++) {
            for (int col = 1; col <= n; col++) {
                if (col == 1) {
                    dp[row][col] = dp[row - 1][2];
                } else if (col == n) {
                    dp[row][col] = dp[row - 1][n - 1];
                } else {
                    dp[row][col] = dp[row - 1][col + 1] + dp[row - 1][col - 1];
                }
            }
        }

        return dp[k][to];
    }
}
