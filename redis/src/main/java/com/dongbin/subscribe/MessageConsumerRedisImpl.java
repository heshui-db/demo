package com.dongbin.subscribe;

import com.dongbin.RedisClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class MessageConsumerRedisImpl implements MessageConsumer {

    private RedisClient redisClient;

    private String[] channels;

    public MessageConsumerRedisImpl(RedisClient redisClient, String[] channels) {
        this.redisClient = redisClient;
        this.channels = channels;
    }

    public MessageConsumerRedisImpl(RedisClient redisClient) {
        this.redisClient = redisClient;
    }

    public void setChannels(String[] channels) {
        this.channels = channels;
    }

    public void subscribe() {
        Jedis jedis = null;

        try {
            if (channels != null && channels.length > 0) {
                jedis = redisClient.getJedis();

                jedis.subscribe(new JedisPubSub() {
                    @Override
                    public void onMessage(String channel, String message) {
                        System.out.println("channel:" + channel + ",message:" + message);
                        accept(message);
                    }
                }, channels);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public void accept(String message) {
        System.out.println("accept message:" + message);
    }
}
