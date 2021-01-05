package org.ztzzz.distributed.count;

import org.redisson.api.*;
import org.redisson.api.listener.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author zhoutzzz
 */

@Component
public class DistributedCount {
    @Autowired
    RedissonClient client;

    public String decr(Long reduce) throws InterruptedException {
        RCountDownLatch latch = client.getCountDownLatch("latch");
        StringBuilder builder = new StringBuilder("source: ");
        if (latch.isExists() || latch.trySetCount(2)) {
            if (latch.getCount() >= 1) {
                latch.countDown();
                latch.await();
            }
            RAtomicLong count;
            long l;
            do {
                count = client.getAtomicLong("count");
                l = count.get();
                if (l < 0 || l - reduce < 0) {
                    throw new InterruptedException("库存不够");
                }
            } while (!count.compareAndSet(l, l - reduce));
            builder.append(l).append(",").append("reduce: ").append(reduce).append("===============>target: ").append(count.get());
        }
        return builder.toString();
    }

    public String pubSub() {

        StringBuilder res = new StringBuilder();

        //发布
//        RTopic pubOrder = client.getTopic("order");
//        pubOrder.publishAsync("下单啦，单号：" + new Random().nextInt(2 << 16));
//        return res.append("下单成功").toString();

        //订阅
        RTopic subOrder = client.getTopic("order");
        subOrder.removeAllListeners();
        subOrder.addListener(String.class, (channel, msg) -> {
            System.out.println(msg);
        });

        return res.append("订阅成功").toString();
    }
}
