# redis-sentinel
总体配置为1主2从3哨兵
redis.conf为master配置，对应的slave1、2为从节点配置，sentinel类似。
## 关于sentinel的一些问题
sentinel默认配置下没有配置bind参数，只能使用127.0.0.1来查找，所以非127.0.0.1的ip需要配置bind参数：bind 127.0.0.1 ::1.
所有的sentinel监听master，不然就是独立的sentinel没什么卵用。