package gateway.inbound;

import gateway.filter.DefaultHttpRequestFilter;
import gateway.filter.FilterChain;
import gateway.route.RandomRoute;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.*;

import java.util.List;

/**
 * @author ztz
 * @description TODO
 * @date 2020/10/30 14:58
 */
public class ZGatewayInitializer extends ChannelInitializer<SocketChannel> {

    private List<String> proxyAddress;

    public ZGatewayInitializer(List<String> proxyAddress) {
        this.proxyAddress = proxyAddress;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        FilterChain filterChain = new FilterChain();
        filterChain.addFilter(new DefaultHttpRequestFilter());
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new HttpRequestDecoder())
                .addLast(new HttpResponseEncoder())
                //消息聚合器,注意,需要添加在http编解码器(HttpServerCodec)之后
                .addLast(new HttpObjectAggregator(65536))
                .addLast(filterChain)
                .addLast(new RandomRoute(proxyAddress))
                .addLast(new ZGatewayInBoundHandler());
    }
}
