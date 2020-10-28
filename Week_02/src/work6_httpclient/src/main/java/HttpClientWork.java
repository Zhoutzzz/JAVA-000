import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * @author ztz
 * @description TODO
 * @date 2020/10/27 22:19
 */
public class HttpClientWork {

    public static void main(String[] args) {
        try (CloseableHttpClient client = HttpClients.createDefault();) {
            HttpHost host = new HttpHost("localhost", 8801);
            HttpRequest request = new HttpGet();
            CloseableHttpResponse execute = client.execute(host, request);
            execute.getEntity().writeTo(System.out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
