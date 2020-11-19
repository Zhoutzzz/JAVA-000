import java.io.IOException;
import java.io.InputStream;

public class Demo {

    public static void main(String[] args) {

    }

    public static void getClass(String className, InputStream resource) {
        try {
            HelloClassLoader helloClassLoader = new HelloClassLoader();
            Object hello = helloClassLoader.loadFileByPath(className, resource).getDeclaredConstructor().newInstance();
            System.out.println(hello.getClass().getClassLoader());
            hello.getClass().getDeclaredMethod("hello").invoke(hello);
//            hello.hello();
//            new HelloClassLoader().findClass("Hello").getDeclaredMethod("hello").invoke(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object dynamicLoadClass(String className) throws IOException {
        InputStream resource = Demo.class.getResourceAsStream("Hello.xlass");
        try {
            HelloClassLoader helloClassLoader = new HelloClassLoader();
            Object hello = helloClassLoader.loadFileByPath(className, resource).getDeclaredConstructor().newInstance();
            System.out.println(hello.getClass().getClassLoader());

//            helloClassLoader = null;
            return hello;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resource.close();
        }
        return null;
    }
}
