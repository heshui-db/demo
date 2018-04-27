package chapter04.socket.client.sender;

import java.io.IOException;

import static chapter04.socket.Commons.SEND_FILE;

/**
 * Created by dongbin on 2018/1/18.
 */
public class FileSender extends BFileSender {

    public FileSender(String[] tokens) throws IOException {
        super(tokens);
    }

    @Override
    public byte getSendType() {
        return SEND_FILE;
    }
}
