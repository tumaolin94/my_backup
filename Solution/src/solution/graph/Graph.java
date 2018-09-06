package solution.graph;

public class Graph {

	
	private int nodeNum, edgeNum;
	vNode[] v; 
	/*
	 * @param nodeNum : number of nodes
	 * @param edgeNum : number of edgeNum
	 * */	
	public Graph(int nodeNum, int edgeNum) {
		this.nodeNum = nodeNum;
		this.edgeNum = edgeNum;
		v = new vNode[nodeNum];
	}
	
	class vNode {
		int data = 0;
		edge nextNode = null;
	}
	
	class edge{
		int weight;
		edge nextNode = null;
	}
	
	public void addEdge(int i, int j,int weight) {
		edge newEdge = new edge();
		newEdge.weight = weight;
		
		if(v[i].nextNode==null) v[i].nextNode = newEdge;
		else {
//			edge temp =;
//			while()
		}
	}
	
}
