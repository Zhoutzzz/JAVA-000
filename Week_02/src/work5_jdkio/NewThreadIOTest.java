package work5_jdkio;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ztz
 * @description TODO
 * @date 2020/10/25 16:12
 */
public class NewThreadIOTest {
    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {
        ServerSocket socket = new ServerSocket(8802);
        while (true) {
            Socket accept = socket.accept();
            new Thread(() -> {
                count.incrementAndGet();
                try {
                    service(accept);
                    System.out.println(count);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
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
