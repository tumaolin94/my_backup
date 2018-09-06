import java.util.HashSet;
import java.util.Set;

public class Candy {
	public static int candy(int[] A) {
		int res = 0;
		int len = A.length;
		int n = len/2;
		Set<Integer> set = new HashSet<>();
		for(int a:A) {
			if(!set.add(a)) {
				n--;
			}
		}
		return n<0?set.size():set.size()-n;
	}
	public static void main(String[] args) {
		System.out.println(candy(new int[] {80,80,100000,80,80,80,80,80,80,1234}));
	}
}
