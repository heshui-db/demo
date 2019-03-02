package com.dongbin;

import com.dongbin.publish.MessagePublisherRedisImpl;
import com.dongbin.subscribe.MessageConsumerRedisImpl;

public class PublishSubscribeTest {

    public static void main(String[] args) throws InterruptedException {
        final String[] channels = {"channel-1", "channel-2"}111;

        MessagePublisherRedisImpl messagePublisherRedis = new MessagePublisherRedisImpl(new RedisClient(), channels);

        Thread thread = new Thread(new Runnable() {
            public void run() {
                MessageConsumerRedisImpl messageConsumerRedis = new MessageConsumerRedisImpl(new RedisClient(), channels);
                //订阅
                messageConsumerRedis.subscribe();
            }
        });

        thread.start();


        Thread.sleep(10000);
        messagePublisherRedis.sendMessage("hello 2019");


        messagePublisherRedis.sendMessage("everyday is a new day");
    }


}
