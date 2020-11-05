学习笔记

# netty

## 整体流程
  netty统一了客户端和服务端访问方式，通过ServerBootStrap和BootStrap作为服务端和客户端，以此建立通讯基础。
  
  在服务端和客户端中绑定EventLoopGroup，bossGroup作为整个netty的大门，只负责接收外来请求，不做任何处理，将接收的请求转发到绑定的workGroup进行处理。


**当网络请求发送给netty时，首先被bossGroup接收，然后转发到workGroup，由workGroup中的轮EventLoop处理，每个EventLoop绑定多个Channel，将IO事件交由其中一个Channel处理，每个Channel绑定一个ChannelPipeline，作为实际IO事件的处理链，每个ChannelPipeline中绑定多个ChannelHandler，负责具体的处理步骤，根据ChannelHandler属于Inbound还是Outbound，处理顺序不同，每个ChannelHandler拥有一个ChannelHandlerContext，作为当前处理器将处理信息传到下一个处理器的媒介。**

  ![netty](./picture/1604472004(1).jpg)

## EventLoopGroup
  
  EventLoopGroup，实际上就是一个线程池，创建bootstrap时，根据group方法不同，可以创建出不同的Reactor模型，比如单线程Reactor---->接收请求并分发给对应的handler处理；多线程Reactor---->接收请求转发到子Group处理，用来接收请求的group依然是单线程；主从Reactor---->多线程bossGroup和多线程WorkGroup，boss接收请求，转发到work处理。

  ![EventLoopGroup](./picture/1604474549(1).jpg)
  
## EventLoop

  作为EventLoopGroup中的线程，与Java线程1：1绑定，实际线程在EventLoop中轮询处理IO事件，当bossGroup将IO请求交由workGroup时，事件统一放在selector中，selector再将事件交由空闲线程处理。
## Channel

  作为EventLoop中的通道，EventLoop中的轮询线程，实际轮询的就是Channel，所有处理事件，都包装在Channel中，Channel作为netty的处理链的最外层。
## ChannelPipeline

  处理链，内部进行数据处理。
## InboundHandle

  入站处理器
## OutBoundHandle

  出站处理器
## ByteBuf

  网络中传输的数据流的统一抽象。

## 关于Inbound与Outbound执行顺序
```
          ch.pipeline().addLast(new InboundHandler1());
          ch.pipeline().addLast(new OutboundHandler1());
          ch.pipeline().addLast(new OutboundHandler2());
          ch.pipeline().addLast(new InboundHandler2());
  或者：
          ch.pipeline().addLast(new OutboundHandler1());
          ch.pipeline().addLast(new OutboundHandler2());
          ch.pipeline().addLast(new InboundHandler1());
          ch.pipeline().addLast(new InboundHandler2());
```

    执行流程：InboundHandler1--> InboundHandler2 -->OutboundHandler2 -->OutboundHandler1

* InboundHandler顺序执行，OutboundHandler逆序执行
* InboundHandler之间传递数据，通过ctx.fireChannelRead(msg)
* InboundHandler通过ctx.write(msg)，则会传递到outboundHandler
* 使用ctx.write(msg)传递消息，Inbound需要放在结尾，在Outbound之后，不然outboundhandler会不执行但是使用channel.write(msg)、pipline.write(msg)情况会不一致，都会执行,那是因为channel和pipline会贯穿整个流。
* outBound和Inbound谁先执行，针对客户端和服务端而言，客户端是发起请求再接受数据，先outbound再inbound，服务端则相反。