package multithread;

import java.util.LinkedList;  
import java.util.concurrent.atomic.AtomicInteger;  
  
/** 
 *  
 * @author Administrator 
 * 
 */  
public class ProducerConsumerByWaitNotify {  
    private static AtomicInteger autoIndex = new AtomicInteger();  
    private static LinkedList<Product> queue = new LinkedList<>();  
  
    class Product {  
        private int index;  
  
        Product() {  
            index = autoIndex.getAndIncrement();  
        }  
  
        public String toString() {  
            return "product__" + index + "   " + super.toString();  
        }  
    }  
  
    class Producer implements Runnable {  
        void produce() {  
            Product p = new Product();  
            queue.add(p);  
            System.out.println("produce " + p.toString());  
        }  
  
        @Override  
        public void run() {  
            while (true) {  
                synchronized (queue) {  
                    if (queue.size() == 50) {  
                        try {  
                            queue.wait();  
                        } catch (InterruptedException e) {  
                            // TODO Auto-generated catch block  
                            e.printStackTrace();  
                        }  
                    } else {  
                        produce();  
                    }  
                    queue.notifyAll();  
                }  
            }  
        }  
    }  
  
    class Consumer implements Runnable {  
  
        void consume() {  
            System.out.println("consume " + queue.remove(0).toString());  
        }  
  
        @Override  
        public void run() {  
            while (true) {  
                synchronized (queue) {  
                    if (queue.size() <= 0) {  
                        try {  
                            queue.wait();  
                        } catch (InterruptedException e) {  
                            // TODO Auto-generated catch block  
                            e.printStackTrace();  
                        }  
                    } else {  
                        consume();  
                    }  
                    queue.notifyAll();  
                }  
            }  
        }  
    }  
  
    public static void main(String[] args) {  
        ProducerConsumerByWaitNotify pcbw = new ProducerConsumerByWaitNotify();  
        Producer producer = pcbw.new Producer();  
        Consumer consumer = pcbw.new Consumer();  
        new Thread(producer).start();  
        new Thread(consumer, "consumer1").start();  
        new Thread(consumer, "consumer2").start();  
    }  
  
}  