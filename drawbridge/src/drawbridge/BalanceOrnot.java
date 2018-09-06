package drawbridge;

public class BalanceOrnot {
	int[] balanceOrnot(String[] strs, int[] maxReplacement) {
		return new int[0];
	}
	
	int helper(String str, int k) {
		int count = 0;
		for(char c: str.toCharArray()) {
			
			if(c == '<') {
				count++;
			}else {
				count--;
			}
			if(count<0) {
				k--;
				count++;
			}
		}
		return (count == 0 && k>=0)?1:0;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BalanceOrnot b = new BalanceOrnot();
		System.out.println(b.helper("<<>",2));
	}

}
