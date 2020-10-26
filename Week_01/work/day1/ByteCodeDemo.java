import java.util.*;
import java.util.concurrent.*;

/**
 * @author ztz
 * @description TODO
 * @date 2020/10/18 16:39
 */
public class ByteCodeDemo {
    private final Set<Integer> set = new HashSet<>();

    public static void main(String[] args) {
        ByteCodeDemo byteCodeDemo = new ByteCodeDemo();
        Random random = new Random();
        for (int i = 0; i < 10; i++) new Thread(() -> byteCodeDemo.add(random.nextInt())).start();
        System.out.println("add 10 element to " + byteCodeDemo.set);
        //        String s = new String("1");
//        s.intern();
//        String s2 = "1";
//        System.out.println(s == s2);
//
//        String s3 = new String("1") + new String("1");
//        s3.intern();
//        String s4 = "11";
//        System.out.println(s3 == s4);
    }

    public synchronized void add(Integer integer) {
        set.add(integer);
    }

    private synchronized void remove(Integer i) {
        set.remove(i);
    }

    private void test() {
        int a = 1, b = 2, c = 3, d = 4;
        for (int i = 0; i < 100; i++) {
            a += b;
            c *= d;
            if (a == 50) {
                d /= b;
                d -= a;
            }
        }
    }
}
