import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class LoadJar {

    public static void main(String[] args) throws Exception {

        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{new URL("file:hello/hello.xar")});
        Class<?> demo = urlClassLoader.loadClass("Demo");
        Object demoInstance = demo.newInstance();
        Method dynamicLoadClass = demo.getMethod("dynamicLoadClass", String.class);
        for (int i = 0; i < 5; i++) {
            Object hello = dynamicLoadClass.invoke(demoInstance, "Hello");
            System.out.println(hello);
        }
        urlClassLoader.close();
        System.gc();
    }
}
