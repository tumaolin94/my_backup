package studyjava;

public class Solution2 {
    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        while (in.hasNextInt()) {//注意while处理多个case
//            int n = in.nextInt();
//            int[] as = new int[n];
//            for(int i = 0; i < n; i++){
//                as[i] = in.nextInt();
//            }
//            int t = in.nextInt();
            solution(2, new int[] {1,2}, 2);
        }
    public static void solution(int n, int[] as, int t){
        double sum = 0;
        double area1 = 0;
        for(int i = 0; i < n; i++){
            double tmp = as[i] * as[i] *3.14;
            double circle = tmp - area1;
            int score = n - i;
            sum += score * circle;
            area1 = tmp;
        }
        double e = sum /area1;
        double res = e * t;
        System.out.println(String.format("%.3f", res));
    }
}
