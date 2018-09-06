package multithread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class ReaderWriterByLock {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReentrantLock w = new ReentrantLock();
		ReentrantLock r = new ReentrantLock();
		AtomicInteger a = new AtomicInteger();
		Reader1 r1 = new Reader1(w,r,a);
		  
	    Writer1 w1 = new Writer1(w, r,a);  
 
	    new Thread(r1, "读者线程 r1").start();  
	    new Thread(r1, "读者线程r2").start();  
	    new Thread(r1, "读者线程r3").start();  
	    new Thread(r1, "读者线程r4").start();  
	    new Thread(w1, "写者线程w1").start();  
	    new Thread(w1, "写者线程w2").start();  
	    new Thread(w1, "写者线程w3").start();  
		
	}
	
	
	
	
}
class Reader1 implements Runnable{
	ReentrantLock w;
	ReentrantLock r;
//	volatile int count = 0;
	volatile int sum = 0;
	AtomicInteger a;
	public Reader1(ReentrantLock w, ReentrantLock r, AtomicInteger a) {
		this.w = w;
		this.r = r;
		this.a = a;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(sum++ <100) {
			System.out.println(Thread.currentThread().getName() + ": " +"enter. ");
			try {
				r.lock();
				if(a.equals(0)) {
					System.out.println(Thread.currentThread().getName() + ": " +"is first Reader. ");
					w.lock();
				}
				a.incrementAndGet();
			}finally {
				System.out.println(Thread.currentThread().getName() + ": " +"release r lock. ");
				r.unlock();
			}
			

			
			System.out.println(Thread.currentThread().getName() + ": " +"is reading. "+sum);
			
			try {
				r.lock();
				a.decrementAndGet();
				if(a.equals(0)) {
					System.out.println(Thread.currentThread().getName() + ": " +"is last Reader. ");
					w.unlock();
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally {
				r.unlock();
			}
			
			
		}
		
	}
}
class Writer1 implements Runnable{
	ReentrantLock w;
	ReentrantLock r;
	int sum =0;
	AtomicInteger a;
	public Writer1(ReentrantLock w, ReentrantLock r, AtomicInteger a) {
		this.w = w;
		this.r = r;
		this.a = a;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(sum++ < 100) {
			System.out.println(Thread.currentThread().getName() + ": " +"enter. ");
			w.lock();
			System.out.println(Thread.currentThread().getName() + ": " +"is writing. "+sum);
			w.unlock();
		}

	}
}



