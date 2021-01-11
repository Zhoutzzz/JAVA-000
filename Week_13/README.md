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