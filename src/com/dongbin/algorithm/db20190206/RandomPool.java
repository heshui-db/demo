package com.dongbin.algorithm.db20190206;

import java.util.HashMap;
import java.util.Map;

/**
 * 随机池 等概率返回Key
 */
public class RandomPool {

    public static class Pool<T> {
        private Map<T, Integer> keyIndexMap;
        private Map<Integer, T> indexKeyMap;
        private int size = 0;

        public Pool() {
            this.keyIndexMap = new HashMap<>();
            this.indexKeyMap = new HashMap<>();
        }

        public void insert(T t) {
            //如果不包含
            if (!keyIndexMap.containsKey(t)) {
                keyIndexMap.put(t, this.size);
                this.indexKeyMap.put(this.size, t);
                this.size++;
            }
        }

        public void delete(T t) {
            if (keyIndexMap.containsKey(t)) {
                int deleteIndex = this.keyIndexMap.get(t);
                int lastIndex = --this.size;

                //获取最后一个值
                T lastKey = this.indexKeyMap.get(lastIndex);

                this.keyIndexMap.put(lastKey, deleteIndex);
                this.indexKeyMap.put(deleteIndex, lastKey);
                this.keyIndexMap.remove(t);
                this.indexKeyMap.remove(lastIndex);
            }
        }

        public T getRandom() {
            if (this.size == 0) {
                return null;
            }

            int randomIndex = (int) (Math.random() * this.size);

            return indexKeyMap.get(randomIndex);
        }
    }

    public static void main(String[] args) {
        Pool<String> randomPool = new Pool<>();
        randomPool.insert("dongbin");
        randomPool.insert("dongbin1");
        randomPool.insert("dongbin3");
        System.out.println(randomPool.getRandom());
        System.out.println(randomPool.getRandom());
        System.out.println(randomPool.getRandom());
        System.out.println(randomPool.getRandom());
    }
}
