package wepay;

import java.util.ArrayDeque;
import java.util.Deque;

public class Alerter {


    public static boolean maxSlidingWindow(int[] a, int k, float f) {		
		if (a == null || k <= 0) {
			return false;
		}
		int presum = 0; // record the sum of current sliding window
		int deleteIndex = 0; // the index of element should be deleted in current window
		float minAvg = Float.MAX_VALUE; // record the minimum average of previous windows
		
		Deque<Integer> q = new ArrayDeque<>(); // A Deque to store index
		for (int i = 0; i < a.length; i++) {
			// remove numbers out of range size
			if (!q.isEmpty() && q.peekFirst() < i - k + 1) {
				q.pollFirst();
			}
			// remove elements smaller than current max in k size
			while (!q.isEmpty() && a[q.peekLast()] < a[i]) {
				q.pollLast();
			}
			q.offer(i);
			presum += a[i];
			// for every sliding windows whose size equals size
			if (i >= k - 1) {
				int max = a[q.peekFirst()];
				
				float avg = (float)(presum * 1.0f)/k; // average of current window
				System.out.println(presum+"   "+k+" avg= "+avg);
				//Alert1
				if(max > avg * f) {
					System.out.println("Alart1: "+max + ">" + avg+"*"+f+"="+avg*f);
					return false;
				}
				//Alert2
				if(minAvg != Float.MAX_VALUE && avg > minAvg * f) {
					System.out.println("Alart2: " + avg +">" + (minAvg)+"*"+f+"="+(minAvg*f));
					return false;
				}
				// save the minimum average of previous windows
				minAvg = Math.min(minAvg, avg);
				// delete elements out of range size
				presum -= a[deleteIndex++];
			}
		}
		return true;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(maxSlidingWindow(new int[]{1,2,3,5,5,5}, 3, 1.5f));
	}

}
