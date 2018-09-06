package practise;

public class Movierating {

	//e.g. [5，9，-1，-3，4，5]->[9,-1,-3,4,5]
	public static int solve(int[] nums) {
		int res = 0;
		int take = 0, skip = 0;
		for(int num: nums) {
			int tempTake = num + Math.max(take, skip);
			skip = take;
			take = tempTake;
		}
		return Math.max(take, skip);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int[] nums = {5,9,-1,-3,4,5};
		int[] nums = {-1,2,-3,-4,-5,-1,5};
		System.out.println(solve(nums));
	}

}
