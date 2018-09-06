package algorithm.sort;



public class MergeSort {
	public void mergeSort(int[] arr, int[] temp, int low, int high) {
		if(low<high) {
			int middle = low+(high-low)/2;
			mergeSort(arr, temp, low, middle);
			mergeSort(arr, temp, middle+1, high);
			mergeSortedArray(arr, temp, low, middle, high);
		}
	}

	public void mergeSortedArray(int[]arr, int[] temp, int low, int middle, int high) {
		int i= low;
		int j=middle+1;
		int k = 0;
		while(i<=middle&&j<=high) {
			if(arr[i]<=arr[j]) {
				temp[k++] = arr[i++];
			}
			else {
				temp[k++] = arr[j++];
			}
		}
		while(i<=middle) temp[k++] = arr[i++];
		while(j<=high) temp[k++]  = arr[j++];
		for(i=0;i<k;i++) {
			arr[low+i] = temp[i];
		}
	}
	
	  private static void inPlaceSort ( Comparable[] x, int first, int last )
	   {
	      int mid, lt, rt;
	      Comparable tmp;

	      if ( first >= last ) return;

	      mid = (first + last) / 2;

	      inPlaceSort (x, first, mid);
	      inPlaceSort (x, mid+1, last);

	      lt = first;  rt = mid+1;
	      // One extra check:  can we SKIP the merge?
	      if ( x[mid].compareTo(x[rt]) <= 0 )
	         return;

	      while (lt <= mid && rt <= last)
	      {
	         // Select from left:  no change, just advance lt
	         if ( x[lt].compareTo(x[rt]) <= 0 )
	            lt++;
	         // Select from right:  rotate [lt..rt] and correct
	         else
	         {
	            tmp = x[rt];     // Will move to [lt]
	            System.arraycopy(x, lt, x, lt+1, rt-lt);
	            x[lt] = tmp;
	            // EVERYTHING has moved up by one
	            lt++;  mid++;  rt++;
	         }
	      }
	      // Whatever remains in [rt..last] is in place
	   }
	
	public static void main(String[] args) {
		int[] test = {1,2,3,10,7,6,5,4,3,2};
		MergeSort ms = new MergeSort();
		int[] temp = new int[test.length];
		ms.mergeSort(test, temp, 0, test.length-1);
//		ms.inPlaceSort(test, 0, test.length-1);
		for(int i:test) {
			System.out.print(i+",");
		}
	}
}
