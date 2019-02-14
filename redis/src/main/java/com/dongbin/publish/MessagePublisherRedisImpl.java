package com.dongbin.publish;

import com.dongbin.RedisClient;
import redis.clients.jedis.Jedis;

public class MessagePublisherRedisImpl implements MessagePublisher {

    //redis client端
    private RedisClient redisClient;

    //多个管道
    private String[] channels;

    public MessagePublisherRedisImpl(RedisClient redisClient) {
        this.redisClient = redisClient;
    }

    public MessagePublisherRedisImpl(RedisClient redisClient, String[] channels) {
        this.redisClient = redisClient;
        this.channels = channels;
    }

    public void setChannels(String[] channels) {
        this.channels = channels;
    }

    public boolean sendMessage(String message) {

        Jedis jedis = null;

        try {
            if (channels != null && channels.length > 0) {
                jedis = redisClient.getJedis();
                for (String channel : channels) {
                    jedis.publish(channel, message);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }
}
