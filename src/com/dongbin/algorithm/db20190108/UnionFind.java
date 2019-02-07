package com.dongbin.algorithm.db20190108;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class UnionFind {

    public static class Element<T> {
        private T t;

        public Element(T t) {
            this.t = t;
        }
    }

    public static class UnionFindSet<T> {

        private Map<T, Element<T>> elementMap = new HashMap<>();
        private Map<Element<T>, Element<T>> parentMap = new HashMap<>();
        private Map<Element<T>, Integer> sizeMap = new HashMap<>();

        public void init(List<T> list) {
            list.forEach(v -> {
                Element<T> element = new Element<>(v);
                elementMap.put(v, element);
                parentMap.put(element, element);
                sizeMap.put(element, 1);
            });
        }

        public boolean isSameSet(T a, T b) {
            if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
                return findHead(elementMap.get(a)) == findHead(elementMap.get(b));
            }
            return false;
        }

        public void union(T a, T b) {
            if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
                Element<T> head1 = findHead(elementMap.get(a));
                Element<T> head2 = findHead(elementMap.get(b));
                if (head1 == head2) {
                    return;
                }

                Integer size1 = sizeMap.get(head1);
                Integer size2 = sizeMap.get(head2);

                if (size1 >= size2) {
                    parentMap.put(head2, head1);
                    sizeMap.put(head1, size1 + size2);
                    sizeMap.remove(head2);
                } else {
                    parentMap.put(head1, head2);
                    sizeMap.put(head2, size1 + size2);
                    sizeMap.remove(head1);
                }
            }
        }

        private Element<T> findHead(Element<T> element) {
            Stack<Element<T>> elementStack = new Stack<>();
            while (element != parentMap.get(element)) {
                elementStack.push(element);
                element = parentMap.get(element);
            }
            while (!elementStack.empty()) {
                parentMap.put(elementStack.pop(), element);
            }
            return element;
        }
    }
}
