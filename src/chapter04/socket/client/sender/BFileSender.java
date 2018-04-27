package chapter04.socket.client.sender;

import chapter04.socket.SocketWrapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static chapter04.socket.Commons.DEFAULT_MESSAGE_CHARSET;
import static chapter04.socket.Commons.SEND_B_FILE;

/**
 * 二进制文件发送类
 */
public class BFileSender implements Sendable {

    protected File file;

    protected long fileLength;

    protected int minLength = 2;

    public BFileSender(String[] tokens) throws IOException {
        if (tokens.length >= minLength) {
            file = new File(tokens[1]);
            if (!file.exists()) {
                throw new FileNotFoundException("文件" + tokens[1] + "未找到");
            }
            this.fileLength = file.length();
        } else {
            throw new RuntimeException("消息格式有误，请使用help命令查看输入格式。");
        }
    }

    @Override
    public byte getSendType() {
        return SEND_B_FILE;
    }

    @Override
    public void sendContent(SocketWrapper socketWrapper) throws IOException {
        System.out.println("我此时向服务器端发送二进制文件，文件大小为：" + this.fileLength);
        sendCharset(socketWrapper);
        //获取文件名
        byte[] fileNameBytes = file.getName().getBytes(DEFAULT_MESSAGE_CHARSET);
        socketWrapper.write((short) fileNameBytes.length);
        socketWrapper.write(fileNameBytes);
        int status = socketWrapper.readInt();
        if (status != 1) {
            processErrorStatus(status);
        }
        socketWrapper.write(this.fileLength);
        socketWrapper.writeFile(file.getPath());
        status = socketWrapper.readInt();
        if (status != 1) {
            processErrorStatus(status);
        } else {
            System.out.println("文件已发送");
        }
    }

    private void processErrorStatus(int status) throws IOException {
        if (status == -1) {
            throw new RuntimeException("服务器端保存失败，由于服务器端已经存在该文件导致..");
        } else {
            throw new IOException("服务器端保存失败，不确定具体原因，程序将结束运行....");
        }
    }

    protected void sendCharset(SocketWrapper socketWrapper) throws IOException {
        /*你可以继承哦*/
    }
}
