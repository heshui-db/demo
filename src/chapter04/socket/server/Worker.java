package chapter04.socket.server;

import chapter04.socket.Commons;
import chapter04.socket.SocketWrapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static chapter04.socket.Commons.*;

/**
 * Created by dongbin on 2018/1/18.
 */
public class Worker extends Thread {

    private SocketWrapper socketWrapper;

    private String name;

    public Worker(SocketWrapper socketWrapper, String name) {
        this.socketWrapper = socketWrapper;
        this.name = name;
        System.out.println("我是线程：" + name + " , 开始启动接收客户端传来数据......");
        this.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (this.isInterrupted()) break;
                byte type = socketWrapper.readByte();
                switch (type) {
                    case SEND_MESSAGE:
                        processMessage();
                        break;
                    case SEND_FILE:
                        processFile();
                        break;
                    case SEND_B_FILE:
                        uploadFileContent(null);
                        break;
                    case GET_FILE:
                        downloadFile();
                        break;

                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 处理客户端传来的流
     *
     * @throws IOException
     */
    private void processMessage() throws IOException {
        int length = socketWrapper.readInt();
        byte[] message = new byte[length];
        socketWrapper.read(message);
        System.out.println("线程：" + name + " 接受到来自客户端传来message信息：" + new String(message, DEFAULT_MESSAGE_CHARSET));
    }

    /**
     * 处理客户端传来文件
     *
     * @throws IOException
     */
    private void processFile() throws IOException {
        String charset = Commons.DEFAULT_MESSAGE_CHARSET;
        System.out.println("线程：" + name + "接受来源客户端发送文件，字符集为：" + charset);
        uploadFileContent(charset);
    }

    private void uploadFileContent(String charset) throws IOException {
        FileOutputStream outputStream = null;
        try {
            //获取文件名长度以及文件名
            short length = socketWrapper.readShort();
            byte[] bytes = new byte[length];
            socketWrapper.readFull(bytes);
            String fileName = new String(bytes, DEFAULT_MESSAGE_CHARSET);
            System.out.println("线程：" + name + "接受来源客户端发送文件，来源文件名为：" + fileName);
            String path = SERVER_SAVE_BASE_PATH + fileName;

            File file = new File(path);
            if (file.exists()) {
                throw new RuntimeException(path);
            }

            socketWrapper.write(1);
            outputStream = new FileOutputStream(file);

            long fileLength = socketWrapper.readLong();
            System.out.println("线程：" + name + "接受来源客户端发送文件，文件长度为：" + fileLength);

            bytes = new byte[DEFAULT_BUFFER_LENGTH];
            int allLength = 0, i = 0;
            while (allLength < fileLength) {
                int len = socketWrapper.read(bytes);
                allLength += len;
                outputStream.write(bytes, 0, len);
                if (charset != null) {
                    System.out.println(new String(bytes, charset));
                }

                if (i++ % 1024 == 0) {
                    System.out.println(".");
                }
            }

            System.out.println("\n文件接收完毕并保存，向客户端发送确认信息 , 实际接受内容长度为：" + fileLength);
            socketWrapper.write(1);
        } catch (Exception e) {
            System.out.println("\n文件接收失败，向客户端发送错误消息。");
            socketWrapper.write(-2);
        } finally {
            closeStream(outputStream);
        }
    }

    /**
     * 下载文件
     *
     * @throws IOException
     */
    private void downloadFile() throws Exception {
        short fileNameLength = socketWrapper.readShort();
        byte[] fileNameBytes = new byte[fileNameLength];
        socketWrapper.read(fileNameBytes);
        String fileName = new String(fileNameBytes, DEFAULT_MESSAGE_CHARSET);
        System.out.println("线程：" + name + "接受客户端下载文件，下载文件名为：" + fileName);

        String path = SERVER_SAVE_BASE_PATH + fileName;
        File file = new File(path);
        if (file.exists()) {
            socketWrapper.write(1);
        } else {
            socketWrapper.write(-1);
            throw new RuntimeException("文件不存在");
        }

        socketWrapper.write(file.length());
        socketWrapper.writeFile(path);
    }

}
