package studyjava;

class ObjectOne {
	int num = 0;
}

public class Test {
	public static void changeValue(ObjectOne ob) {
		ob = new ObjectOne();
		ob.num = 10;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		ObjectOne ob = new ObjectOne();
//		changeValue(ob);
//		System.out.println(ob.num);
		Integer i1 = new Integer(1);
		Integer i2 = new Integer(1);
		int i3 = 1;
		System.out.println(i1 == i2);
		System.out.println(i1 == i3);
		System.out.println(i1.equals(i2));
	}

}
