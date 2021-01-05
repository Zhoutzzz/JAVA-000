package org.ztzzz.distributed.controller;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ztzzz.distributed.count.DistributedCount;
import org.ztzzz.distributed.lock.DistributeLock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhoutzzz
 */
@RestController
@RequestMapping("/api")
public class DistributedController {

    private final DistributeLock dlock;

    private final RedissonClient client;

    private final DistributedCount count;

    @Autowired
    public DistributedController(DistributeLock lock, RedissonClient client, DistributedCount count) {
        this.client = client;
        this.dlock = lock;
        this.count = count;
    }

    @PostMapping("/lock")
    public String setValue() {
        RLock lock = dlock.getLock();
        boolean b = false;
        try {
            b = lock.tryLock(1, TimeUnit.SECONDS);
            if (b) {
                //模拟锁被独占
                System.out.println("=========================get lock success======================");
                Thread.sleep(10000);
                client.getBucket("gali").getAndSet("qiekenao" + ProcessHandle.current().pid());
            } else {
                //获取不到锁直接返回
                System.out.println("=========================get lock false, lock has been get, wait release======================");
                return "failed";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (b) {
                lock.unlock();
            }
        }

        return "success";
    }

    @PostMapping("/count/{reduce}")
    public String distributeCount(@PathVariable Long reduce) throws Exception {
        return count.decr(reduce);
    }

    //启动8080服务，作为发布端，修改代码再启动8081服务作为订阅端。
    @PostMapping("/order")
    public String pubSub() {

        return count.pubSub();
    }

}
