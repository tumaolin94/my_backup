package practise;

public class Color4 {
	 public static void sortColors(int[] nums) {
		 if(nums.length == 0 || nums.length == 1) return;
	        int zero =0;
	        int two = nums.length-1;
	        
	        for (int i = zero; i <= two; ) {
	            if (nums[i] == 1|| nums[i] == 2) {
	                i++;
	            } else if (nums[i] == 0) {
	                nums[i++] = nums[zero];
	                nums[zero++] = 0;              
	            } else {
	                nums[i] = nums[two];
	                nums[two--] = 3;
	            }
	        }
	        for (int i = zero; i <= two; i++) {
	            if(nums[i] == 2) {
	            	nums[i--] = nums[two];
	            	nums[two--] = 2;
	            }
	        }
	        for(int num: nums) System.out.print(" "+num);
	        System.out.println();
	    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		sortColors(new int[] {3,2,1,0,3,3,3,2,2,2,1,1,1,3,3,3,3,1,2,1,2,2,0,0,0,0});
		sortColors(new int[] {2,1,3});
		sortColors(new int[] {1,0});
		sortColors(new int[] {3,1});
		sortColors(new int[] {0});
	}

}
