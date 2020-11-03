package gateway;

import gateway.inbound.ZGatewayInboundServer;
import org.ho.yaml.Yaml;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author ztz
 * @description TODO
 * @date 2020/10/30 17:03
 */
public class ZGatewayApplication {

    public final static String GATEWAY_NAME = "NIOGateway";
    public final static String GATEWAY_VERSION = "1.0.0";

    public static void main(String[] args) throws IOException {
        int listenPort;
        String proxyAddr;

        InputStream resourceAsStream = Objects.requireNonNull(ZGatewayApplication.class.getClassLoader().getResourceAsStream("gateway.yml"));
        Map proxyConfig = Yaml.loadType(new BufferedInputStream(resourceAsStream), HashMap.class);
        Map zgateway = (Map) proxyConfig.get("zgateway");
        listenPort = (int) zgateway.get("listenPort");
        proxyAddr = (String) zgateway.get("proxyAddr");
        //  http://localhost:8888/api/hello  ==> gateway API
        //  http://localhost:8088/api/hello  ==> backend service

        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION + " starting...");
        ZGatewayInboundServer server = new ZGatewayInboundServer(listenPort, proxyAddr);
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION + " started at http://localhost:" + listenPort + " for server:" + proxyAddr);
        try {
            server.run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
