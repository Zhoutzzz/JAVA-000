package org.ztzzz.distributed.lock;

import org.redisson.Redisson;
import org.redisson.RedissonLock;
import org.redisson.api.*;
import org.redisson.client.RedisClient;
import org.redisson.client.RedisConnection;
import org.redisson.config.Config;
import org.redisson.config.RedissonNodeConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author zhoutzzz
 */
@Component
public class DistributeLock {

    private final RedissonClient client;

    @Autowired
    public DistributeLock(RedissonClient client) {
        this.client = client;
    }

    public RLock getLock() {
        return client.getLock("dlock");
    }
}
