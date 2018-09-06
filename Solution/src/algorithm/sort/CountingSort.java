package algorithm.sort;

public // Java implementation of Counting Sort
class CountingSort
{
	//arr is input array. base is calculated range
    public int[] sort(int arr[], int base)
    {
        int n = arr.length;

        for(int i=0;i<n;i++) {
        	System.out.print(arr[i]+" ");
        }
        System.out.println();
        // The output array that will have sorted arr
        int[] output = new int[n];
 
        // Create a count array to store count of inidividul
        // characters and initialize count array as 0
        int count[] = new int[base+1];
        for (int i=0; i<base; i++)
            count[i] = 0;
 
        // store count of each integer
        for (int i=0; i<n; i++)
            ++count[arr[i]];
 
        // Change count[i] so that count[i] now contains actual
        // position of this number in output array
        for (int i=1; i<=base; i++)
            count[i] += count[i-1];
 
        for (int i=0; i<base; i++)
            System.out.print (count[i]+" ");
        // Build the output array
        for (int i = 0; i<n; i++){
//        	System.out.println(i+" "+arr[i]+" "+ count[arr[i]]);
            output[count[arr[i]]-1] = arr[i];
            count[arr[i]]--;
        }
 
        return output;
    }
    
    public static void main(String args[])
    {
        CountingSort ob = new CountingSort();
       int[] arr = {2,5,3,0,2,3,0,3};
       
       int base = 9;
       int[]  res = ob.sort(arr,base);
 
        System.out.print("Sorted integer array is ");
        for (int i=0; i<arr.length; i++)
            System.out.print(res[i]+" ");
    }
}
