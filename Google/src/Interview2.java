import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Interview2 {
	public static String findLong(String str, int k) {
		String res = new String();
		int max = 0;
		Map<Character, Integer> map ;
		char[] ss = str.toCharArray();
		int i = 0;
		int num = 0;
		int[] count = new int[256];
        for (int j = 0; j < ss.length; j++) {
            if (count[ss[j]]++ == 0) num++;
            if (num > k) {
                while (--count[ss[i++]] > 0);
                num--;
            }
            if(j!=ss.length&&max<j-i) {
            	max = j-i;
            	res = new String(ss,i,j-i+1);
            }else if(j==ss.length&&max<j-1) {
            	res = new String(ss,i,j-i);
            }
        }
		return res;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(findLong("abcacdddd",3));
	}

}
