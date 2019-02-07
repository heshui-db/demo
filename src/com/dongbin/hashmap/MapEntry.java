package com.dongbin.hashmap;

public class MapEntry {

    private int key;

    public MapEntry(int key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        MapEntry entey = (MapEntry) obj;
        return key == entey.getKey();
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    @Override
    public int hashCode() {
        return key % 2 == 0 ? 1 : 2;
    }
}
