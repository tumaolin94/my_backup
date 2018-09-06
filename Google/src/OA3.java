public class OA3 {
	public static int search(int[] A, int[] B) {
		if(A.length==0) return 0;
		int[] parents = new int[A.length];
		for(int i=0;i<parents.length;i++) {
			parents[i]=-1;
		}
		for(int i=0;i<B.length;i+=2) {
			if(A[B[i]-1]==A[B[i+1]-1]) {
				int temp = B[i+1]-1;
				parents[temp] = B[i]-1;
				System.out.println("parents["+temp+"]= "+parents[temp]);
			}
		}
		int max = 0;
		int[] res = new int[A.length];
		for(int i=parents.length-1;i>=0;i--) {
			if(parents[i]!=-1) {
				max = Math.max(max, res[parents[i]]+res[i]+1);
				res[parents[i]] = Math.max(res[i]+1, res[parents[i]]);
				System.out.println("res["+parents[i]+"]= "+res[parents[i]]);
//				System.out.println(max);
			}
		}
		return max;
	}
	
	public static void main(String[] args) {
		int[] values = {1,1,1,1,3,3,4,4};
		int[] edges = {1,2,1,3,2,4,2,5,3,6,3,7,7,8};
//		System.out.println("result: "+search(values,edges));
		System.out.println("\t\t\tfile.ext\"".lastIndexOf("\t"));
	}
}
