import java.util.*;

public class Kuaishou {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
//        while (in.hasNext()) {//注意while处理多个case
//            String lines = in.nextLine();
//            String[] line = lines.split(" ");
//            int n = line.length;
//            int x = in.nextInt();
//            int[] array = new int[n];
//            for(int i = 0; i < array.length; i++){
//                array[i] = Integer.parseInt(line[i]);
//            }
        PriorityQueue<Integer> minQ = new PriorityQueue<Integer>((i1,i2)->i2-i1);
        minQ.add(1);
        minQ.add(2);
        minQ.peek();
        minQ.of
        System.out.println(minQ.poll());
        System.out.println(minQ.poll());
//            System.out.println(binarySearch(new int[] {3,5},4));
//        }
    }
    
    public static int binarySearch(int[] array, int x){
        int s = 0;
        int e = array.length-1;
        if(array[0] >= x) return s;
        if(array[e] < x) return e+1;
        while (s <= e) {  
            int mid = s + (e - s)/2;  
            if (x < array[mid]) {  
                e = mid - 1;  
            } else if (x > array[mid]) {  
                s = mid + 1;  
            } else {  
                return mid;  
            }  
        }
        return e+1;
    }
    
	public static int cal(int x, int y, int mod) {
		int ans = 1;
        while (y != 0) {  
        	int tmp = y % 10;
            ans = (power(ans, 10, mod) * power(y, tmp, mod)) % mod; 
            y /= 10;
        }  
        return ans;  
	}
	public static int power(int x, int y, int mod) {
	    x %= mod;
	    int res = 1;
	    while (y != 0) {
	        if ((y & 1) != 0) res = res * x % mod;
	        x = x * x % mod;
	        y >>= 1;
	    }
	    return res;
	} 
}
