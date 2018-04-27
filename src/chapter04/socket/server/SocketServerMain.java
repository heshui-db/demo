package chapter04.socket.server;

import chapter04.socket.SocketWrapper;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

import static chapter04.socket.Commons.*;

/**
 * Created by dongbin on 2018/1/18.
 */
public class SocketServerMain {

    private final static List<Worker> WORKERS = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        initPath();
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("端口已经打开9999，开始准备接受数据.....");
        try {
            int index = 1;
            while (true) {
                SocketWrapper socketWrapper = new SocketWrapper(serverSocket.accept());
                WORKERS.add(new Worker(socketWrapper, "socket_thread_" + index++));
            }
        } finally {
            serverSocket.close();
            interruptWorkers();
        }
    }

    private static void interruptWorkers() {
        for (Worker worker : WORKERS) {
            worker.interrupt();
        }
    }

    private static void initPath() {
        File file = new File(SERVER_SAVE_BASE_PATH);
        if (!file.exists()) {
            boolean success = file.mkdir();
            if (!success) {
                throw new RuntimeException("无法创建目录：" + SERVER_SAVE_BASE_PATH);
            }
        }
    }
}
