package com.dongbin.algorithm.db20190204;

public class Money {

    public static void main(String[] args) {
        int[] arr = {2, 5, 1, 10};
        int aim = 100;
        System.out.println(ways(arr, aim));
    }

    public static int ways(int[] coins, int aim) {
        return process(coins, 0, aim);
    }

    public static int process(int[] coins, int L, int rest) {
        if (L == coins.length) {
            return rest == 0 ? 1 : 0;
        }

        int ways = 0;
        for (int k = 0; k * coins[L] <= rest; k++) {
            ways += process(coins, L + 1, rest - k * coins[L]);
        }
        return ways;
    }

//    public static int dpWays(int[] coins, int aim) {
//
//    }
}
