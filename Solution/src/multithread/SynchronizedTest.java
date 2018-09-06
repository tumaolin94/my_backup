package multithread;

public class SynchronizedTest implements Runnable{  
	Object lock;
	public SynchronizedTest(Object lock) {
		this.lock = lock;
	}
    public  void func(int seed) {  
    	System.out.println(Thread.currentThread().getName() + ":" +" before synchronized block");
    	synchronized(lock) {
            if (seed <= 20) {  
                System.out.println(Thread.currentThread().getName() + ":" +seed);
//                func(++seed);

            }
            
//            return;      		
    	}
    	System.out.println(Thread.currentThread().getName() + ":" +" after synchronized block");

    }  
    
	@Override
	public void run() {
		// TODO Auto-generated method stub
    		try {
    			func(1);
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    public static void main(String[] args) {  
    	Object lock =                new Object();
    	SynchronizedTest test1 = new SynchronizedTest(lock);
    	SynchronizedTest test2 = new SynchronizedTest(lock);
    	Thread thread1 = new Thread(test1, "SyncThread1"); //这个是通过 thread 从同一个对象 建立的线程，所以能用this
    	Thread thread2 = new Thread(test2, "SyncThread2");
    	thread1.start();
    	thread2.start();
    }


}  
