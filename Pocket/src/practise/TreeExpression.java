package practise;

import java.util.Stack;

class TreeNode 
{
    char val;
    TreeNode left, right;
 
    public TreeNode(char val) 
    {
        this.val = val;
    }
}
public class TreeExpression {
	
	public static String tree2exp(TreeNode root) {
		if(root == null) return "";
		String res = "";
		if(root.left == null) {
			return root.val+"";
		}
		res = root.val+"?"+tree2exp(root.left)+":"+tree2exp(root.right);
		return res;
	}
	
	public static String treeStackExp(TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		String res = "";
		while(!stack.isEmpty()) {
			TreeNode tmp = stack.pop();
			res += tmp.val;
			if(tmp.left!=null) {
				stack.push(tmp.right);
				stack.push(new TreeNode(':'));
				stack.push(tmp.left);
				stack.push(new TreeNode('?'));
			}
		}
		return res;
	}

    
// Driver program to test above function
    public static void main(String args[]) {
        TreeNode a = new TreeNode('a');
        TreeNode b = new TreeNode('b');
        TreeNode c = new TreeNode('c');
        TreeNode d = new TreeNode('d');
        TreeNode e = new TreeNode('e');
        TreeNode f = new TreeNode('f');
        TreeNode g = new TreeNode('g'); 
        TreeNode h = new TreeNode('h');
        TreeNode i = new TreeNode('i');    
        a.left = b;
        a.right = e;
        b.left = c;
        b.right = d;
        e.left = f;
        e.right = g;
        g.left = h;
        g.right = i;
        System.out.println(tree2exp(a));
        System.out.println(treeStackExp(a));
    }

}
