package com.dongbin.design.pattern.builder;

public interface Buildable<B> {
    B toBuilder();

    interface Builder<T extends Builder<T, M>, M extends Buildable<?>> {
        M build();

        T from(M in);
    }
}
