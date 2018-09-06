
public class AllCountPalindrome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(countPS("aba"));
	}
	
	public static int sol(String s) {
		char[] cs = s.toCharArray();
		int n = cs.length;
		
		int[][] dp = new int[n+1][n+1];
		
		for(int i = 0; i < n; i++) {
			for(int j = i; j<n;j++) {
				if(i == j) dp[i+1][j+1] = 1;
				else if(cs[i] == cs[j]) {
					dp[i+1][j+1] = dp[i+2][j+1]+dp[i+1][j]+1;
				}else {
					dp[i+1][j+1] = dp[i][j+1]+dp[i+1][j] - dp[i+2][j];
				}
			}
		}
		return dp[0][n];
	}
	static int countPS(String str)
    {
        int N = str.length();
      
        // create a 2D array to store the count
        // of palindromic subsequence
        int[][] cps = new int[N+1][N+1];
      
        // palindromic subsequence of length 1
        for (int i = 0; i < N; i++)
            cps[i][i] = 1;
      
        // check subsequence of length L is 
        // palindrome or not
        for (int L=2; L<=N; L++)
        {
            for (int i = 0; i < N; i++)
            {
                int k = L + i - 1;
                if (k < N){
                if (str.charAt(i) == str.charAt(k))
                    cps[i][k] = cps[i][k-1] +
                                cps[i+1][k] + 1;
                else
                    cps[i][k] = cps[i][k-1] +
                                cps[i+1][k] -
                                cps[i+1][k-1];
                //如果相等，则中间不用去重，因为有中间存在、中间不存在两种情况
                //如果不等，则中间必须去重
                System.out.println("L: "+L+" i:"+i+" k: "+k+" dp: "+cps[i][k]);
                }
                
            }
        }
      
        // return total palindromic subsequence
        return cps[0][N-1];
    }
}
