
public class Solution {

	public static void main(String[] args) {
		System.out.println(combine("abcdef","fedha"));
		System.out.println(combine("abcdef","fed"));
		System.out.println(combine("ef","fedha"));
		System.out.println();
	}
	/**
	 * Simple method, compare between the end of String a and the start of String b
	 * Runtime Complexity: O(n), the worst case is that String a equals the reversal of String B
	 * Space Complexity: O(1), only constant variables
	 * @param String a
	 * @param String b
	 * @return the combination of two Strings 
	 * */
	public static String combine(String a, String b) {
		if(a.length() == 0) return b; // avoid cornor case
		if(b.length() == 0) return a;
		int aLength = a.length(); // the length of String a
		int bLength = b.length(); // the length of String b
		int i = aLength - 1;
		int j = 0;
		while(i >=0 && j < bLength &&a.charAt(i) == b.charAt(j)) {
			i--;
			j++;
		}
//		System.out.println(i + "  "+ j+"  "+a.substring(0, i + 1)+"  "+b.substring(j - 1, bLength));
		return a.substring(0, i + 1) + b.substring(j, bLength);
	}


	
}
