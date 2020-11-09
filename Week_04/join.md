sync关键字，锁定当前类或者当前对象实例
join方法，让调用join的线程挂起等待，被调用的线程开始执行直至执行完成后唤醒调用join的线程继续执行。
```
package java0.conc0301.op;

public class Join {

    public static void main(String[] args) throws InterruptedException {
        Object oo = new Object();

        MyThread thread1 = new MyThread("thread1 -- ");
        MyThread thread2 = new MyThread("thread2 -- ");
        thread1.setOo(oo);
        thread1.start();
        thread2.start();


        synchronized (thread1){
            for (int i = 0; i < 100; i++) {
                if (i == 20) {
                    try {
                        thread1.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + " -- " + i);
            }
        }
    }

}

class MyThread extends Thread {

    private String name;
    private Object oo;

    public void setOo(Object oo) {
        this.oo = oo;
    }

    public MyThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        synchronized (this) {
            for (int i = 0; i < 100; i++) {
                System.out.println(name + i);
            }
        }
    }
}

```

上述代码，thread1和thread2启动之后，在主线程内获取thread1对象的实例锁，意味着当主线程持有thread1锁进入同步块时，thread1线程虽然start了，但是要等main函数释放锁才能运行，虽然main线程与thread1其实是独立的两个线程，理论上会有交替执行的可能，但main获取了锁，thread1自己也无法运行它所属实例的任意方法。sync关键字又为可重入锁，当调用thread1.join（）方法时，内部的sync方法可以继续执行，调用了join之后，main线程挂起释放获取到的thread1实例锁，这时候，thread1才有可能获取CPU运行权限，这里说有可能，因为主线程启动了2个线程，thread1，2是相互独立的，二者都有获取CPU执行权限的资格，谁先执行，就看谁先抢到CPU了。同样的，在main线程执行同步代码块之前，thread2也是可以与主线程争抢CPU运行权限的，因为main只是拿了thread1的锁，没有拿thread2。

```
main -- 0
thread2 -- 0
thread2 -- 1
thread2 -- 2
thread2 -- 3
thread2 -- 4
thread2 -- 5
...
main -- 1
main -- 2
main -- 3
main -- 4
main -- 5
main -- 6
main -- 7
main -- 8
main -- 9
main -- 10
main -- 11
main -- 12
main -- 13
main -- 14
main -- 15
main -- 16
main -- 17
main -- 18
main -- 19
thread1 -- 0
thread1 -- 1
thread1 -- 2
thread1 -- 3
thread1 -- 4
thread1 -- 5
thread1 -- 6
...
main -- 20
main -- 21
main -- 22
...
```
捕捉到的其中一次执行情况，可以看到，main执行一次之后，就切换到了thread2执行，但是，无论运行多少次，thread1的执行，一定在main调用了thread1.join()方法之后才会开始。