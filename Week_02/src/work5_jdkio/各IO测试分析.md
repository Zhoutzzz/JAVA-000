# 传统socket连接
    Finished at 2020/10/27 22:34:20 (took 00:00:33.9291431)
    1510    (RPS: 44.5)                     Status 200:    1510

    RPS: 48.6 (requests/second)
    Max: 444ms
    Min: 65ms
    Avg: 399ms

# 使用新线程的进行非阻塞socket

    ---------------Finished!----------------
    Finished at 2020/10/27 22:40:56 (took 00:00:34.1210533)
    Status 200:    27956
    Status 303:    919

    RPS: 930.7 (requests/second)
    Max: 65ms
    Min: 19ms
    Avg: 19.7ms

# 使用线程池进行的非阻塞socket

    ---------------Finished!----------------
    Finished at 2020/10/27 22:41:06 (took 00:00:34.1318278)
    29623   (RPS: 868.6)                    Status 200:    29115
    Status 303:    510

    RPS: 954.9 (requests/second)
    Max: 74ms
    Min: 19ms
    Avg: 19.2ms

# 使用Netty进行socket
    ---------------Finished!----------------
    Finished at 2020/10/27 23:47:42 (took 00:00:33.8270779)
    Status 200:    247399

    RPS: 7961.8 (requests/second)
    Max: 132ms
    Min: 0ms
    Avg: 0ms

# 使用spring boot进行测试
    ---------------Finished!----------------
    Finished at 2020/10/27 23:57:58 (took 00:00:33.7824241)
    Status 404:    214260

    RPS: 6894.3 (requests/second)
    Max: 56ms
    Min: 0ms
    Avg: 0.2ms

* sb -u http://localhost:8803 -c 20 -N 30
    
    20并发执行30秒

以上三个基于JDK的测试结果，体现了非阻塞情况下的性能提升，但是每次使用新线程接收和线程池接收，则看似差距不大，实际这两种方式的最大区别在于线程创建的开销和线程过多导致的线程上下文切换产生的资源消耗，以此并发测试，每次使用新线程处理请求时，30秒总计创建2W线程，服务器资源大多都浪费在无用开销上。

而在使用Netty时，其吞吐量接近8000， spring boot吞吐量接近7000，两者吞吐量相差1000，这是为什么？？