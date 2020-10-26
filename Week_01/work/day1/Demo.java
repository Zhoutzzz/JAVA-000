import java.net.URL;

public class Demo {

    public static void main(String[] args) {
        URL resource = Demo.class.getResource("Hello.xlass");
        getClass("Hello", resource.getFile().substring(1));
    }

    public static void getClass(String className, String path) {
        try {
            HelloClassLoader helloClassLoader = new HelloClassLoader();
            Object hello = helloClassLoader.loadFileByPath(className, path).getDeclaredConstructor().newInstance();
            System.out.println(hello.getClass().getClassLoader());

            hello.getClass().getDeclaredMethod("hello").invoke(hello);
//            hello.hello();
//            new HelloClassLoader().findClass("Hello").getDeclaredMethod("hello").invoke(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
