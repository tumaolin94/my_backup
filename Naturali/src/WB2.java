import java.util.*;

public class WB2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(cal3(6));
	}
	
    public static long cal3(int n){
    	
        int res = 0;
        for(int i = 0; i <= n; i++){
        	if(isP(i)) res++;
        }
        return res;
    }
    
    public static boolean isP(int n){
    	int bit = Integer.bitCount(n);
        if(n <=1) return true;
        Stack<Integer> s = new Stack<>();
        StringBuilder str = new StringBuilder();
//        System.out.println(n);
        while(bit > 0){
        	if((n & 1) == 1){
            	bit--;
                str.append(1);
            }else{
            	str.append(0);
            }
        	n >>= 1;
            
        }
        int i = 0;
        int j = str.length()-1;
        while(i < j){
        	if(str.charAt(i++) != str.charAt(j--)) return false;
        }
        return true;
    }
	
	
	public static long cal(int a, int b, int c){
		System.out.println(1<<a);
		System.out.println(1<<b);
		System.out.println(-(1<<c));

		int max = Math.max(a, b);
		int min = Math.min(a, b);
		if(c <= min) {
			return min - c + 1;
		}else if( c>= max) {
			return Long.bitCount(-(1<<c))+2;
		}else {
			return max - c + 1;
		}
//		System.out.println("a  " + Integer.bitCount(1<<a));
//		System.out.println("b  " + Integer.bitCount(1<<b));
//		System.out.println("c  " + Integer.bitCount(-(1<<c)));
////		a = 1<<a;
////		b = 1<<b;
////		c = -(1<<c);
////    	System.out.println(a+b+c);
//        return Long.bitCount(a+b+c);
    }
	
    public static int cal2(int n){
    	if(n <= 1) return 0;
        boolean[] notprime = new boolean[n+1];
        isPrime(n, notprime);
        for(boolean not: notprime) {
        	System.out.print(not+", ");
        }
        System.out.println();
        Set<Integer> res = new HashSet<>();
        for(int i = 2; i <= n; i++){
        	if(notprime[i] == false){
            	int p = 1;
                while(Math.pow(i,p) <= n){
//                	if(res.add((int) Math.pow(i,p))) {
//                		System.out.println((int)Math.pow(i,p));
//                		p++;
//                	}
                	res.add((int) Math.pow(i,p));
                	p++;
                }
            }
        }
        List<Integer> list = new ArrayList<>(res);
        Collections.sort(list);
        for(int i: list) {
        	System.out.println(i);
        }
        return res.size();
    }
    
    public static int isPrime(int n, boolean[] notprime) {
        if(n<3) return 0;
        int res =n/2;
        for (int i = 2; i <= n; i++) {
            if (notprime[i] == false) {
                for (int j = 2; i*j <= n; j++) {
                	notprime[i*j] = true;
                }
            }
        }
        return res;
    }
}
