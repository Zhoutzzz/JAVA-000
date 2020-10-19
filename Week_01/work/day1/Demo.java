import java.net.URL;

public class Demo {

    public static void main(String[] args) {
        URL resource = Demo.class.getResource("Hello.xlass");
        getClass("Hello", resource.getFile().substring(1));
    }

    public static void getClass(String className, String path) {
        try {
            // 获取当前类ClassLoader
            HelloClassLoader helloClassLoader = new HelloClassLoader();
            //通过classLoader加载Hello类
            Object hello = helloClassLoader.loadFileByPath(className, path).getDeclaredConstructor().newInstance();
            System.out.println(hello.getClass().getClassLoader());

            //执行hello方法
            hello.getClass().getDeclaredMethod("hello").invoke(hello);

            //将当前类加载器和当前类置空
//            helloClassLoader = null;
//            hello = null;
            //建议系统进行GC
            System.gc();
//            hello.hello();
//            new HelloClassLoader().findClass("Hello").getDeclaredMethod("hello").invoke(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
