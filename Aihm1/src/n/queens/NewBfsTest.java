package n.queens;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import edu.usc.cs561.GridNode;
import edu.usc.cs561.ReadFile;
import edu.usc.cs561.TestAnswer;
import edu.usc.cs561.WriteFile;

public class NewBfsTest {
	GridNode bfsRes = new GridNode(0,0);
	List<GridNode> bfsResult = new ArrayList<>();

	
//	public boolean bfs(List<Integer>[] tree, int row, int baby) {
//		Queue<List<GridNode>> queue = new LinkedList<>();
//		GridNode root = new GridNode(-1,-1);
//		List<GridNode> rootlist = new ArrayList<>();
//		rootlist.add(root);
//		queue.offer(rootlist);
//		while(!queue.isEmpty()) {
//			int size = queue.size();
////			System.out.println("队列没空size: "+size);
//
//			for(int i=0;i<size;i++) {
//				List<GridNode> tempList = queue.poll();
//				GridNode tempNode = tempList.get(tempList.size()-1);
//				int tempRow = tempNode.x+1;
//				int tempCol =0;
////				TODO: 检查tempRow这一行还能不能放
//				if(tempNode.x!=-1&&tree[tempNode.x].size()!=0&&tempNode.y!=row-1) {
////					System.out.println("什么情况"+tempNode.x+", "+tempNode.y);
//					for(tempCol= tempNode.y;tempCol<row;tempCol++) {
//						if(!tree[tempNode.x].contains(tempCol)&&isBfsPlace(tempList, tempNode.x,tempCol, tree)) {
//							System.out.println("第"+tempNode.x+"行还能放");
//							tempRow = tempNode.x;
//							break;
//						}
//					}
//				}
////				System.out.println("什么情况结束后"+tempRow+", "+tempCol);
//				//j是列 count是说如果加入节点了就break，不加入节点就跳过
//				int count=0;
//				while(count==0&&tempRow<row) {
//					for(int j=0;j<row;j++) {
////					TODO: 
//						if((tree[tempRow].size()==0||(tree[tempRow].size()!=0&&!tree[tempRow].contains(j)))&&isBfsPlace(tempList, tempRow,j, tree)) {
//							tempList.add(new GridNode(tempRow,j));
//							if(tempList.size()==baby+1) {
//								bfsResult = tempList;
//								return true;
//							}
//							queue.offer(new ArrayList<>(tempList));
//							tempList.remove(tempList.size()-1);
//							count++;
//						}
//					}
//					if(count==0) {
////						System.out.println("在"+tempRow+"貌似没加入节点");
//						tempRow++;
//					}
//				}
//			}
//		}
//		return false;
//	}
	
	
	/**
	 * newBFS
	 * */
	public boolean newBfs(List<Integer>[] tree, int row, int baby) {
		Queue<List<GridNode>> queue = new LinkedList<>();
		GridNode root = new GridNode(-1,-1);
		List<GridNode> rootlist = new ArrayList<>();
		rootlist.add(root);
		queue.offer(rootlist);
		while(!queue.isEmpty()) {
			int size = queue.size();
			System.out.println(size);
//			System.out.println("队列没空size: "+size);
			for(int i=0;i<size;i++) {
				List<GridNode> tempList = queue.poll();
				GridNode tempNode = tempList.get(tempList.size()-1);
				int tempRow = (tempNode.x==-1||tree[tempNode.x].size()==0)?tempNode.x+1:tempNode.x;
				
				//j是列 count是说如果加入节点了就break，不加入节点就跳过
				int count=0;
				while(count==0&&tempRow<row) {
					int tempCol=(tempRow==tempNode.x)?tempNode.y:0;
					while(tempCol<row) {
//					TODO: 
//						System.out.println(tempRow);
						if(tree[tempRow].contains(tempCol)&&count>0) {
							break;
						}
						else if((tree[tempRow].size()==0||(!tree[tempRow].contains(tempCol)))&&isBfsPlace(tempList, tempRow,tempCol, tree)) {
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
//						System.out.println("在"+tempRow+"貌似没加入节点");
						tempRow++;
					}
				}
			}
		}
		return false;
	}
	
	

	
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
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long start=System.currentTimeMillis();   //获取开始时间 
    	ReadFile rf = new ReadFile();
	
		List<String> list = rf.readFileByLines("8812.txt");
		rf.dealWithData(list);
		List<Integer>[] tree = rf.getTree();
		
		NewBfsTest bt = new NewBfsTest();
		boolean status = bt.newBfs(tree, rf.getRow(), rf.getBaby());
		System.out.println(status);
    	WriteFile wf = new WriteFile();
    	wf.WriteListDataToFile(status, rf.getRow(),list,  bt.bfsResult);
		long end=System.currentTimeMillis(); //获取结束时间  
    	TestAnswer ta = new TestAnswer();
		
		int babynumber = rf.getBaby();
		int[][] origingrid = rf.getGrid();
    	
    	ta.test(origingrid.length, babynumber, origingrid,"output.txt");
    	System.out.println("程序运行时间： "+(end-start)+"ms");  
	}

}
