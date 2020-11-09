package java0.conc0303;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

import static java.util.concurrent.ForkJoinPool.defaultForkJoinWorkerThreadFactory;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 * <p>
 * 一个简单的代码参考：
 */
public class Homework03_ForkJoin {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        ForkJoinPool forkJoinPool = new ForkJoinPool(1, defaultForkJoinWorkerThreadFactory, null, true);
        // 异步执行 下面方法
        ForkJoinTask<Integer> submit = forkJoinPool.submit(Homework03_ForkJoin::sum);
        System.out.println("子线程启动，主线程先执行");
        // 确保  拿到result 并输出
        try {
            System.out.println("异步计算结果为：" + submit.get(1, TimeUnit.MILLISECONDS));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 然后退出main线程
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    private static Map<Integer, Integer> map = new HashMap<>();

    private static int sum() {
        return fibo(42);
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
