
public class Skyline {
	public static int solution(int[] A) {
		int count = A[0];
		for(int i=1;i<A.length;i++) {
			if(A[i]>A[i-1]) {
				count+=(A[i]-A[i-1]); 
			}
		}
		return count;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(solution(new int[] {1,3,1,3}));
	}

}
