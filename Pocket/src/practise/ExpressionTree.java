package practise;

public class ExpressionTree {
	class TreeNode 
	{
	    char val;
	    TreeNode left, right;
	 
	    public TreeNode(char val) 
	    {
	        this.val = val;
	    }
	}
    // Function to convert Ternary Expression to a Binary
    // Tree. It return the root of tree
	int i = 0;
	TreeNode convertExpression(char[] expression){
        if(i == expression.length) return null;
        
        TreeNode root = new TreeNode(expression[i++]);
        
        if(i<expression.length && expression[i]=='?') {
        	i++;
        	root.left = convertExpression(expression);
        	System.out.println(root.val+" left "+root.left.val);
        	i++;
        	root.right = convertExpression(expression);
        	System.out.println(root.val+" right "+root.right.val);
        }

        	
        
        
        return root;
    }
    
	int eti=0;
	TreeNode exp_2_tree(String s)
	{
	    if(eti==s.length())
	        return null;
	    TreeNode r=new TreeNode(s.charAt(eti));
	    eti++; 
	    if(eti<s.length() && s.charAt(eti)=='?')
	    {
	        eti++;
	        r.left=exp_2_tree(s);
	        eti++;
	        r.right=exp_2_tree(s);
	        System.out.println("sss"+r.val+" left "+r.left.val);
	        System.out.println("sss"+r.val+" right "+r.right.val);
	    }
	        return r;
	}
	
	
    // function print tree
    public void printTree( TreeNode root)
    {
        if (root == null)
            return;
                
        System.out.print(root.val +" ");
        printTree(root.left);
        printTree(root.right);
    }
    
// Driver program to test above function
    public static void main(String args[]) 
    {
        String exp1 = "a?b?c:d:e";
        String exp = "a?b?c:d:e?f:g";
        System.out.println(exp);
        ExpressionTree tree = new ExpressionTree();
        char[] expression=exp.toCharArray(); 
        TreeNode root = tree.convertExpression(expression);
        tree.exp_2_tree(exp);
//        tree.printTree(root) ;
    }

}
