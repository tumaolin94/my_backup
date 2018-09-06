package algorithm.sort;

import java.util.ArrayList;
import java.util.List;

public class Sort {

	private char[] sortWithN2(char[] array){
        //TODO code here
		
		if(array == null || array.length == 0) {
			return new char[0];
		}
		
		int size = array.length;
		
		for(int i = 0; i < size; i++) {
			for(int j = 1; j < size - i; j++) {
				if(array[j-1] > array[j]){

	                char temp;
	                temp = array[j-1];
	                array[j-1] = array[j];
	                array[j]=temp;
	            }
			}
		}
		
        return array;
    }
    
    private char[] sortWithNLogN(char[] array){
          //TODO code here
    	if(array == null || array.length == 0) {
    		return new char[0];
		}
        qsort(array, 0, array.length -1);
        return array;
    }
    
    
    public void qsort(char[] arr, int low, int high) {
		if(low<high) {
			int pivot = partition(arr, low, high);
			qsort(arr, low, pivot-1);
			qsort(arr, pivot+1, high);
			
		}
	}
	
	public int partition(char[] array, int low, int high) {
		char pivot = array[low+(high - low)/2];
		swap(array, low, low+(high - low)/2);
		while(low<high) {
			while(low<high&&array[high]>=pivot) high--;
			array[low] = array[high];
			while(low<high&&array[low]<=pivot) low++;
			array[high]=array[low];
		}
		
		array[low]=pivot;
		
		return low;
	}
	public void swap(char[] arrays, int i, int j) {
		char temp = arrays[i];
		arrays[i] = arrays[j];
		arrays[j] = temp;
	}
    
    
    
    
     private char[] sortWithN(char[] array){
          //TODO code here
    	 if(array == null || array.length == 0) {
    		 return new char[0];
 		}
    	 
    	 int[] counts = new int[26];
    	 
    	 for(char c: array) {
    		 counts[c-'a']++;
    	 }
    	  
    	 int p = 0;
    	 for(int i = 0; i < 26; i++) {
    		 for(int j = p; j < p + counts[i];j++) {
    			 array[j] = (char) (i + 'a');
    		 }
    		 p += counts[i];
    	 }
    	 
        return array;
    }
    
    public static void main(String args[]){
        //test
    	Sort sort = new Sort();
    	char[] array1 = "ctzoqlp".toCharArray();
    	List<char[]> arrays = new ArrayList<>();
    	arrays.add("ctzoqlp".toCharArray());
    	arrays.add("".toCharArray());
    	arrays.add(new char[0]);
    	arrays.add("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbccccccccccccccccccccccccccddddddddddddddddddddddddddddddddeeeeeeeeeeeeeeeeeeeeeeeeeeeeeefffffffffffffff".toCharArray());
    	for(char[] array: arrays) {
    		System.out.println("Origin array is: "+ String.valueOf(array));
        	System.out.println("N2");
        	System.out.println(String.valueOf(sort.sortWithN2(array)));
        	System.out.println("N");
        	System.out.println(String.valueOf(sort.sortWithN(array)));
        	System.out.println("NLogN");
        	System.out.println(String.valueOf(sort.sortWithNLogN(array)));
    	}

    }

}
