package solution.graph;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class AdjacencyLists {
	int n = 0;
	List<edge>[] adj;
//	List<Integer>[] adj;	
	class edge{
		int enode = 0;
		int weight = 0;
//		edge nextNode = null;
	}
	
	AdjacencyLists(int numNode) {
		n=numNode;
		adj = (List<edge>[])new List[n];
        for (int i = 0; i < n; i++) 
        adj[i] = new LinkedList<>();
		
	}
//    AdjacencyLists(int n0) {
//        n = n0;
//        adj = (List<Integer>[])new List[n];
//        for (int i = 0; i < n; i++) 
//            adj[i] = new LinkedList<>();
//    }
	void addEdge(int i, int j,int weight) {
	    edge newEdge = new edge();
	    newEdge.enode = j;
	    newEdge.weight = weight;
		adj[i].add(newEdge);
//		adj[i].add(j);
	    }
	
	void removeEdge(int i, int j) {
	        Iterator<edge> it = adj[i].iterator();
	        while(it.hasNext()) {
	        	if(it.next().enode==j) {
	        		it.remove();
	        		return;
	        	}
	        }
	        
	        
	        
	    }
	public void outLink() {
		for(List<edge> e:adj) {
			Iterator<edge> it = e.iterator();
	        while(it.hasNext()) {
	        	System.out.println(it.next());
	        }
		}
	}
	public static void main(String[] args) {
		AdjacencyLists ad = new AdjacencyLists(5);
		ad.addEdge(0,1,1);
//		ad.addEdge(0,2,1);
		int[] a = new int[]{1,2,3,4,5};
		for(int i: a) i=0;
		for(int i: a) System.out.println(i);
		
	}
}
