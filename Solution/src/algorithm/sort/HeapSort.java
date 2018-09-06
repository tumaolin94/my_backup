package algorithm.sort;

public class HeapSort {

	public void sort(int[] arr) {
		buildheap(arr);
		for(int i=arr.length-1;i>=0;i--) {
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;
			heapify(arr,i,0);
		}
	}
	
	
	public void buildheap(int[] arr) {
		int n = arr.length;
		for(int i=(n/2 -1);i>=0;i--) {
			heapify(arr, n, i);
		}
	}
	
	
	public void heapify(int[] arr, int n, int i) {
		int largest = i;
		int left = i * 2 + 1 ;//left child
		int right = i * 2 + 2 ;//right child
		
		if(left<n&&arr[left]>arr[largest]) {
			largest = left;
		}
		
		if(right<n&&arr[right]>arr[largest]) {
			largest = right;
		}
		//swap
		if(largest!=i) {
			int temp = arr[i];
			arr[i] = arr[largest];
			arr[largest] = temp;
			heapify(arr, n, largest);
		}
		

	}
	
	static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i]+" ");
        System.out.println();
    }
 
    // Driver program
    public static void main(String args[])
    {
        int arr[] = {12, 11, 13, 5, 6, 7,1,2,3,4};
 
        HeapSort ob = new HeapSort();
        ob.sort(arr);
 
        System.out.println("Sorted array is");
        printArray(arr);
//    	int[] a = new int[2];
//    	System.out.println(a[1]);
    }
}
