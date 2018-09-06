package studyjava;

public class TestA implements TestInterface{

	@Override
	public void functiontest() {
		System.out.println("Performing callback after synchronous Task");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a = "123";
		String b = new String(a);
		String c = new String(b);
		System.out.println(a == b);
		System.out.println(b == c);
	}

}
