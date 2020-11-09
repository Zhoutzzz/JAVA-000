package java0.conc0303;

import java.util.HashMap;
import java.util.Map;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 * <p>
 * 一个简单的代码参考：
 */
public class Homework03_Join {

    private static int res = 0;
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        Thread t = new Thread(Homework03_Join::sum);
        t.start();
        synchronized (t) {
            try {
                t.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 异步执行 下面方法
//        int result = sum(c); //这是得到的返回值
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + res);

        // 然后退出main线程
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    private static Map<Integer, Integer> map = new HashMap<>();

    private static void sum() {
        res = fibo(42);
    }

    private static int fibo(int a) {
        if (a < 2) {
            return 1;
        }

//        return fibo(a - 1) + fibo(a - 2);
        if (map.containsKey(a)) {
            return map.get(a);
        } else {
            int i = fibo(a - 1) + fibo(a - 2);
            map.put(a, i);
            return i;
        }
    }
}
