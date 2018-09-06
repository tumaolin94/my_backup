package multithread;
import java.util.ArrayList;
import java.util.List;

public class MyList {

    private static List<String> list = new ArrayList<String>();

    public static void add() {
        list.add("anyString");
    }

    public static int size() {
        return list.size();
    }
    public static void main(String[] args) {

        try {
            Object lock = new Object();

            ThreadA a = new ThreadA(lock, "A");
            a.start();
            ThreadA c = new ThreadA(lock,"C");
            c.start();
            Thread.sleep(50);

            ThreadB b = new ThreadB(lock);
            b.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class ThreadA extends Thread {

    private Object lock;

    private String str;
    public ThreadA(Object lock, String str) {
        super();
        this.lock = lock;
        this.str = str;
    }

    @Override
    public void run() {
        try {
        	synchronized (lock) {
            	System.out.println("Thread "+str+" enter");
//                if (MyList.size() != 5) {
                    System.out.println("Thread "+str+ " wait begin "
                            + System.currentTimeMillis());
                    lock.wait();
                    System.out.println("Thread "+str+" wait end  "
                            + System.currentTimeMillis());
//                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class ThreadB extends Thread {
    private Object lock;

    public ThreadB(Object lock) {
        super();
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            synchronized (lock) {
            	System.out.println("Thread B enter");
                for (int i = 0; i < 10; i++) {
                    MyList.add();
                    if (MyList.size() == 5) {
                        lock.notifyAll();
                        System.out.println("Send notification");
                    }
                    System.out.println("Has added " + (i + 1) + " elements!");
                    Thread.sleep(500);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

