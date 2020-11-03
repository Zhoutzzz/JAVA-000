package gateway.route;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author ztz
 * @description TODO
 * @date 2020/11/3 22:31
 */
public class RandomRoute extends ChannelInboundHandlerAdapter implements RouteStrategy {
    private final static Random random = new Random();
    private List<String> endPointHost;

    public RandomRoute(List<String> proxyAddress) {
        this.endPointHost = proxyAddress;
    }

    @Override
    public String routeHost(List<String> endPointHost) {
        int idx = random.nextInt(endPointHost.size());
        return endPointHost.get(idx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        FullHttpRequest request = (FullHttpRequest) msg;
        String host = routeHost(endPointHost);
        request.headers().add("Proxy-Host", host);

        ctx.fireChannelRead(msg);
    }
}
