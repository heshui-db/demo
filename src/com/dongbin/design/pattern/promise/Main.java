package com.dongbin.design.pattern.promise;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {

        Executor executor = Executors.newFixedThreadPool(2);

        Promise<List<String>> promise1 = new Promise<>();
        promise1.fulfillInAsync(Main::getResult1, executor)
                .thenAccept(list -> list.forEach(System.out::println));

        Promise<Integer> promise2 = new Promise<>();

        promise2.fulfillInAsync(Main::getResult2, executor)
                .onError(throwable -> {
                    throwable.printStackTrace();
                    System.out.println(throwable.getMessage());
                })
                .thenAccept(System.out::println);

    }

    private static List<String> getResult1() {
        List<String> result = new ArrayList<>();

        result.add("dong");
        result.add("bin");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static int getResult2() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("test exception");
    }


}
