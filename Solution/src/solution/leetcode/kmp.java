package solution.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import solution.leetcode.Intersection.ListNode;

public class kmp {
	public int kmpSearch(String str1, String str2,int[] next) {
		int i=0;
		int j=0;
		while(i<str1.length()&&j<str2.length()) {
			if(j == -1||str1.charAt(i)==str2.charAt(j)) {
				i++;
				j++;
			}else {
				j=next[j];
			}
			
		}
		if(j==str2.length()) return i-j;
		return -1;
	}
	
	public void getNext(int next[], String str) {
		int k=-1;
		int j=0;
		next[0]=-1;
		int len = str.length();
		while(j<len-1) {
			if(k==-1||str.charAt(k)==str.charAt(j)) {
				k++;
				j++;
				System.out.println("k="+k+" j="+j);
				next[j]=k;
			}else {
				k=next[k];
			}
		}
		System.out.println(next[len-1]);
	}
	public void get2(int next[], String str) {
//		int k
	}

	
	public static void main(String[] args) {
		String str = "ababcab";
		kmp km = new kmp();
		int[] next = new int[str.length()];
		km.getNext(next, str);
		for(int n:next) {System.out.println(n);}
		
		String str1 = "0123456abababa";
		System.out.println(km.kmpSearch(str1, str, next));
		Queue<ListNode> theQueue = new PriorityQueue<>(new Comparator<ListNode>() {

			@Override
			public int compare(ListNode arg0, ListNode arg1) {
				// TODO Auto-generated method stub
				return arg0.val - arg1.val;
			}
		});
//		PriorityQueue<> pq = new PriorityQueue()
	}
	
   
}
