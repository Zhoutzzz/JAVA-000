package java0.conc0303;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * 一个简单的代码参考：
 */
public class Homework03_CountDownLatch {

    private static volatile int res = 0;
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) {
        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        Thread t = new Thread(Homework03_CountDownLatch::sum);

        // 异步执行 下面方法
        t.start();
        System.out.println("子线程启动，主线程先执行");
//        int result = sum(c); //这是得到的返回值
        // 确保  拿到result 并输出
        try {
            countDownLatch.await();
            System.out.println("异步计算结果为："+ res);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 然后退出main线程
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    private static Map<Integer, Integer> map = new HashMap<>();

    private static void sum() {
        try {
            res = fibo(42);
            countDownLatch.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static int fibo(int a) {
        if ( a < 2) {
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
