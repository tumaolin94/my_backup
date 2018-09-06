import java.util.Arrays;

public class AngryChildren {
	public static int find(int[] arr, int k) {
		Arrays.sort(arr);
		int res = Integer.MAX_VALUE ;
		for(int i = 0;i + k < arr.length;i++) {
			res = Math.min(res, arr[i + k -1]-arr[i]);
		}
		return res;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(find(new int[] {10,100,300,50,1000,20,30},3));
	}

}
