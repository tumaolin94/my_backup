package practise;

import java.util.List;

class Box{
	int capacity;
	int type = -1;
	public Box(int capacity) {
		this.capacity = capacity;
	}
}
class Thing{
	int size;
	int type;
	int value;
	public Thing(int s, int t, int v) {
		size = s;
		type = t;
		value = v;
	}
}

public class Boxsaving {
	public static int backPack(int[] A, int[] V, int m) {
	    int[][] dp = new int[A.length][m + 1];
	    for (int j = 1; j < m + 1; j++) {
	        if (j == A[0]) {
	            dp[0][j] = V[0];
	        } else {
	            dp[0][j] = 0;
	        }
	        for (int i = 1; i < A.length; i++) {
	            if (j < A[i]) {
	                dp[i][j] = dp[i - 1][j];
	            } else {
	                dp[i][j] = Math.max(dp[i - 1][j - A[i]] + V[i], dp[i - 1][j]);
	            }
	        }
	    }
	    return dp[A.length - 1][m];
	}
    public static int backPackII(int m, int[] A, int[] V) {
        // write your code here
        int[] dp = new int[m+1];
        for(int i=1;i<m+1;i++) {
        	dp[i] = Integer.MAX_VALUE;
        }
        for(int i=0;i<A.length;i++){
            for(int j=m;j>0;j--){
                if(j>=A[i]){
                	if(dp[j-A[i]] == Integer.MAX_VALUE) {
                		continue;
                	}
                    dp[j] = Math.min(dp[j],dp[j-A[i]]+V[i]);
            }
        }
    }
    return dp[m];
    }
    //最小值
    public static int accurateMinValue(int m, int[] A, int[] V) {
    	// write your code here
        int[] dp = new int[m+1];
        for(int i=1;i<m+1;i++) {
        	dp[i] = Integer.MAX_VALUE;
        }
        for(int i=0;i<A.length;i++){
            for(int j=m;j>0;j--){
                if(j>=A[i]){
                	if(dp[j-A[i]] == Integer.MAX_VALUE) {
                		continue;
                	}
                    dp[j] = Math.min(dp[j],dp[j-A[i]]+V[i]);
            }
        }
    }
        for(int d: dp) {
//        	System.out.print(d+" ");
        }
        return dp[m];
    }
    public static int backPackV(int[] A, int[] V,int target) {
        int[] dp = new int[target+1];
        List<Integer>[] lists = new List[10];
        dp[0] = 1;
        for (int i = 0; i < A.length; i++) {
            for (int j = target; j >= 0; j--) {
                if (j >= A[i]) {
                	dp[j] += dp[j-A[i]];
                }
                }
            for(int d: dp) {
//            	System.out.print(d+" ");
            }

            System.out.println();
            }
        return dp[target];
    }
	public static void main(String[] args) {
		System.out.println(accurateMinValue(3,
				new int[] {1,1,1,1},
				new int[] {1,2,3,4}));
		System.out.println(backPackII(3,
				new int[] {1,1,1,1},
				new int[] {1,2,3,4}));
		System.out.println(mostValue(3,
				new int[] {1,1,1,1},
				new int[] {1,2,3,4}));
//		System.out.println(backPackV(new int[] {1,2,3,3,7},new int[] {1,1,1,1,1},
//				7));
	}
	public static int mostValue(int m, int[] A, int V[]) {
        int[] dp = new int[m+1];
        for (int i = 0; i < A.length; i++) {
            for (int j = m; j > 0; j--) {
                if (j >= A[i]) dp[j] = Math.max(dp[j], dp[j-A[i]]+V[i]);
            }
        }
        return dp[m];
    }
}
