import java.text.DecimalFormat;
import java.util.*;

public class Qu2 {
    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while (in.hasNextInt()) {//注意while处理多个case
//            int n = in.nextInt();
//            int k = in.nextInt();
//            int[][] as = new int[n][2];
//            for(int i = 0; i < n; i++){
//                as[i][0] = in.nextInt();
//                as[i][1] = in.nextInt();
//            }
//            solution(n, k, as);
//        }
    	int n = 5;
    	int k = 2;
    	int[][] as = new int[n][2];
    	as[0] = new int[] {0,1};
    	as[1] = new int[] {1,3};
    	as[2] = new int[] {3,5};
    	as[3] = new int[] {2,4};
    	as[4] = new int[] {100,101};
    	
//    	Scanner in = new Scanner(System.in);
//        String tmp = in.nextLine();
//        String[] time = tmp.split(":");
//        int hour = Integer.parseInt(time[0]);
//        int min = Integer.parseInt(time[1]);
//        cal(hour, min);
//        solution(n, k, as);
//        cal(18,30);
//        cal(9,15);
//        cal(00,15);
//        cal(00,01);
    	solution(n, k, as);
    }
    public static void solution(int n, int k, int[][] as){
        Arrays.sort(as, (a1, a2) ->{
        	if(a1[0] != a2[0]) return a1[0] - a2[0];
        	return a1[1] - a2[1];
        });
        Map<Integer, Integer> map = new HashMap<>();
//        int[] r = new int[as[as.length-1][1]+1];
//        System.out.println(r.length);
        for(int[] a: as){
            for(int i = a[0];i <= a[1];i++){
//                r[i]++;
                map.put(i, map.getOrDefault(i, 0)+1);
            }
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int key: map.keySet()) {
        	if(map.get(key) >= k) {
        		min = Math.min(min, key);
        		max = Math.max(max, key);
        	}
        }
//        for(int i = as[0][0]; i < r.length; i++){
//            if(r[i] >= k ){
//                min = i;
//                break;
//            }
//        }
//        
//        for(int i = r.length - 1; i >= 0; i--){
//            if(r[i] >= k ){
//                max = i;
//                break;
//            }
//        }
        if(min == Integer.MAX_VALUE || max == Integer.MIN_VALUE){
            System.out.println("error");
            return;
        }
        System.out.println(min+" "+max);
    }
}