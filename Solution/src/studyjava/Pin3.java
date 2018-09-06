package studyjava;
import java.text.DecimalFormat;
import java.util.*;
public class Pin3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        int[][] as = new int[n][2];
//        for(int i = 0; i < n; i++){
//            as[i][0] = in.nextInt();
//            as[i][1] = in.nextInt();
//        }
//        int n = 5;
//        int[][] as = new int[n][2];
//    	as[0] = new int[] {0,0};
//    	as[1] = new int[] {0,1};
//    	as[2] = new int[] {1,0};
//    	as[3] = new int[] {1,1};
//    	as[4] = new int[] {2,0};
//    	for(int[] a:as) {
//    		System.out.println(a[0]+" "+a[1]);
//    	}
//        cal(n, as);
//        System.out.println(slide(as[0], as[3]));
//        System.out.println(slide(as[3], as[4]));
        int n = 8;
        int k = 3;
        int[] as = new int[] {1,3,-1,-3,5,3,6,7};
        cal(n,k,as);
    }
//    public static void cal(int n, int[][] as) {
//        Arrays.sort(as, (a1, a2) ->{
//        	if(a1[0] != a2[0]) return a1[0] - a2[0];
//        	return a1[1] - a2[1];
//        });
//    	for(int[] a:as) {
//    		System.out.println(a[0]+" "+a[1]);
//    	}
//        Map<Double, Integer> map = new HashMap<>();
//        int res = 0;
//    	for(int i = 0; i < n-2; i++){
//            for(int j = i+1; j < n-1; j++){
//                double a = slide(as[i], as[j]);
//                for(int k = j+1; k < n; k++){
//                    double b = slide(as[i], as[k]);
//                    if(a != b) {
//                    	res++;
//                    	System.out.println(as[i][0]+","+as[i][1]+" "+as[j][0]+","+as[j][1]+" "+as[k][0]+","+as[k][1]);
//                    }
//                }
//            }
//        }
//        System.out.println(res);
//    }
//    public static double slide(int[] a, int[] b){
//        return Math.abs((double)(b[1] - a[1])*1.0/(1.0*(b[0] - a[0])));
//    }
    public static int[] cal(int n, int k, int[] a) {
    	if (n == 0 || k <= 0) {
			return new int[0];
		}
    	int[] res = new int[n-k+1];
		int ri = 0;
		Deque<Integer> maxQueue = new ArrayDeque<>();
		Deque<Integer> minQueue = new ArrayDeque<>();
		for (int i = 0; i < n; i++) {
			// remove numbers out of range k
			if (!maxQueue.isEmpty() && maxQueue.peekFirst() < i - k + 1) {
				maxQueue.pollFirst();
			}
			if (!minQueue.isEmpty() && minQueue.peekFirst() < i - k + 1) {
				minQueue.pollFirst();
			}
			// remove smaller numbers in k range as they are useless
			while (!maxQueue.isEmpty() && a[maxQueue.peekLast()] < a[i]) {
				maxQueue.pollLast();
			}
			while (!minQueue.isEmpty() && a[minQueue.peekLast()] > a[i]) {
				minQueue.pollLast();
			}
			// q contains index... r contains content
			maxQueue.offer(i);
			minQueue.offer(i);
			if (i >= k - 1) {
				res[ri++] = a[maxQueue.peekFirst()] -a[minQueue.peekFirst()];
//				System.out.println(x);
			}
		}
		for(int i = 0; i < res.length;i++) {
			System.out.print(res[i]);
			if(i < res.length-1){System.out.print(" ");}
		}
		return res;
    }
    public int[] maxSlidingWindow(int[] a, int k) {		
		if (a == null || k <= 0) {
			return new int[0];
		}
		int n = a.length;
		int[] r = new int[n-k+1];
		int ri = 0;
		// store index
		Deque<Integer> q = new ArrayDeque<>();
		for (int i = 0; i < a.length; i++) {
			// remove numbers out of range k
			if (!q.isEmpty() && q.peekFirst() < i - k + 1) {
				q.pollFirst();
			}
			// remove smaller numbers in k range as they are useless
			while (!q.isEmpty() && a[q.peekLast()] < a[i]) {
				q.pollLast();
			}
			// q contains index... r contains content
			q.offer(i);
			if (i >= k - 1) {
				r[ri++] = a[q.peekFirst()];
			}
		}
		return r;
	}
    
}
