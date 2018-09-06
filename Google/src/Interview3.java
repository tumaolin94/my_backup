
public class Interview3 {
//	You are given an array of numbers and a positive integer k. You are allowed to move an element of the array at most k position earlier, or any number of positions later. 
//	Given this constraint, you need to make the array as sorted as possible.
//	For example, if k=2 and the input array is 5 4 2 3 1, then the output should be 2 3 1 4 5.
	
	public static void newsort(int[] array, int k) {
		for(int i=1;i<array.length;i++) {
			for(int j=Math.max(0, i-k);j<i;j++) {
				if(array[j]>array[i]) {
					int temp = array[j];
					array[j] = array[i];
					array[i] = temp;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {4,5,2,3,1,6,7};
		newsort(array,4);
		for(int a:array) {
			System.out.print(a+" , ");
		}
	}

}
