
public class Solution2 {

	public static void main(String[] args) {

		System.out.println();
		System.out.println(solution2(new int[] {1,2,3,4}, 6));
	}
	
	
	/**
	 * In this problem, there are two steps in my algorithm
	 * Step1: Find the Xth largest element in Array 'a' by the Kth largest element in List 'L'
	 * 		  In the list 'L', there always be 1 2nd largest element, 2 3th largest, ..., n - 1 smallest element, 
	 *        where n is the length of Array 'a'.
	 *        In this part, the Time Complexity is O(n), for worst case, which asks the smallest element in List 'L'
	 * Step2: Find what Xth largest element in Array 'a' exactly is
	 * 		  Using quick selection algorithm, whose Time Complexity is O(n), based on master theorem
	 * @param a, input array
	 * @param k, the Kth largest element in List L
	 * @return return the Kth largest value of L
	 * */
	public static int solution2(int[] a, int k) {
		// Find what position element we should find in Array a
		int x = getXthLargestInArray(a, k);
		System.out.println("x is the "+ x +" largest element in array a");
		// Find the exact element is
		return search(a, 0, a.length-1,a.length-x);
	}
	
	/**
	 * get Xth largest element In Array a
	 * @param a, input array
	 * @param k, the Kth largest element in List L
	 * @return return the position x 
	 * */
	public static int getXthLargestInArray(int[] a, int k) {
		int n = a.length;
		int i = 1;
		System.out.println("k:" + k);
		while(i < n && k > 0) {
			k -= i++;
			
		}
		return i;
	}
	
	/**
	 * quick select algorithm by recursion
	 * @param a, input array
	 * @param start, start position
	 * @param end, end position
	 * @param k, Kth largest element in the array
	 * @return the target value
	 * */
	public static int search(int[] a,int start, int end, int k){
        if(start>=end) return a[start];
        // find the pivot of current area
        int pivot = partition(a, start, end, k);
        System.out.println("pivot " + pivot);
        // find the target element
        if(pivot == k) return a[k];
        // pivot larger than target element
        else if(pivot>k){
            return search(a, start,pivot-1,k);
        // pivot smaller than target element
        }else return search(a,pivot+1,end,k);
    }
	/**
	 * looking for the pivot by excuting one time quick sort
	 * @param a, input array
	 * @param start, start position
	 * @param end, end position
	 * @param pos, current position of this array
	 * @return pivot position
	 * */
    public static int partition(int[] a, int start, int end, int pos){
        int val = a[pos];
        swap(a,pos,end);
        int i = start,j=end-1;
        while(i<j){
            while(i<j&&a[i]<=val) i++;
            while(i<j&&a[j]>val) j--;
            swap(a,i,j);
        }
        if(a[i]>val){
            swap(a,i,end);
            return i;
        }
        swap(a,i+1,end);
        return i+1;
    }
    
    /**
     * swap two elements in array 'a'
     * @param a, input array
     * @param i, position i
     * @param j, position j
     * */
    public static void swap(int[] a, int i, int j){
        int temp = a[i];
        a[i]  = a[j];
        a[j]  = temp;
    }

}
