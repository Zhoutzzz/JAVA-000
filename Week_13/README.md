学习笔记
# Spring kafka注意点
在项目中配置kafka时，构建主题的时候，compact()方法会配置主题的日志清理策略为compact，这是topic __consumer_offsets的默认策略，这个策略会根据消息中的key进行删除操作，如果用kafkaTemplate操作发送消息没有key，会报错：
```
org.apache.kafka.common.InvalidRecordException: One or more records have been rejected
```

所以要配置忽略key的主题，不能调用compact()方法：
```
@Bean
	public NewTopic topic1() {
		return TopicBuilder.name("giao")
				.partitions(2)
				.replicas(3)
            //  .compact()
				.build();
	}
```
# Spring activeMQ注意点
点对点和发布订阅，只能二选一，不能同时存在，同时存在的话会有一个模式收不到消息。
配置文件中配置spring.jms.pub-sub-domain=true开启发布订阅，
重新创建一个JmsTemplate的时候，需要配置spring.main.allow-bean-definition-overriding=true
重写定义的bean，不然启动不起来
JmsMessagingTemplate与JmsTemplate的区别在于，前者是后面版本（Spring 4.1）提供的更高层的消息抽象API,
后者相对来说更基础。
但是在用jackson进行序列化的时候，前者发送的消息实际对象为GenericMessaging,
反序列化不过来，所以用JmsTemplate。