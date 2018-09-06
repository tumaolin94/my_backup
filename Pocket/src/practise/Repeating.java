package practise;

import java.util.HashSet;
import java.util.Set;

public class Repeating {

	static void printTwoElements(int arr[], int size)
	{
	    System.out.print("The repeating element is ");
	    Set<Integer> missing = new HashSet<>();
	    Set<Integer> repeating = new HashSet<>();
	    for(int i = 0; i < size; i++)
	    {
	        if(arr[Math.abs(arr[i])-1] > 0)
	            arr[Math.abs(arr[i])-1] = -arr[Math.abs(arr[i])-1];
	        else {
	        	System.out.println(Math.abs(arr[i]));
	        }
	            
	    }
	 
	    System.out.print("And the missing element is ");
	    for(int i = 0; i < size; i++)
	    {
	        if(arr[i] > 0)
	            System.out.println(i + 1);
	    }
	}
	 
	/* Driver program to test above function */
	public static void main(String[] args)
	{
	    int arr[] = {7, 4, 5, 5, 5, 6, 2};
	    int n = arr.length;
	    printTwoElements(arr, n);
	}

}
