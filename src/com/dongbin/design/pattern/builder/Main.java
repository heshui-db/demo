package com.dongbin.design.pattern.builder;

public class Main {

    public static void main(String[] args) {

        Parlour parlour = new Parlour().toBuilder()
                .sofa("db")
                .TV("12")
                .wall("34")
                .build();

        System.out.println(parlour);
    }
}
