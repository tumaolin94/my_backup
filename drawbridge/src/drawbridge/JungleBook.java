package drawbridge;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class JungleBook {
	int junglebook(int[] list) {
		int[] indegree = new int[list.length];
		int[] pray = new int[list.length];
		Arrays.fill(pray, -1);
		for(int i=0;i<list.length;i++) {
			
			if(list[i] != -1 && indegree[list[i]] == 2) {
				pray[list[i]] = -1;
				continue;
			}
			if(list[i] != -1 && indegree[list[i]] != 2) {
				indegree[i]++;
				pray[list[i]] = i;
				if(list[list[i]]!=-1) {
					indegree[i]++;
				}
				
			}
		}
		for(int i: indegree) System.out.println(i);
		for(int i: pray) System.out.println("  "+i);
		int res = 0;
		Queue<Integer> queue = new LinkedList<>();
		for(int i=0;i<indegree.length;i++) {
			if(indegree[i]==0) queue.offer(i);
		}
		while(!queue.isEmpty()) {
			int size = queue.size();
			res++;
			for(int i=0;i<size;i++) {
				int index = queue.poll();
				if(pray[index]!=-1) {
					if(--indegree[pray[index]] == 0) queue.offer(pray[index]);
					if(pray[pray[index]] !=-1) {
						if(--indegree[pray[pray[index]]] == 0) queue.offer(pray[pray[index]]);
					}
				}
				
			}
		}
		return res;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new JungleBook().junglebook(new int[] {3,-1,0,1}));

	}

}
