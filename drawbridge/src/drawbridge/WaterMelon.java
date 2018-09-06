package drawbridge;

public class WaterMelon {

	public static int water(int[] boxes, int[] melons) {
		int m = boxes.length;
		int n = melons.length;
		int res = 0;
		for(int k = 0;k<n;k++) {
			int i = 0, j = k;
			while(i < m && j !=(k+n)) {
				if(boxes[i] >= melons[j%n]) {
					i++;
					j++;
				}else {
					i++;
				}
			}
			System.out.println("k+ "+k+" "+j%n+" "+(j-k));
			res = Math.max(res, j-k);
		}
		return res;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(water(new int[] {4,3,2,1},new int[]{1,2,3,4}));
	}

}