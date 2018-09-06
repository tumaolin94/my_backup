
public class OA2 {
	public static int search(int[] value, int[] edge) {
		if(value.length==0) return 0;
		int[] children = new int[value.length];
		for(int i=0;i<children.length;i++) {
			children[i]=-1;
		}
		for(int i=0;i<edge.length;i+=2) {
			if(value[edge[i]-1]==value[edge[i+1]-1]) {
				System.out.println(edge[i]+" connect "+edge[i+1]+" value: "+value[edge[i]-1]);
				int temp = edge[i]-1;
				while(children[temp]!=-1) {
					temp = children[temp];
					
				}
				children[temp] = edge[i+1]-1;
				System.out.println("children["+temp+"]= "+children[temp]);
			}
		}
		int max = 0;
		for(int i=0;i<children.length;i++) {
			int count = 0;
			int temp = i;
			while(children[temp]!=-1) {
				count++;
				temp = children[temp];
			}
			max = Math.max(max, count);
		}
		return max;
	}
	
	public static void main(String[] args) {
		int[] values = {1,1,2,1,1,2,1,1,1};
		int[] edges = {1,2,1,3,2,4,2,5,5,7,3,6,6,8,6,9};
		System.out.println("result: "+search(values,edges));
	}
}
