
public class OA1 {
	
	public static int solution(String A, String B) {
		if(A.length()==0||B.length()==0) return -1;
		int count = 0;
		int n1 = A.length(),n2 = B.length();
		int countB = 0;
		int countA = 0;
		while(countB<n2) {
			while(countA<n1&&A.charAt(countA)!=B.charAt(countB)) countA++;
			if(countA==n1) return -1;
			count = countA;
			while(countB<n2&&A.charAt(countA%n1)==B.charAt(countB)) {
				count++;
				countA++;
				countB++;
			}
			
			//这个是用来解决 dadddd dddd and da dadadadax
			if(countB!=n2) {
				if(countA<n1)countB=0;
				else return -1;
			}
		}
		System.out.println("count "+count);
		return (count%n1==0)?count/n1:count/n1+1;
	}
	
	public static void main(String[] args) {
		String A =  "da";
		String B = "dax";
		System.out.println("result: "+solution(A,B));
	}
}
