
public class Solution3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(solution3(new int[] {0,2,1,8,4,9},2));
	}

	/**
	 * Using Dynamic Programming to solve this problem
	 * Let 'sell' means, the max profit when sell apple at ith day.
	 * Let 'buyOrHold' means, the max profit when buy an 
	 * apple or hold an apple at ith day.
	 * Run time Complexity: O(n), where n is the length of prices
	 * Because only one for loop in this method.
	 * 
	 * Space Complexity: O(1), only two extra variable.
	 * 
	 * @param prices, price of an apple on the i-th day
	 * @param p, extra dollars for buying an apple
	 * @return the max profit of the whole deal
	 * */
	public static int solution3(int[] prices, int p) {
		if(prices.length == 0) return 0;
        int sell = 0, buyOrHold = -prices[0];
        for (int i = 1; i < prices.length; i++) {
        	sell = Math.max(sell, buyOrHold + prices[i] - p);
        	buyOrHold = Math.max(buyOrHold, sell - prices[i]);
        }
        return sell;
    }
}
