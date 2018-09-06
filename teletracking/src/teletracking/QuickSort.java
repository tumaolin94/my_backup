package teletracking;


public class QuickSort {
	public void qsort(int[] arrays, int start, int end) {
		if(start < end) {
			int pivot = partition(arrays, start, end);
			qsort(arrays, start, pivot-1);
			qsort(arrays, pivot+1, end);
		}
	}
	public int partition(int[] arr, int low, int high) {
		int pivot = arr[low + (high - low)/2];
		swap(arr,low, low + (high - low)/2);
		while(low < high) {
			while(low<high && arr[high] > pivot) high--;
			arr[low] = arr[high];
			while(low<high && arr[low] <= pivot) low++;
			arr[high] = arr[low];
		}
		arr[low] = pivot;
		return low;
	}
	public void swap(int[] arrays, int i, int j) {
		int temp = arrays[i];
		arrays[i] = arrays[j];
		arrays[j] = temp;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] test = {1,2,3,10,7,6,5,4,3,2};
		QuickSort qk = new QuickSort();
		qk.qsort(test, 0, test.length-1);
		for(int i:test) {
			System.out.print(i+",");
		}
	}

}
