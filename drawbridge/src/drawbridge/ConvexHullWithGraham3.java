package drawbridge;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import drawbridge.ConvexHullWithGraham2.Point;
public class ConvexHullWithGraham3 { 
     Point[] ch; //点集p的凸包
     Point[] p ; //给出的点集
     int n;
     int l;
     int len=0;
    public ConvexHullWithGraham3(Point[] p,int n,int l){
       this.p=p;
       this.n=n;
       this.l=l;
       ch= new Point[n]; 
    }
   //小于0,说明顺时针。大于0 逆时针p2 在p1左边
   public  double multiply(Point p1, Point p2, Point p0) { 
       return ((p1.x - p0.x) * (p2.y - p0.y) - 
    		   (p2.x- p0.x) * (p1.y- p0.y)); 
   } 
   //求距离
   public  double distance(Point p1, Point p2) { 
       return (Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) 
               * (p1.y - p2.y))); 
   } 
   public double answer(){
    double sum = 0; 
       for (int i = 0; i < len - 1; i++) { 
           sum += distance(ch[i], ch[i + 1]); 
       } 
       if (len > 1) { 
           sum += distance(ch[len - 1], ch[0]); 
       } 
//       sum += 2 * l * Math.PI; 
       System.out.println((sum)); 
       return sum;
   }
   public  int Graham_scan() {
	   int res = n;
       int k = 0, top = 2; 
       Point tmp; 
       //找到最下且偏左的那个点   
       for (int i = 1; i < n; i++) {
           if ((p[i].y < p[k].y) 
                   || ((p[i].y == p[k].y) && (p[i].x < p[k].x))) 
               k = i;     	   
       }

       //将这个点指定为pts[0],交换pts[0]与pts[k] 
       tmp = p[0]; 
       p[0] = p[k]; 
       p[k] = tmp; 
       //按极角从小到大,距离偏短进行排序   
       Arrays.sort(p, (p1,p2) ->{
    	   return ((multiply(p1, p2, p[0]) > 0) 
                 || ((multiply(p1, p2, p[0]) == 0) && (distance( 
                 p[0], p1) < distance( 
                 p[0], p2))))?-1:1; 
       });
       for(Point x: p) {
    	   System.out.println(x.x+","+x.y);
       }
       //前三个点先入栈  
       ch[0] = p[0]; 
       ch[1] = p[1]; 
       ch[2] = p[2];
       PriorityQueue<Point> pq = new PriorityQueue<Point>(n,(p1, p2)->{return (int) (p2.len - p1.len);});
        //判断与其余所有点的关系   
       for (int i = 3; i < n; i++) { 
            //不满足向左转的关系,栈顶元素出栈   top 在 p[i]左边，所以出栈
           while (top > 0 && multiply(p[i], ch[top], ch[top - 1]) >= 0) {
        	   System.out.println(i+ "now :"+p[i].x+","+p[i].y);
        	   System.out.println(i+ "pop:"+ch[top].x+","+ch[top].y);
        	   top--; 
           }
            //当前点与栈内所有点满足向左关系,因此入栈. 
//           double len= distance(ch[top],p[i]);
//           p[i].len += len;
           ch[++top] = p[i];
       } 
       Set<Point> set = new HashSet<>();
       Map<Point, Integer> map = new HashMap<>();
       len=top+1;
       double left = distance(ch[0],ch[top]);
       double right = distance(ch[0],ch[1]);
       ch[top].len = left;
       ch[top].right = ch[0];
       ch[0].left = ch[top];
       for(int i=0;i<len-1;i++) {
    	   ch[i].len = left + right;
    	   left = right;
    	   right = distance(ch[i],ch[i+1]);
    	   pq.offer(ch[i]);
    	   ch[i].right = ch[i+1];
    	   ch[i+1].left = ch[i];
    	   set.add(ch[i]);
    	   map.put(ch[i], i);
       }
       ch[top].len +=right;
       pq.offer(ch[top]);
       set.add(ch[top]);
       map.put(ch[top],top);
       double sum = answer();
       while(sum>l) {
    	   Point temp = pq.poll();
    	   Point nRight = temp.right;
    	   Point nLeft = temp.left;
    	   System.out.println(nLeft.x);
    	   System.out.println("map:"+ map.get(nLeft));
    	   top = map.get(nLeft);
    	   for (int i = top+1; i < n; i++) { 
               //不满足向左转的关系,栈顶元素出栈   top 在 p[i]左边，所以出栈
              while (top > 0 && multiply(p[i], ch[top], ch[top - 1]) >= 0) {
           	   System.out.println(i+ "now :"+p[i].x+","+p[i].y);
           	   System.out.println(i+ "pop:"+ch[top].x+","+ch[top].y);
           	   top--; 
              }
               //当前点与栈内所有点满足向左关系,因此入栈. 
//              double len= distance(ch[top],p[i]);
//              p[i].len += len;
              ch[++top] = p[i];
          }
       }
       for(int i=0;i<len;i++) System.out.println("  "+ch[i].len);
       return len; 
   } 
  
   /**
样例: 
Sample Input 
9 100 
200 400 
300 400 
300 300 
400 300 
400 400 
500 400 
500 200 
350 200 
200 200 

Sample Output 
1628 
3 100
0 0
3 0
3 3
	*/
   public static void main(String[] args)  { 
    
       int n = 7; 
       int x, y; 
       Point[] p = new Point[n]; 
//       for (int i = 0; i < n; i++) { 
//           x = in.nextInt(); 
//           y = in.nextInt();
//           p[i] = new Point(x, y); 
//       } 
       p[0] = new Point(0,3);
       p[1] = new Point(3,0);
       p[2] = new Point(3,3);
       p[3] = new Point(0,0);
       p[4] = new Point(2,2);
       p[5] = new Point(2.5,2);
       p[6] = new Point(2,2.5);
       ConvexHullWithGraham3 ma=new ConvexHullWithGraham3(p,n,10); 
       ma.Graham_scan(); 
       ma.answer();
   } 
	public static class Point{//点   
		  double x;   
		  double y;   
		  double len = 0;
		  List<Point> list= new ArrayList<>();
		  Point left = null;
		  Point right = null;
		  public Point(double x,double y){
		     this.x=x;
		     this.y=y;
		  }
		}   
} 