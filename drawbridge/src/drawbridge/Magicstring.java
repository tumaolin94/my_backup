package drawbridge;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Magicstring {
	int magicstring(int n ) {
		if(n == 0) return 0;
		Map<Character, String> map = new HashMap<>();
		map.put('a', "e");
		map.put('e', "ai");
		map.put('i', "aeou");
		map.put('o', "iu");
		map.put('u', "a");
		int count = 0;
		Queue<Character> queue = new LinkedList<>();
		for(char c: "aeiou".toCharArray()) {
			queue.offer(c);
		}
		while(!queue.isEmpty()) {
			int size = queue.size();
			if(--n == 0 ) return count + size;
			for(int i=0;i<size;i++) {
				char tmp = queue.poll();
				for(char c: map.get(tmp).toCharArray()) {
					queue.offer(c);
				}
			}
		}
		return count%1000000007;
	}
	int newfunction(int n) {
		Map<Character, String> map = new HashMap<>();
		map.put('a', "e");
		map.put('e', "ai");
		map.put('i', "aeou");
		map.put('o', "iu");
		map.put('u', "a");
		int a = 1, e = 1, i = 1, o = 1, u = 1;
		for (int x = 1; x < n; x++) {
			int at = e+i+u;
			int et = a + i;
			int it = e + o;
			int ot = i;
			int ut = i+ o;
			a = at;
			e = et;
			i = it;
			o = ot;
			u = ut;
			}
			return (a + e + i + o + u)%1000000007;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new Magicstring().newfunction(20));
		System.out.println(new Magicstring().magicstring(20));
	}

}
