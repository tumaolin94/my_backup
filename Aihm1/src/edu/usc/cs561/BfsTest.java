package edu.usc.cs561;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/**
* @author Maolin Tu
* 
* This is a Broad-First-Search method for a Zookeeper Simulation
*/
public class BfsTest {
	GridNode bfsRes = new GridNode(0,0);
	List<GridNode> bfsResult = new ArrayList<>();
	
	   /**
	   * This method executes  a BFS function.
	   * @param tree The position of trees, row   the total rows of the board, baby  the number of lizards
	   * @return true  if there is at least one solution, false  if there is no solution
	   */
	public boolean newBfs(List<Integer>[] tree, int row, int baby) {
		Queue<List<GridNode>> queue = new LinkedList<>();
		GridNode root = new GridNode(-1,-1);
		List<GridNode> rootlist = new ArrayList<>();
		rootlist.add(root);
		queue.offer(rootlist);
		while(!queue.isEmpty()) {
			int size = queue.size();
			for(int i=0;i<size;i++) {
				List<GridNode> tempList = queue.poll();
				GridNode tempNode = tempList.get(tempList.size()-1);
				int tempRow = (tempNode.x==-1||tree[tempNode.x].size()==0)?tempNode.x+1:tempNode.x;

				int count=0;
				while(count==0&&tempRow<row) {
					int tempCol=(tempRow==tempNode.x)?tempNode.y:0;
					while(tempCol<row) {
						if((tree[tempRow].size()==0||(!tree[tempRow].contains(tempCol)))&&isBfsPlace(tempList, tempRow,tempCol, tree)) {
							tempList.add(new GridNode(tempRow,tempCol));
							if(tempList.size()==baby+1) {
								bfsResult = tempList;
								return true;
							}
							queue.offer(new ArrayList<>(tempList));
							tempList.remove(tempList.size()-1);
							count++;
						}
						tempCol++;
					}
					if(count==0) {
						tempRow++;
					}
				}
			}
		}
		return false;
	}
	
	

	   /**
	   * This method judges if the postion can be put a lizard.
	   * @param temp  current board status, x   x coordinate, y   y coordinate, tree  list of trees' position
	   * @return true  if there is at least one solution, false  if there is no solution
	   */	
	public static boolean isBfsPlace(List<GridNode> temp,int x, int y, List<Integer>[] tree) {
	   	boolean downSign = true;
	   	boolean leftSign = true;
    	boolean rightslanSign = true;
    	boolean leftslanSign = true;
		if(temp.size()==1&&temp.get(0).x==-1) {
			return true;
		}else {

			for(int i=1;i<temp.size();i++) {
				GridNode tempNode = temp.get(i);
				int tempX = tempNode.x;
				int tempY = tempNode.y;
					if(tempX==x) {
						leftSign=false;
						if(tree[x].size()!=0) {
							for(int treeY:tree[x]) {
								if(treeY>tempY&&treeY<y) {
									leftSign=true;
									break;
								}
							}
						}
					}else if(tempY==y){
						downSign = false;
						for(int treeX=tempX;treeX<x;treeX++) {
							if(tree[treeX].contains(y)) {
								downSign=true;
								break;
							}
						}
					}else if(Math.abs(tempX - x) == Math.abs(tempY-y)&&tempY<y) {
						leftslanSign = false;
						for(int treeX=tempX+1;treeX<x;treeX++) {
							if(tree[treeX].size()!=0) {
								for(int treeY:tree[treeX]) {
									if(treeY-tempY==treeX-tempX) {
										leftslanSign=true;
										break;
									}
								}
								if(leftslanSign==true) break;
							}
						}
					}else if(Math.abs(tempX - x) == Math.abs(tempY-y)&&tempY>y) {
						rightslanSign = false;
						for(int treeX=tempX+1;treeX<x;treeX++) {
							if(tree[treeX].size()!=0) {
								for(int treeY:tree[treeX]) {
									if(treeY-tempY==tempX-treeX) {
//										System.out.println("出发了"+treeY+","+tempY+", "+treeX+","+tempX);
										rightslanSign=true;
										break;
									}
								}
								if(rightslanSign==true) break;
							}
						}						
					}
				
			}
		}

		return (downSign&&leftSign&&rightslanSign&&leftslanSign);
	}

}
