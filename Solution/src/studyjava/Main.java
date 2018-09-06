package studyjava;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
public class Main{
	public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
int n = scan.nextInt();
int m = scan.nextInt();
int c = scan.nextInt();
Map<Integer, ArrayList<Integer>> map = new HashMap<>();
int res = 0;
for(int i = 0;i < n; i++){
    int num = scan.nextInt();
    for(int j = 0; j < num; j++){
        int tmp = scan.nextInt();
        if(!map.containsKey(tmp)){
            map.put(tmp, new ArrayList<>());
        }
        map.get(tmp).add(i);
    }
}
for(Integer key: map.keySet()){
    ArrayList<Integer> lists = map.get(key);
    int pre = lists.get(0);
    for(int i = 1; i<lists.size();i++){
        int cur = lists.get(i);
        if(cur - pre <= m){
            res++;
            break;
        }
        pre = cur;
    }
}
System.out.println(res);
}
    public static int helper(int[] arrays, int[] query, Map<Integer, ArrayList<Integer>> map){
        //System.out.println("size:" + map.size());
        int left = query[0]-1;
        int right = query[1]-1;
        int target = query[2];
        int res = 0;
        ArrayList<Integer> tmp = map.get(target);
        int iL = Collections.binarySearch(tmp, left);
        if(iL < 0) iL = -iL;
        int iR = Collections.binarySearch(tmp,right);
        if(iR < 0) iR = -iR - 1;
        return res;
    }
}