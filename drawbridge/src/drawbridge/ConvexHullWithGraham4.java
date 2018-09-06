package drawbridge;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;

import drawbridge.ConvexHullWithGraham2.Point;
public class ConvexHullWithGraham4 { 
     Point[] ch; //点集p的凸包
     List<Point> p ; //给出的点集
     int n;
     int l;
     int len=0;
    public ConvexHullWithGraham4(List<Point> p,int n,int l){
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
   public  double Graham_scan(int n) {
	   if(n <=1) return 0;
	   if(n == 2) return (distance(p.get(0), p.get(1)));
       int k = 0, top = 2; 
       //找到最下且偏左的那个点   
       for (int i = 1; i < n; i++) {
           if ((p.get(i).y < p.get(k).y) 
                   || ((p.get(i).y == p.get(k).y) && (p.get(i).x < p.get(k).x))) 
               k = i;     	   
       }

       //将这个点指定为pts[0],交换pts[0]与pts[k] 
       Collections.swap(p, 0, k);
       //按极角从小到大,距离偏短进行排序   
       Collections.sort(p, (p1,p2) ->{
    	   return ((multiply(p1, p2, p.get(0)) > 0) 
                 || ((multiply(p1, p2, p.get(0)) == 0) && (distance( 
                		 p.get(0), p1) < distance( 
                				 p.get(0), p2))))?-1:1; 
       });
       for(Point x: p) {
    	   System.out.println(x.x+","+x.y);
       }
       //前三个点先入栈  
       ch[0] = p.get(0); 
       ch[1] = p.get(1); 
       ch[2] = p.get(2);
        //判断与其余所有点的关系   
       for (int i = 3; i < n; i++) { 
            //不满足向左转的关系,栈顶元素出栈   top 在 p[i]左边，所以出栈
           while (top > 0 && multiply(p.get(i), ch[top], ch[top - 1]) >= 0) {
        	   System.out.println(i+ "now :"+p.get(i).x+","+p.get(i).y);
        	   System.out.println(i+ "pop:"+ch[top].x+","+ch[top].y);
        	   top--; 
           }
            //当前点与栈内所有点满足向左关系,因此入栈. 
//           double len= distance(ch[top],p[i]);
//           p[i].len += len;
           ch[++top] = p.get(i);
       } 
       double sum = 0;
       Point needRemove = ch[0];
       
       len=top+1;
       double left = distance(ch[0],ch[top]);
       double right = distance(ch[0],ch[1]);
       ch[top].len = left;
//       needRemove.len = left+distance(ch[top-1],ch[top]);
       
       for(int i=0;i<len-1;i++) {
    	   ch[i].len = left + right;
    	   needRemove = (left+right)>needRemove.len?ch[i]:needRemove;
    	   sum +=left;
    	   left = right;
    	   right = distance(ch[i],ch[i+1]);
       }
//       double sum = answer();
       sum +=left;
       System.out.println("needRemove: "+needRemove.x+","+needRemove.y);
       p.remove(needRemove);
       for(Point i: p) System.out.println(i.x+","+i.y);
       System.out.println("len"+sum);
       return sum; 
   } 
  
   /**

	*/
   public static void main(String[] args)  { 
    
//       Point[] p = new Point[n]; 
//       for (int i = 0; i < n; i++) { 
//           x = in.nextInt(); 
//           y = in.nextInt();
//           p[i] = new Point(x, y); 
//       } 
       List<Point> p = new LinkedList<>();
//       p[0] = new Point(0,3);
//       p[1] = new Point(3,0);
//       p[2] = new Point(3,3);
//       p[3] = new Point(0,0);
//       p[4] = new Point(2,2);
//       p[5] = new Point(2.5,2);
//       p[6] = new Point(2,2.5);
       p.add(new Point(0,3));
       p.add(new Point(3,0));
       p.add(new Point(3,3));
       p.add( new Point(0,0));
       p.add(new Point(2,2));
       p.add(new Point(2.5,2));
       p.add(new Point(2,2.5));
//       Random r = new Random(10000);
//       for(int i=0;i<100;i++) {
//    	   p.add(new Point(r.nextDouble()*10,r.nextDouble()*10));
//       }
       int n = p.size();
       ConvexHullWithGraham4 ma=new ConvexHullWithGraham4(p,n,100); 
       while(ma.Graham_scan(n)>10) {
    	   n--;
       }
       System.out.println("final answer:"+ n);
//       System.out.println(ma.Graham_scan(n-1));
//       ma.answer();
   } 
	public static class Point{//点   
		  double x;   
		  double y;   
		  double len = 0;
		  List<Point> list= new ArrayList<>();
		  public Point(double x,double y){
		     this.x=x;
		     this.y=y;
		  }
		}   
} 