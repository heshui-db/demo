package com.dongbin.lock;

import com.dongbin.RedisClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.List;
import java.util.UUID;

/**
 * 分布式锁
 */
public class DistributeLock {

    private RedisClient redisClient;

    public DistributeLock(RedisClient redisClient) {
        this.redisClient = redisClient;
    }

    /**
     * 加锁
     * <p>
     * 返回锁的标识 是因为多个线程都有相同的Key
     * 解锁只用Key 会误解锁
     *
     * @param key
     * @param acquireTimeout 获取超时时间
     * @param timeout        锁超时时间
     * @return 锁的标识
     */
    public String lockWithTimeout(String key, long acquireTimeout, long timeout) {
        Jedis jedis = null;
        String identifier = null;
        try {
            jedis = redisClient.getJedis();

            String uuid = UUID.randomUUID().toString();

            String lockKey = getLockKey(key);

            int lockExpire = (int) (timeout / 1000);

            long end = System.currentTimeMillis() + acquireTimeout;

            while (System.currentTimeMillis() < end) {
                if (jedis.setnx(lockKey, uuid) == 1) {
                    jedis.expire(lockKey, lockExpire);
                    identifier = uuid;
                    return identifier;
                }

                if (jedis.ttl(lockKey) == -1) {
                    jedis.expire(lockKey, lockExpire);
                }

                Thread.sleep(100);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return identifier;
    }

    /**
     * @param key
     * @param identifier
     */
    public boolean unLock(String key, String identifier) {

        //未获取到锁的线程解锁
        if (identifier == null) {
            return false;
        }

        Jedis jedis = null;

        String lockKey = getLockKey(key);
        boolean release = false;

        try {
            jedis = redisClient.getJedis();
            while (true) {
                jedis.watch(lockKey);
                String lockValue = jedis.get(lockKey);
                if (identifier.equals(lockValue)) {
                    Transaction transaction = jedis.multi();
                    transaction.del(lockKey);
                    List<Object> exec = transaction.exec();
                    if (exec != null) {
                        release = true;
                        jedis.unwatch();
                        break;
                    }
                }
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

        return release;
    }

    private String getLockKey(String key) {
        return "lock:" + key;
    }
}
