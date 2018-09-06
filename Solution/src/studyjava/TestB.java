package studyjava;

public class TestB {

	private TestInterface t1;
	public void registerInterface(TestInterface t1) {
		this.t1 = t1;
	}
	
	public void doTask() {
		System.out.println("Performing callback before synchronous Task");
		if(t1 != null) {
			t1.functiontest();
		}
	}
	public void doGeekStuff()
    {
 
        // An Async task always executes in new thread
        new Thread(new Runnable() {
            public void run()
            {
 
                // perform any operation
                System.out.println("Performing operation in Asynchronous Task");
 
                // check if listener is registered.
                if (t1 != null) {
 
                    // invoke the callback method of class A
                    t1.functiontest();
                }
            }
        }).start();
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestB tb = new TestB();
		TestInterface ti = new TestA();
		tb.registerInterface(ti);
		tb.doGeekStuff();
	}

}
