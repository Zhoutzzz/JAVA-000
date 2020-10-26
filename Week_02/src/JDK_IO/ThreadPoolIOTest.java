import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author ztz
 * @description TODO
 * @date 2020/10/25 16:12
 */
public class ThreadPoolIOTest {

    public static void main(String[] args) throws Exception {
        ServerSocket socket = new ServerSocket(8803);
        Executor executor = Executors.newFixedThreadPool(96);
        while (true) {
            Socket accept = socket.accept();
            executor.execute(() -> service(accept));
        }
    }

    private static void service(Socket socket) {
        try {
            Thread.sleep(20);
            PrintWriter print = new PrintWriter(socket.getOutputStream());
            print.println("HTTP/1.1 200 OK");
            print.println("Content-Type:text/html;charset=utf-8");
            print.println();
            print.write("hello,nio");
            print.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
