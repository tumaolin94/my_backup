package studyjava;

import java.util.Date;

public class Child extends Polymorphism{
	@Override
	public void function1() {
		System.out.println("This is Child function1");
	}
	
	public void function3() {
		System.out.println("This is Child function1");
	}
	final Date date = new Date(22222L);
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Child c1 = new Child();
////		c1.function1();
////		c1.function2();
//		Date d1 = c1.date;
//		System.out.println("the value of fDateOfDiscovery of internal class : " + c1.date.getTime());
//		d1.setTime(111111111L);
//        System.out.println("the value of fDateOfDiscovery of internal class : " + c1.date.getTime());
//        System.out.println("the value of date after change its value : " + d1.getTime());
		String s1 = new String("abc");
		String s2 = "abc";
		System.out.println(s1 == s2);
		System.out.println(s1.equals(s2));
		System.out.println(3*0.1 == 0.3);
	}

}
