package multithread;

import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;
// wait 必须在循环里，因为唤醒后出现在wait的代码行中，如果不循环，就消失了
public class ProducerConsumer {
//	Stack<Integer> items = new Stack<Integer>();
    final static int NO_ITEMS = 10;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        ProducerConsumer pc = new ProducerConsumer();
        Stack<Integer> items = new Stack<Integer>();
        Producer2 pro2 = new Producer2(items, 10); 
        Thread t1 = new Thread(pro2,"Producer1");
        Thread p2 = new Thread(pro2,"Producer2");
        Consumer2 consumer  = new Consumer2(items, 10);
//        Thread t2 = new Thread(consumer,"consumer1");
//        Thread t3 = new Thread(consumer,"consumer2");
//        Thread t4 = new Thread(consumer,"consumer3");
        Thread t2 = new Thread(consumer,"consumer1");
        Thread t3 = new Thread(consumer,"consumer2");
        Thread t4 = new Thread(consumer,"consumer3");
        t1.start();
        p2.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        t2.start();
        t3.start();
        t4.start();
        try {
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}

}

class Producer2 implements Runnable{
	Stack<Integer> items;
	int full;
	int count = 0;
	public Producer2(Stack<Integer> items, int full) {
		this.items = items;
		this.full = full;
	}
	public void setItems(Stack<Integer> items) {
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
			synchronized(items) {
				while(items.size() == full) {
					System.out.println(Thread.currentThread().getName() +  " is waiting");
					try {
						items.wait();
						System.out.println(Thread.currentThread().getName() +  " is waking up");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				produce();
				System.out.println(Thread.currentThread().getName() +  " is calling notifyAll");
				items.notifyAll();
				try {
	                // 休眠一段时间
	                Thread.sleep(10);
	            } catch (InterruptedException e) {
	            }			
	        }
		}
		
	}
}

class Consumer2 implements Runnable{
	Stack<Integer> items;
	int full;
	AtomicInteger consumed = new AtomicInteger();
	public Consumer2(Stack<Integer> items, int full) {
		this.items = items;
		this.full = full;
	}
	public void setItems(Stack<Integer> items) {
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
			synchronized (items) {
				
                while (items.isEmpty() && (!theEnd())) {
                    try {
                    	System.out.println(Thread.currentThread().getName()+" is waiting");
                    	items.wait();
                    	System.out.println(Thread.currentThread().getName()+" wake up");
                    } catch (InterruptedException e) {
                        Thread.interrupted();
                    }
                }
                consume(Thread.currentThread().getName());
                System.out.println(Thread.currentThread().getName() + "call notifyAll");
                items.notifyAll();
            }
        }
	}
}