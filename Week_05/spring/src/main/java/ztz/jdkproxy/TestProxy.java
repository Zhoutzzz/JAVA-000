package ztz.jdkproxy;

import java.lang.reflect.*;

public class TestProxy {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        //方法一
        Class<?> proxyClass = Proxy.getProxyClass(Galigeigei.class.getClassLoader(), Galigeigei.class);
        Constructor<?> constructor = proxyClass.getConstructor(InvocationHandler.class);
        Galigeigei o = (Galigeigei) constructor.newInstance(new GaliProxy(new GaliReal()));
        o.printGaligeigei();

        System.out.println("---------------------------------------------------华丽分割线---------------------------------------------------");

        //方法2
        Galigeigei proxy = (Galigeigei) Proxy.newProxyInstance(Galigeigei.class.getClassLoader(), new Class[]{Galigeigei.class}, new GaliProxy(new GaliReal2()));
        proxy.printGaligeigei();
    }

}
