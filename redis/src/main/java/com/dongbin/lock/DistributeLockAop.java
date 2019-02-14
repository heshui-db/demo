package com.dongbin.lock;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * 用切面来处理加锁和解锁
 * <p>
 * before 加锁
 * after 解锁
 */

@Aspect
public class DistributeLockAop {

    @Resource
    private DistributeLock distributeLock;

    /**
     * 设置加锁的Key
     */
    private ThreadLocal<String> keyThreadLocal = new ThreadLocal<String>();

    /**
     * 设置加锁后的值
     */
    private ThreadLocal<String> valueThreadLocal = new ThreadLocal<String>();


    @Before("init()")
    public void doBefore(JoinPoint joinPoint) {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        RedisLock redisLock = method.getAnnotation(RedisLock.class);
        keyThreadLocal.set(redisLock.key());
        String value = distributeLock.lockWithTimeout(redisLock.key(), redisLock.acquireTimeout(), redisLock.timeout());
        valueThreadLocal.set(value);
        //如果value为null 表示获取锁失败 to do
    }

    @After("init()")
    public void doAfter(JoinPoint joinPoint) {
        distributeLock.unLock(keyThreadLocal.get(), valueThreadLocal.get());

    }

    @Pointcut("@annotation(RedisLock)")
    public void init() {

    }

}
