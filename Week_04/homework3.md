ThreadPoolExecutors
线程池，集中管理，复用线程资源，避免过多创建线程造成的一系列开销

Semaphore
创建时初始化给定数量资源，当资源用尽，后来的操作将被阻塞，类似于令牌。

CountDownLatch
类似跑步比赛的起跑线，当主线程在某处await时，子线程运行可以countdown，计数为0时，主线程继续执行，一旦创建运行，则不可再用。

CyclicBarrier
与CountDownLatch类似，但是其可以复用，类似于在一个圈中设置一个节点，当圈中任务跑到节点时停下，当所有任务都到节点时，发出一个通知，即调用回调函数。
以上三种在需要线程间协作的场景都可以使用

Future
异步的执行一个任务

ForkJoin
类似Future, 但是其特别之处在于，ForkJoinPool中的两个任务队列，当其中一个任务队列的为空时，对应的线程会从另一个队列拿取任务执行

ReentrantLock
Java层面的锁，对应的synchronized为JVM层面的锁，其比sync关键字，更为灵活，拥有尝试机制，可中断。在对锁有中断需求，或者动态判断加锁条件的情况下使用，中低并发且没有对锁本身有特别操作需求时可以用sync。

ReentrantReadWriteLock
类似数据库读写锁，读时共享，写时独占，读多写少场景下很适合用的锁。

LockSupport
类似wait/notify机制，但其不会发生死锁情况，中断线程不会抛出异常，需要特别处理。

ConcurrentHashMap
线程安全的HashMap,适合在多线程情况下使用，其size方法得到的值，不一定是最新值。

CopyOnWriteArrayList
在对该对象内容进行更改时，会在复制原值得到的副本上进行修改，不影响老数据，读多写少的并发场景适合使用，但是因为并发问题，其size方法得到的值，不一定是最新值。