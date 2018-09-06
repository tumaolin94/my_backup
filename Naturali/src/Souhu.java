
import java.util.*;
public class Souhu {
	public static int cal(int a, int b, int c){
    	long sum = (long) (Math.pow(2,a)+Math.pow(2,b)-Math.pow(2,c));
        return Long.bitCount(sum);
    }
    public static void main(String[] args) {
    	System.out.println(cal(2,3,1));
//        Scanner in = new Scanner(System.in);
//        while (in.hasNextInt()) {//注意while处理多个case
//        	int n = in.nextInt();
//            int k = in.nextInt();
//            int[] array = new int[n];
//            for(int i = 0; i < n; i++){
//                array[i] = in.nextInt();
//            }
//        cal(n,k,array);
//            int n = in.nextInt();
//            Map<Integer, Integer> daily = new HashMap<>();
//            Map<Integer, Integer> mission = new HashMap<>();
//            for(int i = 0; i < n; i++){
//                int method = in.nextInt();
//                if(method == 1){
//                    int start = in.nextInt();
//                    int end = in.nextInt();
//                    int score = in.nextInt();
//                    for(int k = start; k <= end; k++){
//                        daily.put(k, Math.max(daily.getOrDefault(k, Integer.MIN_VALUE), score));
//                    }
//                }else{
//                    int day = in.nextInt();
//                    int score = in.nextInt();
//                    mission.put(i, mission.getOrDefault(i,0)+score);
//                }
//            }
//            cal(daily, mission);
//        }
    }
    public static void cal(Map<Integer, Integer> daily, Map<Integer, Integer> mission){
        long res = 0;
    	for(Integer value: daily.values()) {
        	res += value;
        }
    	for(Integer value: mission.values()) {
        	res += value;
        }
    	System.out.println(res);
    }
    public static void cal(int n, int k, int[] array){
        PriorityQueue<Integer> pq = new PriorityQueue<>((i1, i2)->{return i2 - i1;});
        
        for(int a: array){
            pq.offer(a);
            if(pq.size() > k) pq.poll();
        }
        int[] res = new int[k];
        for(int i = 0; i < k; i++){
            res[i] = pq.poll();
        }
        for(int i = k-1; i>0; i--){
            System.out.print(res[i]+",");
        }
        System.out.println(res[0]);
    }
}


