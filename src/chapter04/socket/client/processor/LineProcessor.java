package chapter04.socket.client.processor;

import chapter04.socket.Commons;
import chapter04.socket.SocketWrapper;
import chapter04.socket.client.sender.Sendable;

import java.io.IOException;

/**
 * Created by dongbin on 2018/1/18.
 */
public class LineProcessor {

    private String[] tokens;

    private Sendable sendable;

    public LineProcessor(String line) throws Exception {
        line = preLine(line);
        if (line.trim().length() == 0) {
            throw new RuntimeException("line is null");
        }

        tokens = line.trim().split("\\s+");
        String firstToken = tokens[0];
        Class<?> clazz = Commons.findSendableClassByOrder(firstToken);
        try {
            sendable = (Sendable) clazz.getConstructor(String[].class)
                    .newInstance(new Object[]{tokens});
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private String preLine(String line) {
        if (line == null) return "";
        if (line.startsWith(">")) return line.substring(1);
        return line;
    }

    public void sendContentBySocket(SocketWrapper socketWrapper) throws IOException {
        if(sendable != null && sendable.getSendType() > 0) {
            socketWrapper.write(sendable.getSendType());
            sendable.sendContent(socketWrapper);
        }
    }
}
