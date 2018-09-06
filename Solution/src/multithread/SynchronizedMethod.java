package multithread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SynchronizedMethod implements Runnable{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> list = new LinkedList<>();
		SynchronizedMethod s1 = new SynchronizedMethod(list);
		Thread t1 = new Thread(s1, "t1");
		Thread t2 = new Thread(s1, "t2");
		t1.start();
		t2.start();
	}

	
	List<Integer> list ;
	public SynchronizedMethod(List<Integer> list) {
		this.list = list;
	}
	public synchronized void add() {
		list.add(1);
		System.out.println(Thread.currentThread().getName() +" "+ list.size());	
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(list.size()<20) {
			add();
		
		}

	}

}
