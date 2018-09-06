package studyjava;

import java.util.HashMap;
import java.util.Map;

public class Ksubarray {

	public static int solution(int[] arr, int k) {
		Map<Integer, Integer> map = new HashMap<>();
		int res = 0;
//		int[] sum = new int[arr.length+1];
		map.put(0, 1);
		int sum = 0;
		for(int i = 0; i<arr.length;i++) {
			sum = (arr[i]+sum)%k;
			res +=map.getOrDefault(sum, 0);
			map.put(sum, map.getOrDefault(sum, 0)+1);
		}
//		for(int key: map.keySet()) {
//			int tmp = map.get(key);
//			System.out.println(key+" "+tmp);
//			res += tmp*(tmp-1)/2;
//		}
		return res;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(solution(new int[] {3,7,6,4,3},4));
		System.out.println(solution(new int[] {1,2,3,4,5},2));
	}

}
