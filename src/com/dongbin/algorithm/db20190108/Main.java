package com.dongbin.algorithm.db20190108;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println(Integer.parseInt("a", 16));
    }

    private static class Edge {

        private int person1;
        private int person2;

        private int clash;

        public int getPerson1() {
            return person1;
        }

        public void setPerson1(int person1) {
            this.person1 = person1;
        }

        public int getPerson2() {
            return person2;
        }

        public void setPerson2(int person2) {
            this.person2 = person2;
        }

        public int getClash() {
            return clash;
        }

        public void setClash(int clash) {
            this.clash = clash;
        }
    }

    /**
     * @param n     监狱数量
     * @param edges 关系值
     */
    public static int gyfr(int n, List<Edge> edges) {
        if (edges == null || edges.size() == 0) {
            return 0;
        }
        edges.sort((o1, o2) -> o1.getClash() > o2.getClash() ? 1 : -1);
        return 0;
    }

}
