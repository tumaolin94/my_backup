package solution.leetcode;

import java.util.Random;

public class TestStatic {
	private   static  Random rand =  new  Random( 47 );  
	static int ranStatic = rand.nextInt(20);
	final int ranFinal = rand.nextInt(20);
	public static void main(String[] args) {
		System.out.println(TestStatic.ranStatic);
		TestStatic ts = new TestStatic();
		ts.ranStatic = 9;
		System.out.println(new TestStatic().ranFinal);
		System.out.println(TestStatic.ranStatic);
	}
}
