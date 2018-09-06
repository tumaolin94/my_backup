package studyjava;

import java.util.Arrays;
import java.util.Scanner;
public class Input {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
//        while (in.hasNextInt()) {//注意while处理多个case
//            int n = in.nextInt();
//            int[] arrays = new int[n];
//            for(int i = 0; i < n; i++){
//                arrays[i] = in.nextInt();
//            }
//            solution(arrays, n);
//        }
        solution(new int[] {2,3,2,4,2}, 5);
    }
    
    public static void solution(int[] arrays, int n){
        Arrays.sort(arrays);
        int one = 0;
        int two = 0;
        for(int array: arrays){
            one += array%2;
            two += array - array%2;
        }
//        System.out.println(one+" " +two);
        int res = 1;
//        if(two%one==0) {
//        	System.out.println(two / one +1);
//        }
//        if(one == 1 System.out.println(x);)
//        if(one * 2 > two) {
//        	System.out.println(1);
//        	return;
//        }
        while(one > 0 && two >0) {
        	if(one * 2 > two) {
        		System.out.println(res);
        		return;
        	}
        	two -= one*2;
        	res += 2;
        }
        System.out.println(res);
    }
}