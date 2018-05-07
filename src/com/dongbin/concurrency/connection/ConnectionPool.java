package com.dongbin.concurrency.connection;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * 模拟连接池
 */
public class ConnectionPool {

    //双向链表创建连接池
    private LinkedList<Connection> pool = new LinkedList<>();

    //初始化连接池
    public ConnectionPool(int initialSize) {
        if (initialSize > 0) {
            for (int i = 0; i < initialSize; i++) {
                pool.addLast(ConnectionDriver.createConnection());
            }
        }
    }

    //释放连接
    public void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (pool) {
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    //获取连接
    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pool) {
            if (mills <= 0) {
                while (pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            }

            long future = System.currentTimeMillis() + mills;
            long remaining = mills;

            while (pool.isEmpty() && remaining > 0) {
                pool.wait(remaining);
                remaining = future - System.currentTimeMillis();
            }

            Connection result = null;

            if (!pool.isEmpty()) {
                result = pool.removeFirst();
            }

            return result;

        }
    }
}
