package studyjava;

import java.util.Scanner;
public class T1 {
    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
        //while (in.hasNextInt()) {//注意while处理多个case
            int n = 10;
            int m = 1;
            System.out.println(s2(n,m));
            System.out.println(solution(n,m));
        //}
    }
    public static int s2(int n, int m){
        int d = -1;
        int res = 0;
        for(int i = 1; i <= n; i++){
            
            res += d*i;
            if((i)%m == 0){
                d *= -1;
            }
        }
        return res;
    }
    public static int solution(int n, int m){
        int d = -1;
        int res = 0;
        for(int i = 1; i <= n; i+=m){
            
            System.out.println(res);
            res += d*(2*i+m-1)*m/2;
//            if((i)%m == 0){
//                d *= -1;
//            }
            d *= -1;
        }
        return res;
    }
}
