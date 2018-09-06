import java.util.Scanner;
import java.util.Stack;

public class S1 {
    public static void main(String[] args){
////        Scanner scan = new Scanner(System.in);
////        while(scan.hasNext()){
////            String str = scan.nextLine();
////            System.out.println(str.length()-getResult(str));
////        }
////    	String str = "XXA";
//////    	String str = "XXA";
////    	System.out.println(getResult(str));
////    	String s = in.nextLine();
//    	String s = "())(";
//    	String s2 = "()()";
////        boolean b = sol(s);
//        System.out.println(sol(s2));
//        System.out.println(sol2(s2));
    	Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        for(int i = 0; i < n; i++){
//            String s = in.next();
//            System.out.println(sol(s));
//        }
        System.out.println(sol("))(("));
    }
    public static int getResult(String str){
        StringBuilder sb  = new StringBuilder(str);
        String newStr = sb.reverse().toString();
        char[] c1 = str.toCharArray();
        char[] c2 = newStr.toCharArray();
        int n = str.length();
        int[][] dp = new int[n+1][n+1];
        for(int i=1;i<n+1;i++){
            for(int j=1;j<n+1;j++){
                if(c1[i-1]==c2[j-1]){ //此处应该减1.
//                    dp[i][j]=dp[i-1][j-1]+1;
                	dp[i][j]=dp[i-1][j-1];
                }else{
                    dp[i][j]=dp[i-1][j]+dp[i][j-1];
                }
            }
        }
        return dp[n][n];
    }
    
    public static boolean sol(String s) {
    	Stack<Character> stack = new Stack<>();
    	int n = s.length();
    	char[] c = s.toCharArray();
    	for(int i = 0; i < n; i++) {
    		if(c[i] == '(')
    			stack.push(c[i]);
    		else {
    			if(stack.size() > 0 && stack.peek() == '(')
    				stack.pop();
    			else stack.push(c[i]);
    		}
    	}
    	if(n % 2 == 1 || n < 2 || stack.size() > 4)
    		return false;
    	else if(n == 2 && stack.size() == 0) return false;
    	else return true;
    	
    }
    public static String sol2(String s) {
    	Stack<Character> stack = new Stack<>();
    	int n = s.length();
    	char[] c = s.toCharArray();
    	int l = 0;
    	int r = 0;
    	for(int i = 0; i < n; i++) {
    		if(c[i]=='(') {
    			l++;
    		}else {
    			if(l>0) l--;
    			else r++;
    		}
    	}
    	if(n == 2 && r == 0) return "No";
    	if(n == l && r <= 2) return "Yes";
    	else return "No";
    	
    	
    }
    int judge(char[] s){
    	Stack<Character> stack = new Stack<>();
    	int n = s.length;
        for(int i=0; i<n; i++)
        {
            if(s[i] == '(')
            	stack.push(s[i]);
            else
            {
                if(!stack.empty())
                	stack.pop();
                else
                    return 0;
            }
        }

        if(!stack.empty())
            return 0;
        return 1;
    }
//    public static String sol3(String s) {
//    	int n = s.length();
//    	char[] c = s.toCharArray(); 
//    }
}
