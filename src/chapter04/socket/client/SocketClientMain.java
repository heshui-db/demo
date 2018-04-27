package chapter04.socket.client;

import chapter04.socket.SocketWrapper;
import chapter04.socket.client.processor.LineProcessor;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketException;
import java.util.Scanner;

/**
 * Created by dongbin on 2018/1/18.
 */
public class SocketClientMain {

    public static void main(String[] args) throws IOException{
        Scanner scanner = new Scanner(System.in);
        SocketWrapper socketWrapper = new SocketWrapper("localhost" , 9999);
        try {
            System.out.println("已经连接上服务器端，现在可以输入数据开始通信了.....\n>");
            String line = scanner.nextLine();
            while(!"bye".equals(line)) {
                if(line != null) {
                    try {
                        LineProcessor processor = new LineProcessor(line);
                        processor.sendContentBySocket(socketWrapper);
                    }catch(RuntimeException e) {
                        System.out.println(e.getMessage());
                    }catch(FileNotFoundException e) {
                        System.out.println(e.getMessage());
                    }catch(SocketException e) {
                        System.out.println("Socket异常： " + e.getMessage()  + "，程序将与服务器断开链接....");
                        break;
                    }catch(Exception e) {
                        e.printStackTrace();
                        System.out.println("与线上服务器断开链接！"+e.getMessage());
                        break;
                    }
                }
                System.out.println(">");
                line = scanner.nextLine();
            }
        }finally {
            socketWrapper.close();
        }
    }
}
