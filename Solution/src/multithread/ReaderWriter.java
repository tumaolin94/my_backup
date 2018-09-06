package multithread;

public class ReaderWriter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    DateFile df = new DateFile();  
	    Reader r1 = new Reader(1, df);  
	    Reader r2 = new Reader(2, df);  
	    Reader r3 = new Reader(3, df);  
	    Reader r4 = new Reader(4, df);  
	    Writer w1 = new Writer(1, df);  
	    Writer w2 = new Writer(2, df);  
	    Writer w3 = new Writer(3, df);  
	    new Thread(r1, "读者线程 r1").start();  
	    new Thread(r2, "读者线程r2").start();  
	    new Thread(r3, "读者线程r3").start();  
	    new Thread(r4, "读者线程r4").start();  
	    new Thread(w1, "写者线程w1").start();  
	    new Thread(w2, "写者线程w2").start();  
	    new Thread(w3, "写者线程w3").start();  
	}

}

class DateFile {  
    // readCount表示正在读的人数，初值为0,表示还没有人读。  
    private int readerCount;  
    // doreading表示读信号量，当doreading=true时不能进行写操作。  
    private boolean doreading;  
    // dowriting表示写信号量，当dowriting=ture时不能进行读操作。  
    private boolean dowriting;  
  
    public DateFile() {  
        readerCount = 0;  
        doreading = false;  
        dowriting = false;  
    }  
  
    // 线程睡眠，不消耗CPU资源  
    public static void naps() {  
        try {  
            Thread.sleep((int) (4000 * Math.random()));  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
    }  
  
    public synchronized int startRead() {  
        // 开始读操作  
  
        while (dowriting == true) {  
            try {  
                System.out.println(Thread.currentThread().getName()+" is waiting");  
                // 等待写者发出notify  
                wait();  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        }  
  
        System.out.println(Thread.currentThread().getName()+" begin to read!");  
        readerCount++;  
        if (readerCount == 1) {  
            doreading = true;  
        }  
        return readerCount;  
    }  
  
    public synchronized int endRead() {  
        // 结束读操作  
        --readerCount;  
        if (readerCount == 0) {  
            doreading = false;  
        }  
        notifyAll();  
        System.out.println(Thread.currentThread().getName()+" reading done!");  
        return readerCount;  
    }  
  
    public synchronized void startWrite() {  
        // 开始写操作  
  
        while (doreading == true || dowriting == true) {  
            try {  
                System.out.println(Thread.currentThread().getName()+" is waiting");  
                wait();  
                // 等待写者或读者发出notify  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        }  
        System.out.println(Thread.currentThread().getName()+" begin to write!");  
        dowriting = true;  
    }  
  
    public synchronized void endWrite() {  
        // 结束写操作  
        dowriting = false;  
        notifyAll();  
        System.out.println(Thread.currentThread().getName()+" writing done!");  
    }  
}  
class Reader implements Runnable {  
    private int readerNum;  
    private DateFile df;  
  
    public Reader(int readerNum, DateFile df) {  
        this.readerNum = readerNum;  
        this.df = df;  
    }  
  
    public void run() {  
        //while (true) {  
            System.out.println("reader " + readerNum + " comes here");  
            df.naps();  
            df.startRead();  
            df.naps();  
            df.endRead();  
        //}  
    }  
}  

class Writer implements Runnable {  
	  
    private DateFile df;  
    private int writerNum;  
  
    public Writer(int writerNum, DateFile df) {  
        this.df = df;  
        this.writerNum = writerNum;  
  
    }  
  
    public void run() {  
        //while (true) {  
            System.out.println("Writer " + writerNum + " comes here");  
            df.naps();  
            df.startWrite();  
            df.naps();  
            df.endWrite();  
        //}  
    }  
}  