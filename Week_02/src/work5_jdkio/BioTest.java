package work5_jdkio;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author ztz
 * @description TODO
 * @date 2020/10/25 15:57
 */
public class BioTest {


    public static void main(String[] args) throws Exception {
        ServerSocket socket = new ServerSocket(8801);
        while (true) {
            Socket accept = socket.accept();
            service(accept);
        }
    }

    private static void service(Socket socket) throws Exception {
        Thread.sleep(20);
        PrintWriter print = new PrintWriter(socket.getOutputStream());
        print.println("HTTP/1.1 200 OK");
        print.println("Content-Type:text/html;charset=utf-8");
        print.println();
        print.write("hello,nio");
        print.close();
        socket.close();
    }
}
