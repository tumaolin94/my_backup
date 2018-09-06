import java.util.*;

public class Jingchi2{

    static int max = 0;
	public static void main(String[] args){
    	Scanner in = new Scanner(System.in);
    	String str = in.next();
    	System.out.println(str);
//		int n = in.nextInt();	
//        int[] value = new int[n];
//
//        for(int i = 0; i < n; i++){
//			value[i] = in.nextInt();        	
//        }
		int n = 4;
		int[] value = new int[] {3,1,5,8};
//        System.out.println(cal(value,n));
    }
    public static int cal(int[] value, int n){
    	if(n == 0) return 0;
        boolean[] visit = new boolean[n];
        int count = n;
        int sum = 0;
        helper(value, n, visit, count, 0);
        return max;
    }

	public static void helper(int[] value, int n, boolean[] visit, int count, int sum){
    	System.out.println(count+" "+sum);
		if(count == 0){
        	max = Math.max(sum, max);
        }
        
        for(int i = 0; i < n; i++){
        	if(visit[i] == false){
            	int left = 1;
                int index = i-1;
                while(index>=0){
                	if(visit[index] == false){
                    	left = value[index];
                        break;
                    }
                    index--;
                }
                int right = 1;
                index = i+1;
                while(index<n){
                	if(visit[index] == false){
                    	right = value[index];
                        break;
                    }
                    index++;
                }
                int tmp = left * value[i] * right;
                visit[i] = true;

                helper(value, n, visit, count-1, sum + tmp);
                visit[i] = false;
            }
        }
    }
    
}