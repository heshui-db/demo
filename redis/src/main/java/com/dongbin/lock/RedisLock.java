package com.dongbin.lock;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.METHOD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisLock {

    /**
     * 加密的Key
     *
     * @return
     */
    String key();

    long acquireTimeout() default 6000L;

    long timeout() default 6000L;
}
