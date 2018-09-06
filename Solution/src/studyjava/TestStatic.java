package studyjava;

public class TestStatic {
	int x;
	static int n = 0;
	static {
		System.out.println("excute TestStatic");
	}
	public static void main(String[] args) {
		// TODO Auto-generated mtinhod stub
		TestStatic ts = new TestStatic();
		System.out.println(TestStatic.n);
		
		System.out.println(ts.x);
	}

}
