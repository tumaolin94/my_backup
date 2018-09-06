package exception;

import java.io.IOException;

public class TestThrow {
	public static void main(String[] args) throws ArithmeticException{
		try{
			int i = test1();
		}catch(ArithmeticException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e);
		}
		System.out.println("main after");
	}
	
	
	public static int test1() throws Exception{
		System.out.println("before");
		int n = 0;
//		try {
//			n = n/0;
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		n = n/0;
		n = n/0;
		return 1;
	}

}
