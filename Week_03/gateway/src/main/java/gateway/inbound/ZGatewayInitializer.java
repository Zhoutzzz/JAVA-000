package gateway.inbound;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.*;

/**
 * @author ztz
 * @description TODO
 * @date 2020/10/30 14:58
 */
public class ZGatewayInitializer extends ChannelInitializer<SocketChannel> {

    private String proxyAddress;

    public ZGatewayInitializer(String proxyAddress) {
        this.proxyAddress = proxyAddress;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new HttpRequestDecoder())
                .addLast(new HttpResponseEncoder())
                //消息聚合器,注意,需要添加在http编解码器(HttpServerCodec)之后
                .addLast(new HttpObjectAggregator(65536));
        pipeline.addLast(new ZGatewayInBoundHandler(this.proxyAddress));
    }
}
