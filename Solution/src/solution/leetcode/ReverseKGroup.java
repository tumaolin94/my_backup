package solution.leetcode;

public class ReverseKGroup {
	  public class ListNode {
		      int val;
		      ListNode next;
		      ListNode(int x) { val = x; }
		  }
    public void reverseK(ListNode head, int k){
        ListNode tail = head;
        ListNode pre = head ;
        head = head.next;
        ListNode next = head.next;
        while(k>1){
            tail.next = head.next;
            head.next = pre;
            pre = head;
            head = next;
            next = head.next;
            k--;
        }
    }
    
    public static void main(String[] args) {}
}
