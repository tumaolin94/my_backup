package studyjava;
import java.util.*;
public class T2 {
    public static void main(String[] args) {
        System.out.println(solution2(100000));

    }

    public static int solution2(int n){
    	if(n <= 2) return 0;
        int res = 0;
        int one = 1;
        int two = 2;
        Set<Integer> set = new HashSet<>();
        for(int i = 3; i <= n; i++){
        	if(one + two > i){
            	res++;
            	System.out.println("delete:"+ i);
            }else{
                int tmp = one + two;
            	one = two;
                two = tmp;
               	//while(set.contains(++two));
                System.out.println(one+" "+two);
            }
        }
        return res;
    }
    public static long solution(int k, int a, int x, int b, int y){
        Map<Integer, Integer> mapA = new HashMap<>();
        Map<Integer, Integer> mapB = new HashMap<>();
        Map<Long, Long>  factorial = new HashMap<>();
        int res = 0;
        for(int i = 1; i <=x;i++){
            mapA.put(a*i, i);
        }
        for(int i = 1; i <=y;i++){
            mapB.put(b*i, i);
        }
        for(int i = 0; i <= k; i++){
            if(mapA.containsKey(i) && mapB.containsKey(k-i)){
                res += (cal(mapA.get(i), x, mapB.get(k-i), y, factorial))%1000000007;
            }
        }
        return res;
    }
    
    public static long cal(int a, int x, int b, int y, Map<Long, Long>  factorial){
        return calF(x, factorial)/(calF(a, factorial)*calF(x-a, factorial)) + calF(b, factorial)/(calF(b, factorial)*calF(y-b, factorial));
    }
    public static long calF(long number, Map<Long, Long>  factorial) {
    	long res = 1;
    	if(factorial.containsKey(number)){
            return factorial.get(number);
        }
        if (number <= 1)
            return 1;
        else{
            res = number * calF(number - 1, factorial);
            factorial.put(number, res);
        }
        return res;
    }
}
