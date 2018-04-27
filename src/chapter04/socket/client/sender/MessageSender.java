package chapter04.socket.client.sender;

import chapter04.socket.SocketWrapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static chapter04.socket.Commons.*;

/**
 * Created by dongbin on 2018/1/18.
 */
public class MessageSender implements Sendable {
    private String message;//普通的message消息

    private byte[] messageBytes;//消息发送时使用

    private int length = 0;

    public MessageSender(String[] tokens) throws UnsupportedEncodingException {
        if (tokens.length >= 2) {
            message = tokens[1];
            this.messageBytes = message.getBytes(DEFAULT_MESSAGE_CHARSET);
            this.length = messageBytes.length;
        } else {
            throw new RuntimeException("请在sendMsg后面添加内容。");
        }
    }

    /**
     * 发送内容处理
     *
     * @throws IOException
     */
    @Override
    public void sendContent(SocketWrapper socketWrapper) throws IOException {
        System.out.println("我此时想服务器端发送消息：" + message);
        socketWrapper.write(length);
        socketWrapper.write(messageBytes);
        System.out.println("发送消息完毕。");
    }

    @Override
    public byte getSendType() {
        return SEND_MESSAGE;
    }

}