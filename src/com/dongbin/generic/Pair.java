package com.dongbin.generic;

public class Pair<T> {
    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Object get(){
        return value;
    }
}
