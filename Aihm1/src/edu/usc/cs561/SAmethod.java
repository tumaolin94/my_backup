package edu.usc.cs561;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SAmethod {
	GridNode res = new GridNode(0,0);
	List<GridNode> result = new ArrayList<>();
	List<Integer>[] tree;
	public SAmethod(int number,List<Integer>[] tree) {
		this.tree = tree;
	}
	public void generateRandomPositions(int number, int row) {
		Random r = new Random();  
		result.add(new GridNode(-1,-1));
		int count=0;
		for(int i=0;i<number;i++) {
			
			while(true) {
				int x=r.nextInt(row);
				int y=r.nextInt(row);
				GridNode cur = new GridNode(x,y);
				if(calculateAttacks(result,x, y, tree)==0) {
					result.add(cur);
					count++;
					System.out.println(x+","+y);
					break;
				}
			}
		}
		System.out.println(count);
	}
	
	public int calculateAttacks(List<GridNode> list,int x, int y, List<Integer>[] tree) {
	    int numAttacks = 0;
	    
	    for(GridNode temp:list) {
	    	if(temp.x==-1) continue;
	    	if(Math.abs(temp.x - x) == Math.abs(temp.y - y)  || temp.x==x||temp.y==y)  {  
	    		numAttacks++;  
          } 
	    }
	    return numAttacks;
	}
	
	public static void main(String args[]) {
		ReadFile rf = new ReadFile();
		List<String> list = rf.readFileByLines("880.txt");
		rf.dealWithData(list);
		List<Integer>[] tree = rf.getTree();
		SAmethod sm = new SAmethod(rf.getRow(),tree);
		sm.generateRandomPositions(rf.getBaby(), rf.getRow());
	}
}
