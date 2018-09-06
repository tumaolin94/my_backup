import java.util.Arrays;

public class SortSubstring {
	public static int solution(int[] A) {
		int res = 0;
		int temp =0;
		Arrays.sort(A);
		for(int i=1;i<A.length;i++) {
			if(A[i]<A[i-1]) {
				temp = i-1;
				while(i<A.length&&A[i]<=A[temp]) {
					i++;
				}
				System.out.println(temp+"   "+i);
				res += i- temp;
			}
		}
		return res;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(printUnsorted(new int[] {1,2,6,1,2,2,9}));
//		System.out.println(solution(new int[] {1,2,6,1,2,2,9}));
	}

	static int printUnsorted(int A[]){
		int n = A.length;
		int s = 0, e = n-1, i, max, min;   
      
       
      // step 1(a) of above algo
      for (s = 0; s < n-1; s++)
      {
        if (A[s] > A[s+1])
          break;
      }
      if (s == n-1)
      {
        System.out.println("The complete array is sorted");
        return 0;
      }
       
      // step 1(b) of above algo
      for(e = n - 1; e > 0; e--)
      {
        if(A[e] < A[e-1])
          break;
      }
       
      // step 2(a) of above algo
      max = A[s]; min = A[s];
      for(i = s + 1; i <= e; i++)
      {
        if(A[i] > max)
          max = A[i];
        if(A[i] < min)
          min = A[i];
      }
       
      // step 2(b) of above algo
      for( i = 0; i < s; i++)
      {
        if(A[i] > min)
        {  
          s = i;
          break;
        }      
      } 
       
      // step 2(c) of above algo
      for( i = n -1; i >= e+1; i--)
      {
        if(A[i] < max)
        {
          e = i;
          break;
        } 
      }  
           
      // step 3 of above algo
      System.out.println(" The unsorted subarray which"+
                         " makes the given array sorted lies"+
                       "  between the indices "+s+" and "+e);
      return e - s + 1;
    }
}
