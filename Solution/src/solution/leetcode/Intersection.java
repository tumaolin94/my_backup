package solution.leetcode;

import java.util.Stack;

public class Intersection {
	  public class ListNode {
		      int val;
		      ListNode next;
		      ListNode(int x) {
		          val = x;
		          next = null;
		      }
		  }
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA==null||headB==null) return null;
        Stack<ListNode> qa = new Stack<>();
        Stack<ListNode> qb = new Stack<>();
        while(headA!=null){
            qa.push(headA);
            headA = headA.next;
        }
        while(headB!=null){
            qb.push(headB);
            headB = headB.next;
        }
        if(qa.peek()!=qb.peek()) return null;
        ListNode res = new ListNode(0);
        while((!qa.isEmpty()&&!qb.isEmpty())&&(qa.peek()==qb.peek())){
            res = qa.pop();
            qb.pop();
        }
        return res;
    }
	public static void main(String[] arges) {
		Intersection is = new Intersection();
		ListNode headA = is.new ListNode(1);
		ListNode headB = headA;
		is.getIntersectionNode(headA, headB);
	}
}
