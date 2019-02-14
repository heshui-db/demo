package com.dongbin;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisClient {

    private Jedis jedis;


    public RedisClient() {
        JedisPool jedisPool = initialPool();
        jedis = jedisPool.getResource();
    }

    private JedisPool initialPool() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setTestOnBorrow(false);
        poolConfig.setMaxTotal(20);
        poolConfig.setMaxIdle(5);
        return new JedisPool(poolConfig, "127.0.0.1", 56379, 1000, "dongbin");
    }

    public Jedis getJedis() {
        return jedis;
    }

    public void setJedis(Jedis jedis) {
        this.jedis = jedis;
    }

    public static void main(String[] args) {
        RedisClient redisClient = new RedisClient();
        Jedis jedis = redisClient.getJedis();
        try {
            jedis.del("dongbin");
            System.out.println(jedis.exists("dongbin"));
            jedis.set("dongbin", "dongbin1");
            System.out.println(jedis.exists("dongbin"));
        } finally {
            jedis.close();
        }
    }

}
