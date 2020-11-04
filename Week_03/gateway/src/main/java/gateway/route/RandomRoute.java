package gateway.route;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author ztz
 * @description TODO
 * @date 2020/11/3 22:31
 */
public class RandomRoute extends ChannelInboundHandlerAdapter implements HttpEndpointRouter {
    private final static Random random = new Random();
    private static CopyOnWriteArrayList<String> endPointHost;

    public RandomRoute(List<String> proxyAddress) {
        endPointHost = new CopyOnWriteArrayList<>(proxyAddress);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpRequest request = (FullHttpRequest) msg;
        String host = route(endPointHost);
        request.headers().add("Proxy-Host", host);

        ctx.fireChannelRead(msg);
    }

    @Override
    public String route(List<String> endpoints) {
        if ( endpoints == null || endpoints.isEmpty()) {
            return "127.0.0.1";
        }
        int idx = random.nextInt(endpoints.size());
        return endpoints.get(idx);
    }
}
