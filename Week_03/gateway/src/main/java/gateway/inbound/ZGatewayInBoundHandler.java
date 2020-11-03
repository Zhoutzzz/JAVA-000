package gateway.inbound;

import gateway.outbound.NettyHttpClient;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.unix.DomainSocketAddress;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

/**
 * @author ztz
 * @description TODO
 * @date 2020/10/30 14:55
 */
public class ZGatewayInBoundHandler extends ChannelInboundHandlerAdapter {

    //    private static Logger logger = LoggerFactory.getLogger(HttpInboundHandler.class);
    private final String proxyServer;
    private final int port;
    private boolean domain;
    private static final NettyHttpClient client = new NettyHttpClient();

    public ZGatewayInBoundHandler(String proxyServer, int port, boolean isDomain) {
        this.proxyServer = proxyServer;
        this.port = port;
        this.domain = isDomain;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            if (msg instanceof FullHttpRequest) {
                FullHttpRequest fullRequest = (FullHttpRequest) msg;
                fullRequest.headers().iteratorAsString().forEachRemaining(System.out::println);
                fullRequest.headers().set(HttpHeaderNames.HOST, proxyServer);
                SocketAddress address;
                if (domain) {
                    address = new DomainSocketAddress("baidu.com");
                } else {
                    address = new InetSocketAddress(proxyServer, port);
                }
                client.connect(address, ctx, msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
