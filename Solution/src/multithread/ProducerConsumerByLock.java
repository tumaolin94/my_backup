package multithread;

import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerByLock {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReentrantLock lock = new ReentrantLock();
		Condition notFull = lock.newCondition();
		Condition notEmpty = lock.newCondition();
		Stack<Integer> items = new Stack<Integer>();
		ProducerByLock pro2 = new ProducerByLock(lock, notFull, notEmpty, items); 
		Thread t1 = new Thread(pro2,"Producer1");
        Thread p2 = new Thread(pro2,"Producer2");
        ConsumerByLock consumer  = new ConsumerByLock(lock, notFull, notEmpty, items);
//        Thread t2 = new Thread(consumer,"consumer1");
//        Thread t3 = new Thread(consumer,"consumer2");
//        Thread t4 = new Thread(consumer,"consumer3");
        Thread t2 = new Thread(consumer,"consumer1");
        Thread t3 = new Thread(consumer,"consumer2");
        Thread t4 = new Thread(consumer,"consumer3");
        t2.start();
        t3.start();
        t4.start();
        
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        t1.start();
        p2.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
//        t2.start();
//        t3.start();
//        t4.start();
        try {
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}

}
class ProducerByLock implements Runnable{

	ReentrantLock lock;
	private Condition notFull;
	private Condition notEmpty;
	Stack<Integer> items;
	int count = 0;
	public ProducerByLock(ReentrantLock lock, Condition notFull, Condition notEmpty,Stack<Integer> items) {
		this.lock = lock;
		this.notFull = notFull;
		this.notEmpty = notEmpty;
		this.items = items;
	}
	
	public void produce() {
		System.out.println(Thread.currentThread().getName() + ": " +"produce 1 item, now the size of item is "+ items.size());
		items.push(1);
		System.out.println("produce totally "+(++count));
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(count < 20) {
			System.out.println(Thread.currentThread().getName() + ": " +"lock");
			lock.lock();
			System.out.println(Thread.currentThread().getName() + ": " +" enter");
			while(items.size() == 10) {
				try {
					System.out.println(Thread.currentThread().getName() + ": " +"await notFull");
					notFull.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				produce();
				notEmpty.signal();
			} finally {
				System.out.println(Thread.currentThread().getName() + ": " +"unlock");
				lock.unlock();
			}
		}
	}
	
}

class ConsumerByLock implements Runnable{

	ReentrantLock lock;
	private Condition notFull;
	private Condition notEmpty;
	Stack<Integer> items;
	AtomicInteger consumed = new AtomicInteger();
	public ConsumerByLock(ReentrantLock lock, Condition notFull, Condition notEmpty,Stack<Integer> items) {
		this.lock = lock;
		this.notFull = notFull;
		this.notEmpty = notEmpty;
		this.items = items;
	}
	
	public void consume(String name) {
		if (!items.isEmpty()) {
			System.out.println(name + ": " +"consume 1 item, now the size of item is "+ items.size());
			items.pop();
			consumed.incrementAndGet();
			System.out.println(consumed.get());
		}
	}
    private boolean theEnd() {
        return consumed.get() >= 20;
    }
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!theEnd()) {
			System.out.println(Thread.currentThread().getName() + ": " +"lock");
			lock.lock();
			System.out.println(Thread.currentThread().getName() + ": " +" enter");
			while(items.isEmpty() && !theEnd()) {
				try {
					System.out.println(Thread.currentThread().getName() + ": " +"await notEmpty");
					notEmpty.await();
					System.out.println(Thread.currentThread().getName() + ": " +"was signaled");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				consume(Thread.currentThread().getName());
				notFull.signal();
			} finally {
				System.out.println(Thread.currentThread().getName() + ": " +"unlock");
				lock.unlock();
			}
			
		}
	}
	
}
