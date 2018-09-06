import java.util.Arrays;
import java.util.Comparator;

class Line{
		int x1,x2,y1,y2;
		public Line(int x1,int y1,int x2,int y2) {
			this.x1 = x1;
			this.x2 = x2;
			this.y1 = y1;
			this.y2 = y2;
		}
	}
public class Interview1 {
	
	public static int Calculatearea(Line[] Horizontal, Line[] Vertical) {
		Arrays.sort(Horizontal, new Comparator<Line>() {
			@Override
			public int compare(Line l1, Line l2) {
				if(l1.x1!=l2.x1) {
					return l1.x1 - l2.x1;
				}else {
					return l2.y1 - l1.y1;
				}
			}
		});
		Arrays.sort(Vertical, new Comparator<Line>() {
			@Override
			public int compare(Line l1, Line l2) {
				if(l1.x1!=l2.x1) {
					return l1.x1 - l2.x1;
				}else {
					return l2.y1 - l1.y1;
				}
			}
		});
		for(Line h:Horizontal) {
			System.out.println(h.x1+","+h.y1+","+h.x2+","+h.y2);
		}
		for(Line v:Vertical) {
			System.out.println(v.x1+","+v.y1+","+v.x2+","+v.y2);
		}
		int res = Integer.MIN_VALUE;
		for(int i=0;i<Horizontal.length;i++) {
			for(int j=i+1;j<Horizontal.length;j++) {
				int min = Integer.MAX_VALUE;
				int max = Integer.MIN_VALUE;
				for(Line v:Vertical) {
					if(v.x1>=Horizontal[i].x1&&v.x1>=Horizontal[j].x1&&
							v.x1<=Horizontal[i].x2&&v.x1<=Horizontal[j].x2&&
							v.y1<=Horizontal[i].y1&&v.y1<=Horizontal[j].y1&&
							v.y2>=Horizontal[i].y1&&v.y2>=Horizontal[j].y1) {
						if(min == Integer.MAX_VALUE) min = v.x1;
						else {
							max = v.x1;
						}
					}
				}
				if(max == Integer.MIN_VALUE) continue;
				res = Math.max(res, (Horizontal[i].y1 - Horizontal[j].y1)*(max - min));
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Line[] Horizontal = new Line[3];
		Horizontal[0] = new Line(0,0,5,0);
		Horizontal[1] = new Line(0,3,11,3);
		Horizontal[2] = new Line(0,5,10,5);
		Line[] Vertical = new Line[3];
		Vertical[0] = new Line(1,-2,1,8);
		Vertical[1] = new Line(3,-10,3,10);
		Vertical[2] = new Line(8,-10,8,10);
		
		System.out.println(Calculatearea(Horizontal,Vertical));
	}

}
