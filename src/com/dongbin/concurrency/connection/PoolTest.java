package com.dongbin.concurrency.connection;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class PoolTest {

    static ConnectionPool pool = new ConnectionPool(10);

    static CountDownLatch start = new CountDownLatch(1);

    static CountDownLatch end;

    public static void main(String[] args) throws InterruptedException {
        int threadCount = 20;
        end = new CountDownLatch(threadCount);

        int count = 20;

        AtomicInteger got = new AtomicInteger();

        AtomicInteger nogot = new AtomicInteger();

        for (int i = 0; i < threadCount; i++) {
            Thread thread = new Thread(new ConnectRunner(count, got, nogot), "ConnectThread_" + i);
            thread.start();
        }

        start.countDown();

        end.await();

        System.out.println("total invoke:" + threadCount * count);
        System.out.println("get connection:" + got);
        System.out.println("no got connect:" + nogot);

    }

    static class ConnectRunner implements Runnable {

        private int count;

        private AtomicInteger got;

        private AtomicInteger nogot;


        public ConnectRunner(int count, AtomicInteger got, AtomicInteger nogot) {
            this.count = count;
            this.got = got;
            this.nogot = nogot;
        }

        @Override
        public void run() {

            try {
                start.await();
            } catch (Exception e) {

            }

            while (count > 0) {
                try {
                    Connection connection = pool.fetchConnection(1000);

                    if (connection != null) {
                        try {
                            connection.createStatement();
                            connection.commit();
                        } finally {
                            pool.releaseConnection(connection);
                            got.incrementAndGet();
                        }

                    } else {
                        nogot.incrementAndGet();
                    }
                } catch (Exception e) {

                } finally {
                    count--;
                }
            }

            end.countDown();

        }
    }
}
