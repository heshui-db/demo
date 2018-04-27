package chapter04.socket.client.sender;

import chapter04.socket.SocketWrapper;

import java.io.IOException;

import static chapter04.socket.Commons.*;


/**
 * Created by dongbin on 2018/1/18.
 */
public class DefaultSender implements Sendable {

    public DefaultSender(String[] tokens) {
        String firstToken = tokens[0];
        if (HELP_STR.equalsIgnoreCase(firstToken)) {//帮助
            System.out.println(HELP_SHOW);
        } else if (EXIT_STR.equalsIgnoreCase(firstToken)) {//退出
            System.exit(0);
        } else {
            throw new RuntimeException(ERROR_MESSAGE_FORMAT);
        }
    }

    @Override
    public byte getSendType() {
        return 0;
    }

    @Override
    public void sendContent(SocketWrapper socketWrapper) throws IOException {

    }
}
