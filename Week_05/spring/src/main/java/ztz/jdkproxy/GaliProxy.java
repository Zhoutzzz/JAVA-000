package ztz.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class GaliProxy implements InvocationHandler {

    Galigeigei galiReal;

    public GaliProxy(Galigeigei galigeigei) {
        this.galiReal = galigeigei;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("打印之前，代理说了句：yo！");
        Object invoke = method.invoke(galiReal);
        System.out.println("打印之后，代理说了句：skr！");
        return invoke;
    }
}
