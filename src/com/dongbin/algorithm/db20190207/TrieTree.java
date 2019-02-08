package com.dongbin.algorithm.db20190207;

public class TrieTree {

    public static class Node {

        public int pass;
        public int end;

        public Node[] nexts;

        public Node() {
            pass = 0;
            end = 0;
            nexts = new Node[26];//26个字母
        }
    }

    public static class Trie {

        private Node root;//根节点

        public Trie() {
            root = new Node();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }

            char[] chars = word.toCharArray();

            root.pass++;
            Node cur = root;

            int index;

            for (char c : chars) {
                index = c - 'a';
                if (cur.nexts[index] == null) {
                    cur.nexts[index] = new Node();
                }
                cur = cur.nexts[index];
                cur.pass++;
            }
            cur.end++;
        }

        /**
         * 删除
         *
         * @param word
         */
        public void delete(String word) {
            if (search(word) > 0) {
                root.pass--;

                char[] chars = word.toCharArray();

                Node cur = root;
                for (char c : chars) {
                    int index = c - 'a';
                    cur.nexts[index].pass--;
                    if (cur.nexts[index].pass <= 0) {
                        cur.nexts[index] = null;
                        return;
                    }
                    cur = cur.nexts[index];
                }
                cur.end--;
            }
        }

        /**
         * 查找 添加了几次
         *
         * @param word
         * @return
         */
        public int search(String word) {
            if (word == null) {
                return 0;
            }

            char[] chars = word.toCharArray();

            Node cur = root;
            for (char c : chars) {
                if (cur.nexts[c - 'a'] == null) {
                    return 0;
                }
                cur = cur.nexts[c - 'a'];
            }
            return cur.end;
        }


        /**
         * 查询以pre开头的次数
         *
         * @param pre
         * @return
         */
        public int prefixNumber(String pre) {
            if (pre == null) {
                return 0;
            }

            char[] chars = pre.toCharArray();

            Node cur = root;
            int index;
            for (char c : chars) {

                index = c - 'a';

                if (cur.nexts[index] == null) {
                    return 0;
                }

                cur = cur.nexts[index];
            }

            return cur.pass;
        }

    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        System.out.println(trie.search("dongbin"));
        trie.insert("dongbina");
        trie.insert("dongbinb");
        trie.insert("dongbinc");
        trie.insert("dongbind");
        System.out.println(trie.search("dongbina"));
        System.out.println(trie.prefixNumber("dongbin"));
        trie.delete("dongbina");
        System.out.println(trie.search("dongbina"));
        System.out.println(trie.prefixNumber("dongbin"));
    }
}
