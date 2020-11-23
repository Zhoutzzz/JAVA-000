package demo;


import net.bytebuddy.implementation.bind.annotation.Super;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.util.concurrent.Callable;

public class AOPInterceptor {
    public static String interceptor(@SuperCall Callable<String> target) {
        System.out.println("--------------------------------这是拦截方法， 在执行真实方法之前就会被调用打印这段话--------------------------------");
        try {
            String test = target.call();
            System.out.println(test);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("--------------------------------拦截方法执行结束， 在执行真实方法之后打印这段话--------------------------");
        return "this is bytebuddy's proxy";
    }
}