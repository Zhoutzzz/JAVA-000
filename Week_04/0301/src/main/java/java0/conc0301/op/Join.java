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