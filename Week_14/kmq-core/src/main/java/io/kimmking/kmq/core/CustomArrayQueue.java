package io.kimmking.kmq.core;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class CustomArrayQueue<E> {
    private Object[] queue;
    private static final int DEFAULT_CAPACITY = 16;

    private int freeIndex;

    private int takeIndex;

    private int itemCount;

    private static final ReentrantLock lock = new ReentrantLock();

    private Condition c;

    public CustomArrayQueue() {
        this.queue = new Object[DEFAULT_CAPACITY];
        this.freeIndex = this.queue.length - 1;
        this.takeIndex = this.freeIndex;
    }

    public CustomArrayQueue(int capacity) {
        this.queue = new Object[capacity];
        this.freeIndex = this.queue.length - 1;
        this.takeIndex = this.freeIndex;
        c = lock.newCondition();
    }

    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        long nanos = unit.toNanos(timeout);
        try {
            lock.lockInterruptibly();
            while (itemCount == 0) {
                if (nanos <= 0) {
                    return null;
                }
                nanos = c.awaitNanos(nanos);
            }
            return poll();
        } finally {
            lock.unlock();
        }
    }

    public E poll() {
        try {
            lock.lock();
            E item = (E) this.queue[takeIndex];
            takeIndex--;
            itemCount--;
            reset();
            return item;
        } finally {
            lock.unlock();
        }
    }

    public boolean push(E item) {
        try {
            lock.lock();
            int cur = this.freeIndex;
            checkCapacity();
            this.queue[cur] = item;
            do {
                itemCount++;
                this.freeIndex--;
            } while (cur == freeIndex);
            return true;
        } finally {
            lock.unlock();
        }
    }

    private void checkCapacity() {
        if (freeIndex < 0) {
            Object[] objects = new Object[queue.length << 1];
            int newLength = objects.length - 1;
            for (int i = queue.length - 1; i >= 0; i--) {
                objects[newLength] = queue[i];
                newLength--;
            }
            this.freeIndex = newLength;
            this.queue = objects;
        }
    }

    private void reset() {
        if (this.freeIndex == this.takeIndex) {
            this.freeIndex = this.takeIndex = queue.length - 1;
        }
    }
}
