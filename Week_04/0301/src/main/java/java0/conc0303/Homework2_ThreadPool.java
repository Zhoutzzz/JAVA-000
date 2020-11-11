package java0.conc0303;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * 一个简单的代码参考：
 */
public class Homework03_ThreadPool {
    
    public static void main(String[] args) {

        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        Callable<Integer> callable = () -> {
            System.out.println("子线程开始");
            return sum();
        };
        ExecutorService pool = Executors.newSingleThreadExecutor();

        Future<Integer> submit = pool.submit(callable);
        // 异步执行 下面方法
//        int result = sum(c); //这是得到的返回值

        // 确保  拿到result 并输出
        try {
            System.out.println("异步计算结果为："+submit.get(3, TimeUnit.SECONDS));
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        pool.shutdown();
        // 然后退出main线程
    }

    private static Map<Integer, Integer> map = new HashMap<>();

    private static int sum() {
        return fibo(42);
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
