
public class Huawei1 {
	
	public static void main(String[] args) {
//		System.out.println(cal(0,0));
//		System.out.println(cal(0,1));
//		System.out.println(cal(0,2));
//		System.out.println(cal(0,3));
//		System.out.println(cal(0,4));
//		System.out.println(cal(0,5));
//		System.out.println(cal(20,6));
		cal(1);
		cal(3);
		cal(4);
		
		cal(10);
	}
	public static void test(int n) {    
	       if (n < 1)    
	           return;    
	       int faces = 6;    
	       long[][] dp = new long[2][];    
	       dp[0] = new long[faces * n + 1];    
	       dp[1] = new long[faces * n + 1];    
	       int flag = 0;    
	       for (int i = 1; i <= faces; i++) {
	    	   dp[0][i] = 1; 
	       }
	              
	       for (int k = 2; k <= n; ++k) {    
//	           for (int i = 0; i < k; ++i) {
//	        	   dp[1 - flag][i] = 0;
//	           }    
	                   
	           for (int i = k; i <= faces * k; ++i) {    
	               dp[1 - flag][i] = 0;    
	               for (int j = 1; j <= i && j <= faces; ++j)    
	                   dp[1 - flag][i] += dp[flag][i - j];    
	           }    
	           flag = 1 - flag;    
	       }    
	       double total = Math.pow(faces, n);    
	       System.out.print("["); 
	       for (int i = n; i <= faces * n; i++) {    
	           double ratio = Math.abs((double) dp[flag][i] / total);  
	           String tmp = String.format("%.5f",ratio);
	           System.out.print("["+i+", "+tmp+"]");
	           if(i != faces * n) {
	        	   System.out.print(", ");
	           }
	       }
	       System.out.println("]"); 
//	       [[1, 0.16667], [2, 0.16667], [3, 0.16667], [4, 0.16667], [5, 0.16667], [6, 0.16667]]
	   }   
	public static void cal(int n) {    
	       if (n < 1)    
	           return;    
	       int faces = 6;    
	       long[][] dp = new long[2][];    
	       dp[0] = new long[faces * n + 1];    
	       dp[1] = new long[faces * n + 1];    
	       int flag = 0;    
	       for (int i = 1; i <= faces; i++) {
	    	   dp[0][i] = 1; 
	       }
	              
	       for (int k = 2; k <= n; ++k) {    
	           for (int i = 0; i < k; ++i) {
	        	   dp[1 - flag][i] = 0;
	           }    
	                   
	           for (int i = k; i <= faces * k; ++i) {    
	               dp[1 - flag][i] = 0;    
	               for (int j = 1; j <= i && j <= faces; ++j)    
	                   dp[1 - flag][i] += dp[flag][i - j];    
	           }    
	           flag = 1 - flag;    
	       }    
	       double total = Math.pow(faces, n);    
	       System.out.print("["); 
	       for (int i = n; i <= faces * n; i++) {    
	    	   double ratio = (double) dp[flag][i]*1.0 / total;
	           String tmp = String.format("%.5f",ratio);
	           System.out.print("["+i+", "+tmp+"]");
	           if(i != faces * n) {
	        	   System.out.print(", ");
	           }
	       }
	       System.out.println("]"); 
//	       [[1, 0.16667], [2, 0.16667], [3, 0.16667], [4, 0.16667], [5, 0.16667], [6, 0.16667]]
	   }    
	
	public static int cal2(int y, int w){
        int[] month = new int[]{31, 31,28,31,30,31,30,31,31,30,31,30};

        int count = 0;
        int k = 6;
        for(int i=0;i<y;i++)
        {
            month[2]=28;
            if ( ((1900+i)%4==0 &&(1900+i)%100!=0) ||(1900+i)%400==0)
            {
                month[2]=29;    
            }
            for(int j=1;j<=12;j++)
            {
                    if(i==0 && j==1)    continue;
                    k=(k+month[j-1]%7)%7; 
                    if(k== w)  count++;
            }   
        }
          
        return w == 6? count+1 :count;
    }
	
	public static String cal1(String s, int n) {
        StringBuilder builder = new StringBuilder();
        char[] cs = s.toCharArray();
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
        	if(cs[i]>='0' && cs[i]<='9') continue;
            if (String.valueOf(cs[i]).getBytes().length == 1) {
                sum += 1;
                

                if (sum > n) {
                    break;
                }
                builder.append(cs[i]);
            } else {
                sum += 2;
                if (sum > n) {
                    break;
                }
                builder.append(cs[i]);
            }
        }

        return builder.toString();
    }

}
