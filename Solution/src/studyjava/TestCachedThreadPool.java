package studyjava;

import java.util.concurrent.ExecutorService;   
import java.util.concurrent.Executors;   

public class TestCachedThreadPool{   
    public static void main(String[] args){   
//        ExecutorService executorService = Executors.newCachedThreadPool();   
      ExecutorService executorService = Executors.newFixedThreadPool(5);  
//      ExecutorService executorService = Executors.newSingleThreadExecutor();  
        int i = 0;
        for (;;){   
            executorService.execute(new TestRunnable());   
//            System.out.println("************* a" + i++ + " *************");   
        }   
//        executorService.shutdown();   
    }   
}   

class TestRunnable implements Runnable{   
    public void run(){   
        
        try {
        	System.out.println(Thread.currentThread().getName() + "线程被调用了。");   
			Thread.sleep(5000000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }   
}  
