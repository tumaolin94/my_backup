
public class PrePermutation {
	public static int solution(int[] A){
        int sum = 0;
        int valueCount = 0;
        int count = 0;

        for(int i = 0; i< A.length; i++){
        	sum+= (i + 1);
            valueCount += A[i];
            if(sum == valueCount){
                count++;
            }
        }
        return count;

    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(solution(new int[]{2,1,3,4,5}));
	}

}
