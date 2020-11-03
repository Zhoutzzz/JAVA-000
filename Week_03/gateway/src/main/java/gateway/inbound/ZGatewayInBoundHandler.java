package gateway.inbound;

import gateway.outbound.NettyHttpClient;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.unix.DomainSocketAddress;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.resolver.InetSocketAddressResolver;

import java.net.*;

/**
 * @author ztz
 * @description TODO
 * @date 2020/10/30 14:55
 */
public class ZGatewayInBoundHandler extends ChannelInboundHandlerAdapter {

    //    private static Logger logger = LoggerFactory.getLogger(HttpInboundHandler.class);
    private static final NettyHttpClient client = new NettyHttpClient();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("remoteAddress---->" + ctx.channel().remoteAddress());
        System.out.println("localAddress---->" + ctx.channel().localAddress());
        try {
            if (msg instanceof FullHttpRequest) {
                FullHttpRequest fullRequest = (FullHttpRequest) msg;
                String proxyHost = fullRequest.headers().get("Proxy-Host");
                String[] hostAndPort = proxyHost.split(":");
                SocketAddress address = new InetSocketAddress(hostAndPort[0], Integer.parseInt(hostAndPort[1]));
                client.connect(address, ctx, msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        cause.printStackTrace();
    }
}
