学习笔记

# JVM内存结构
整个JVM运行时内存，包括程序计数器，虚拟机栈，本地方法栈，堆，方法区，本地内存。

其中，程序计数器，本地方发栈，虚拟机栈为线程私有，堆与方法区为线程共享。

## 程序计数器
负责控制具体方法执行位置，也可以理解为当前执行的字节码行号指示器，因为CPU在同一时刻只能执行一个线程，每个线程执行的方法及行数不同，所以计数器是线程私有。

## 虚拟机\本地方法栈
在Hotspot中，虚拟机栈与本地方法栈合并实现，其中保存了线程的所执行的方法的局部变量表，动态链接，返回值地址，操作数栈等，这些信息统称为栈帧信息。栈帧信息，在编译期间就会确定，不再改变。

* 操作数栈
 
    操作数栈保存的是包括long、double这种64位数据类型在内的任意Java数据类型，如果是基本数据类型，操作数栈在进行算术运算的时候会将距离栈顶最近的2个数出栈进行运算然后将结果在入栈。
* 局部变量表

    局部变量表保存的是当前执行的方法的局部变量，方法的参数，在实例方法（非static修饰）中，局部变量表的第一个slot，存放的是当前方法所属实例引用，即this，之后根据方法体内部的变量定义顺序、方法的入参进行slot的分配

* 动态链接

    在方法调用中，字节码的调用指令是以常量池中指向方法的符号引用作为参数，当这些符号引用在运行时被转化为直接引用时，就称为动态链接。相对的，在类加载或这些符号引用第一次被使用时转化为直接引用的，称为静态解析

* 方法返回地址

    方法正常或因为异常退出后，回到最初方法被调用的位置，例如，主调方法PC计数器，遇到异常根据异常处理器表决定位置。


## 堆、方法区

* 堆

    堆是JVM中分配内存最多的部分，其中堆又根据分代理论分为新生代、老年代，在Java1.8以前，整个堆的内存分配，就根据分代理论分为两大块。
    而1.8后，典型的比如G1，根据不同的垃圾收集器将内存分为多个等大小的region，默认大小为1/堆内存大小，并且每个region根据JVM运行情况扮演，Eden，Survivor，老年代，在比如不分代的ZGC，每个reigon只会是大型、中型、小型3中类型。
    
    **在G1中，存在Humongous Reigon区域，专门用来存储大对象，当对象需要分配的内存空间大于一个reigon的一半容量，则会认为这是大对象，当对象需要分配的内存空间大于一个reigon的容量，则会被存在方N个连续的Humongous Reigon中。一般G1把Humongous Region作为老年代的一部分看待**

    一般情况，大于1G内存的机器，JVM默认情况下会为堆分配总内存的1/4作为堆内存，而堆内存中，默认情况下又会将新生代和老年代的内存比例以1：2进行分配，新生代中，Eden和S0,S1默认会按照8：1：1的比例分配内存。

* 新生代

    新生代分为Eden区，survivor0, survivor1区，给对象分配内存时，优先将对象放入Eden区，当其在GC过程中没有被回收，会进入到s区.在s区或者eden区的对象如果在进行了X次GC仍存活，则会进入到老年代，其中X根据参数-XX:MaxTurningThreshold进行设置，另外，当需要分配内存的对象大于设置的阈值时，也会直接进入老年代，通过-XX：PretenureSizeThreshold。


# Hotspot中的垃圾收集器

## 垃圾收集器的搭配

    在Java1.7之前版本，Hotspot中存在6中GC收集器，serial、serial old、 CMS、parllel scavenge、parnew、parllel old，1.8中新增G1收集器，JDK15中新增ZGC收集器

    Serial、ParNew、Parallel Scavenge负责新生代垃圾收集；
    CMS、Serial Old、Parallel Old负责老年代收集。
    G1中内存分为reigon，G1收集时只针对reigon，所以G1可以算作新生代老年代一起收集的收集器。

* 从JDK9开始，各个垃圾收集器的组合：

    XX:UseConcMarkSweepGC --> ParNew + CMS + Serial Old(CMS在发生并发收集失败时的备用老年代收集器);

    XX:UseSerialGC --> Serial + Serial Old;
    
    XX:UseParallelGC Parallel Scavenge + Serial Old（PS MarkSweep--PS收集器中内置的老年代收集器，其实现与Serial Old几乎一致,但在回收老年代时，不会直接调用Serial Old回收，而是用PS MarkSweep回收）（JDK8 server模式下默认组合）;
    
    XX:UseParallelOldGC --> Parallel Scavenge + Parallel Old;
    
    XX:UseG1GC --> G1(JDK9 默认);

## 三大GC基础算法
    标记-清除，标记-复制，标记-清除-整理

    JVM中，对无用对象判断方式，使用可达性分析法，以根对象为起点，向下扫描引用对象，不在路径中的对象，则会被回收。

    Serial、ParNew、Parallel Scavenge使用的标记-复制算法回收内存。

    Serial Old、Parallel Old使用标记-整理算法回收内存

    CMS使用标记-清除算法回收内存

    G1整体看使用标记-整理算法，从局部看基于标记-复制算法。

# 类加载
    JDK类加载基于双亲委派模型，除Bootstarp为虚拟机内部实现的根加载器，其余加载器都有一个父加载器

    当一个类加载时，默认由AppClassLoader加载，但在加载前AppClassLoader会委托父类加载器ExtClassLoader进行加载，逐层往上直至BootstrapClassLoader，如果父类之前有加载过，则会直接返回加载的类，否则父类加载器加载该类并返回，当父类加载器不能加载该类时会往下由其自己进行加载
## 类加载步骤
    加载 --> (验证 --> 准备 --> 解析) 链接 --> 初始化 --> 使用 --> 卸载
    链接阶段会交叉执行，比如A类还在准备阶段，B类就已经处在验证阶段。

## 类加载时机
    1. 定义对象数组，不会触发该类的初始化。
    2. 常量在编译期间会存入调用类的常量池中，本质上并没有直接引用定义常量的类，不会触发定义常量所在的类。
    3. 通过类名获取 Class 对象，不会触发类的初始化，Hello.class 不会让 Hello 类初始化。
    4. 通过 Class.forName 加载指定类时，如果指定参数 initialize 为 false 时，也不会触发类初始化，其实这个参数是告诉虚拟机，是否要对类进行初始化。Class.forName（“jvm.Hello”）默认会加载 Hello 类。
    5. 通过 ClassLoader 默认的 loadClass 方法，也不会触发初始化动作（加载了，但是不初始化）。

## 类初始化时机
    1. 当虚拟机启动时，初始化用户指定的主类，就是启动执行的 main 方法所在的类；
    2. 当遇到用以新建目标类实例的 new 指令时，初始化 new 指令的目标类，就是 new一个类的时候要初始化；
    3. 当遇到调用静态方法的指令时，初始化该静态方法所在的类；
    4. 当遇到访问静态字段的指令时，初始化该静态字段所在的类；
    5. 子类的初始化会触发父类的初始化，但是通过子类引用父类的静态字段，只会触发父类的初始化，而不会触发子类的初始化。
    6. 如果一个接口定义了 default 方法，那么直接实现或者间接实现该接口的类的初始化，会触发该接口的初始化；
    7. 使用反射 API 对某个类进行反射调用时，初始化这个类，其实跟前面一样，反射调用要么是已经有实例了，要么是静态方法，都需要初始化；
    8. 当初次调用 MethodHandle 实例时，初始化该 MethodHandle 指向的方法所在的类。

## 类卸载时机
* 同时满足以下3个条件，该类被卸载：
    * 当前类没有任何实例存在；
    * 表示当前类的Class对象没有被引用，如不能在任何地方通过反射访问该类的方法；
    * 当前类的加载器没有被引用
```
    public void test() {
        HelloClassLoader helloClassLoader = new HelloClassLoader();
            //通过classLoader加载Hello类
            Object hello = helloClassLoader.loadFileByPath(className, path).getDeclaredConstructor().newInstance();
            System.out.println(hello.getClass().getClassLoader());

            //执行hello方法
            hello.getClass().getDeclaredMethod("hello").invoke(hello);

            //将当前类加载器和当前类置空
            helloClassLoader = null;
            hello = null;
            //建议系统进行GC
            System.gc();
    }
```
**打印如下内容**

[0.343s][info   ][class,load] HelloClassLoader source: file:/E:/xxx/xxx/out/production/JAVA-000/

[0.343s][info   ][class,load] java.net.Socket$2 source: jrt:/java.base

[0.343s][info   ][class,load] java.net.SocketInputStream source: jrt:/java.base

[0.344s][info   ][class,load] sun.nio.cs.StreamDecoder source: jrt:/java.base

[0.344s][info   ][class,load] sun.nio.cs.US_ASCII$Decoder source: jrt:/java.base

[0.344s][info   ][class,load] java.io.IOException source: jrt:/java.base

<font color=red>[0.345s][info   ][class,load] Hello source: __JVM_DefineClass__
HelloClassLoader@3d24753a</font>

Hello, classLoader!

<font color=red>[0.348s][info   ][class,unload] unloading class Hello 0x0000000021a76840</font>

**红字部分显示类Hello最终被加载执行hello方法后被卸载**

## JDK默认类加载器

    BootstrapClassLoader、ExtClassLoader、AppClassLoader。
    其中这三个加载器的父子关系为：
    AppClassLoader extends ExtClassLoader
    ExtClassLoader extends BootstrapClassLoade
**自定义类加载器CustomClassLoader extends AppClassLoader**