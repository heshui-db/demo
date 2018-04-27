package chapter04.socket.client.sender;

import chapter04.socket.SocketWrapper;

import java.io.IOException;

/**
 * Created by dongbin on 2018/1/18.
 */
public interface Sendable {

    byte getSendType();

    void sendContent(SocketWrapper socketWrapper) throws IOException;
}
