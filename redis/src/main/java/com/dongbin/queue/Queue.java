package com.dongbin.queue;

import com.dongbin.RedisClient;
import redis.clients.jedis.Jedis;

import java.util.List;

public class Queue {

    private class Producer {

        private RedisClient redisClient;

        private String queue;

        public Producer(RedisClient redisClient, String queue) {
            this.redisClient = redisClient;
            this.queue = queue;
        }

        public void sendMessage(String msg) {
            Jedis jedis = redisClient.getJedis();
            try {
                jedis.lpush(queue, msg);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }
        }
    }

    private class Consumer implements Runnable {

        private RedisClient redisClient;

        private String queue;

        public Consumer(RedisClient redisClient, String queue) {
            this.redisClient = redisClient;
            this.queue = queue;
        }

        public void run() {
            Jedis jedis = redisClient.getJedis();
            while (true) {
                List<String> messages = jedis.blpop(0, queue);
                System.out.println(messages);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Queue q = new Queue();

        String queue = "queue-test";

        Producer producer = q.new Producer(new RedisClient(), queue);

        Consumer consumer = q.new Consumer(new RedisClient(), queue);

        new Thread(consumer).start();

        Thread.sleep(10000);

        producer.sendMessage("hello 2019");

        Thread.sleep(3000);

        producer.sendMessage("everyday is a new day or nice day");
    }

}
