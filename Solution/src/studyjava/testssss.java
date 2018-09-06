package studyjava;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;
class Rectangle{
    int leftX;
    int leftY;
    int rightX;
    int rightY;
    public Rectangle(int leftX, int leftY, int rightX, int rightY){
        this.leftX = leftX;
        this.leftY = leftY;
        this.rightX = rightX;
        this.rightY = rightY;
    }
}
public class testssss {

    public static void main(String[] args) {

//        Scanner in = new Scanner(System.in);
//        while (in.hasNextInt()) {//注意while处理多个case
//            int n = in.nextInt();
//            int[] leftX = new int[n];
//            int[] leftY = new int[n];
//            int[] rightX = new int[n];
//            int[] rightY = new int[n];
//            for(int i = 0; i < n; i++){
//                leftX[i] = in.nextInt();
//            }
//            for(int i = 0; i < n; i++){
//                leftY[i] = in.nextInt();
//            }
//            for(int i = 0; i < n; i++){
//                rightX[i] = in.nextInt();
//            }
//            for(int i = 0; i < n; i++){
//                rightY[i] = in.nextInt();
//            }
//            Rectangle[] r = new Rectangle[n];
//            for(int i = 0; i < n; i++){
//                r[i] = new Rectangle(leftX[i],leftY[i],rightX[i],rightY[i]);
//            }
//            System.out.println(solution(r));
//        }
//      int n = in.nextInt();
      int[] leftX = new int[] {0,90};
      int[] leftY = new int[] {0,90};
      int[] rightX = new int[] {100,200};
      int[] rightY = new int[] {100,200};
      Rectangle[] r = new Rectangle[3];
      r[0] = new Rectangle(0,0,100,100);
      r[1] = new Rectangle(90,90,200,200);
      r[2] = new Rectangle(110,110,200,200);
      System.out.println(solution(r));
    }
    public static int solution(Rectangle[] rs){
    if(rs.length == 0) return 0;
	Arrays.sort(rs, (Rectangle r1, Rectangle r2) ->{
        if(r1.leftX!=r2.leftX)
            return r1.leftX - r2.leftX;
        else return r1.leftY - r2.leftY;});
    
    int n = rs.length;
    int res = 1;
    int tmp = 1;
    int start = 0;
    Stack<Rectangle> stack = new Stack<>();
        stack.push(rs[0]);
    for(int i = 1; i < n; i++) {
        Rectangle tmpR = stack.peek();
    	if(rs[i].leftX < tmpR.rightX && rs[i].leftY < tmpR.rightY) {
    		tmp++;
            stack.push(rs[i]);
//    		System.out.println("test");
    		res = Math.max(tmp, res);
    	}else {
    		start++;
            stack.pop();
    		tmp--;
    	}
    }
    return res;
}
}
