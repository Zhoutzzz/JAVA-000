import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class HelloClassLoader extends ClassLoader {
    byte[] res = null;

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if (res == null) {
            throw new ClassNotFoundException("can't read class file by path");
        }
        return defineClass(name, res, 0, res.length);
    }

    public Class<?> loadFileByPath(String className, InputStream data) throws ClassNotFoundException {
        try  {
            res = new byte[data.available()];
            data.read(res);
            for (int i = 0; i < res.length; i++) {
                res[i] = (byte) (255 - res[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                data.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return findClass(className);
    }
}
