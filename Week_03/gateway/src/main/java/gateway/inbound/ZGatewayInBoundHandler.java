package gateway.inbound;

//import gateway.outbound.NettyHttpClient;

import gateway.outbound.NettyGatewayOutBoundHandler;
import gateway.outbound.NettyHttpClient;
import gateway.outbound.ZGatewayOutBoundHandler;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.util.ReferenceCountUtil;

/**
 * @author ztz
 * @description TODO
 * @date 2020/10/30 14:55
 */
public class ZGatewayInBoundHandler extends ChannelInboundHandlerAdapter {

    //    private static Logger logger = LoggerFactory.getLogger(HttpInboundHandler.class);
    private final String proxyServer;
    //    private ZGatewayOutBoundHandler handler;
    private static final NettyHttpClient client = new NettyHttpClient();

    public ZGatewayInBoundHandler(String proxyServer) {
        this.proxyServer = proxyServer;
//        handler = new ZGatewayOutBoundHandler(this.proxyServer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {

            FullHttpRequest fullRequest = (FullHttpRequest) msg;
            fullRequest.headers().set("host", "127.0.0.1");
            client.connect("127.0.0.1", 8088, ctx, msg);
            //logger.info("channelRead流量接口请求开始，时间为{}", startTime);

//            String uri = fullRequest.uri();
//            //logger.info("接收到的请求url为{}", uri);
//            if (uri.contains("/test")) {
//                handlerTest(fullRequest, ctx);
//            }

//            handler.handle(fullRequest, ctx);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
