import java.text.DecimalFormat;
import java.util.Arrays;

public class YJ1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] a = new int[3][2];
		a[0][0] = 18;
		a[1][0] = 15;
		a[2][0] = 10;
		a[0][1] = 75;
		a[1][1] = 72;
		a[2][1] = 45;
		System.out.println(sol(3, 20,a));
	}
	public static double sol(int n, int d, int[][] a){
        Arrays.sort(a, (a1, a2)->{
        	double d2 = (double)a2[1]*1.0/a2[0];
        	double d1 = (double)a1[1]*1.0/a1[0];
        	if(d2 > d1) return 1;
        	if(d2 < d1) return -1;
        	else return 0;
        });
        double[] res = new double[n];
        for(int i = 0; i < n; i++) {
        	res[i] = (double)a[i][1]*1.0/a[i][0];
        	System.out.println(res[i]);
        }
        int sum = 0;
        double r = 0.0;
        for(int i = 0; i < n && sum < d; i++) {
        	if(sum + a[i][0] <= d) {
        		sum += a[i][0];
        		r += a[i][1];
        	}else {
        		int tmp = d - sum;
        		sum += tmp;
        		r += tmp * res[i];
        	}
        	System.out.println(r);
        }

        return Double.parseDouble(String.format("%.2f",r));
    }

	
}
