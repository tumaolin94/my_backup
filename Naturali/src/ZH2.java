import java.util.*;
public class ZH2 {
    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
        System.out.println(sol(3));
    }
    
    public static int sol2(int n){
        int[] dp = new int[n+1];
        if(n == 0) return 0;
        if(n == 1 ) return 1;
        dp[1] = 1;
        int res = 1;
        int i = 1;
        int min = Integer.MAX_VALUE;
        while(2*i <= n){
            i *= 2;
            res++;
            System.out.println(n+" "+i+" "+res);
            min = n - i + res;
            
        }
        System.out.println("i "+i);
        System.out.println("left" + min);
        min = Math.min(min, res + 2*i - n);
        System.out.println("right " + min);
        return min;
    }
    
    public static int sol(int n){
        if(n == 0) return 0;
        if(n == 1 ) return 1;


//        int[] visit = new int[n-1];
//        int[] steps = new int[n-1];
        Set<Integer> visit = new HashSet<>();
        Map<Integer, Integer> steps = new HashMap<>();
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        while(!q.isEmpty()) {
        	int t=q.poll();
            int moves[]={1,-1,t};
            for(int move : moves) {
            	int tmp = t + move;
            	if(tmp < 0 || tmp > n) continue;
            	if(!visit.contains(tmp)){//未访问的  
            		visit.contains(tmp);
                    q.offer(tmp);  
                    steps.put(tmp, steps.getOrDefault(t, 0)+1);
                }
            	if(tmp == n) {
            		return steps.get(n);
            	}
            }
        }
        return 0;
    }
}
