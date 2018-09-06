package studyjava;
import java.util.*;
public class Palindrome{
    public static void main(String[] args){
//        Scanner in = new Scanner(System.in);
//        while(in.hasNext()){
//            String str = in.nextLine();
//            System.out.println(solution(str));
//        }
    	System.out.println(solution("abcda"));
    }
    public static int solution(String str){
        char[] cs = str.toCharArray();
        char[] revs = new StringBuilder(str).reverse().toString().toCharArray();
        int[][] dp = new int[cs.length+1][cs.length+1];
        for(int i = 1; i <= cs.length; i++){
            for(int j = 1; j <= cs.length; j++){
                if(cs[i] == revs[j]) {
                    dp[i][j] = dp[i-1][j-1]+1;
                }else{
                     dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return cs.length - dp[cs.length][cs.length];
    }
    
}
