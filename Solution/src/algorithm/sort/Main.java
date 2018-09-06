package algorithm.sort;
import java.util.Scanner;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {//??while????case
            int n = in.nextInt();
            int[][] as = new int[n][2];
            for(int i = 0; i < n; i++){
                as[i][0] = in.nextInt();
                as[i][1] = in.nextInt();
            }
            solution(as);
        }
    }
    public static void solution(int[][] as){
        Map<Integer, Boolean> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        int[] lucky = new int[] {4,
				 7,
				 44,
				 47,
				 74,
				 77,
				 444,
				 447,
				 474,
				 477,
				 744,
				 747,
				 774,
				 777};
        for(int i: lucky){
            list.add(i);
            map.put(i, true);
        }
        for(int[] a: as){
            int sum = 0;
            for(int i = a[0];i <= a[1]; i++){
                if(isLucky(i, list, map)){
                    sum += 1 ;
                }
            }
            System.out.println(sum);
        }
    }
    public static boolean isLucky(int n, List<Integer> list, Map<Integer, Boolean> map){
        if(n < 4)return false;
        if(map.containsKey(n) && map.get(n) == true) return true;
        for(int i = 0; i < list.size() && list.get(i) < n; i++){
            if(n % list.get(i) == 0 ){
                if(list.contains(n / list.get(i))){
                    map.put(n , true);
                    return true;
                }else{
                    return isLucky(n / list.get(i), list, map);
                }
            }
        }
        return false;
    }

}