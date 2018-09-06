package n.queens;

import java.util.List;
import java.util.Random;

import edu.usc.cs561.GridNode;


public class SimulatedAnnealingState {
	
    Random randomGenerator = new Random();
    List<Integer>[] tree;
    int row;
    int cost;
    GridNode q[];
    int[] number;
    int babynumber;
    boolean placeFull = true;
    int[] singleCost;
    int largeIndex;
    public boolean ifNoBabyAndNoTree(List<Integer>[] tree,GridNode[] q,int x,int y) {
    	for(GridNode node:q) {
    		if(node!=null&&node.x==x&&node.y==y) {
//    			System.out.println("ifNoBabyAndNoTree: "+"在"+x+" , "+y+" 处有个baby");
    			return false;
    		}
    	}
    	for(int tre:tree[x]) {
    		if(tre==y) {
//    			System.out.println("ifNoBabyAndNoTree: "+"在"+x+" , "+y+" 处有个tree");
    			return false;
    		}
    	}
    	return true;
    }
    
    public SimulatedAnnealingState(List<Integer>[] tree, int row, int babynumber) {
        this.row = row;
        this.q = new GridNode[babynumber];
    	this.tree = tree;
    	this.babynumber = babynumber;
        int count=0;
        int line = 0;
        singleCost = new int[babynumber];
        number = new int[row];
        for(int i=0;i<row&&count<babynumber;i++) {
        	for(int j=0;j<row&&count<babynumber;j++) {
        		System.out.println("SimulatedAnnealingState"+i+","+j);
            	if(ifNoBabyAndNoTree(tree,q,i,j)) {
        		q[count] = new GridNode(i,j);
        		number[i]++;
        		System.out.println("q["+count+"]= "+i+","+j);
        		count++;
        	}
        	}
        }
        if(count<babynumber) placeFull=false;
//        while (count<babynumber) {
//        	int tempX = randomGenerator.nextInt(row);
////        	while(number[line]+tree[line].size()==row) {
////        		line++;
////        	}
////        	int tempX = line;
//        	int tempY = randomGenerator.nextInt(row);
//        	System.out.println("conductor "+count+" y="+tempY);
//        	if(ifNoBabyAndNoTree(tree,q,tempX,tempY)) {
//        		q[count] = new GridNode(tempX,tempY);
//        		number[tempX]++;
//        		System.out.println("q["+count+"]= "+tempX+","+tempY);
//        		count++;
////        		line++;
//        	}
//            
//        }
        System.out.println();
    }

    public SimulatedAnnealingState(int row, GridNode q[],int babynumber, List<Integer>[] tree, int[] number) {
        this.row = row;
        this.q = q;
        cost = 0;
        this.babynumber=babynumber;
        this.tree=tree;
        this.number = number;
    }


    public SimulatedAnnealingState getNextState() {
        int i;
        GridNode[] nextStateQueen = new GridNode[babynumber];
        //randomly pick a queen we want to change at a time.
        //用 rand来读取第随机个baby
        int rand = randomGenerator.nextInt(babynumber);
        //以下代码是在同一行变换用的，目前不需要
//        while(number[rand]+tree[rand].size()==row) {
//        	rand = randomGenerator.nextInt(row);
//        }
//        System.out.println("此时的rand是"+rand);
        for (i = 0; i < babynumber; i++) {
            nextStateQueen[i] = new GridNode(q[i].x,
                    q[i].y);
            //we only change that randomly picked queen in this state. 
            //if the current queen is not the queen we picked
            //the next state of that queen will be same as the previos queen.
            if (rand == i) {
            	int tempX = randomGenerator.nextInt(row);
                int tempY = randomGenerator.nextInt(row);
//                System.out.println("getNextState:"+i+" 原来坐标是:"+q[i].x+","+q[i].y+"现在是:"+tempX+" , "+tempY);
                //this is to ensure that the new state will not be the same as the 
                while ((tempX==q[i].x&&tempY == q[i].y)||ifNoBabyAndNoTree(tree,nextStateQueen,tempX,tempY)==false) {
//                	System.out.println("getNextState:"+i+" 重新来 "+tempY);
                	tempX = randomGenerator.nextInt(row);
                	tempY = randomGenerator.nextInt(row);
                }
                //new state will be added to the new state
                nextStateQueen[i] = new GridNode(tempX, tempY);
                number[q[i].x]--;
                number[tempX]++;
            }
        }

        return new SimulatedAnnealingState(row, nextStateQueen,babynumber, tree, number);
    }

    public void calculateCost() {
        int i, j;
        cost = 0;
        for (i = 0; i < babynumber; i++) {
            for (j = 0; j < babynumber; j++) {
                if (i==j) continue;
                if (q[i].x == q[j].x// same row
                        || q[i].y == q[j].y //same column
                        || (Math.abs(q[i].x - q[j].x) == Math.abs(q[i].y - q[j].y)) // same diagonal
                        ) {
                	GridNode small = q[i];
                	GridNode large = q[j];
                	if(small.x>large.x) {
                		small = q[j];
                		large = q[i];
                	}
                	if(small.x==large.x) {
						int smallY = Math.min(small.y, large.y);
						int largeY = Math.max(small.y, large.y);
						if(tree[small.x].size()!=0) {
							for(int treeY:tree[small.x]) {
								if(treeY>smallY&&treeY<largeY) {
//									System.out.println("small.x==large.x 在"+small.x+","+small.y+" 和"+large.x+","+large.y);
									cost--;
									break;
								}
							}
						}
                	}else if(small.y==large.y){
						for(int treeX=small.x+1;treeX<large.x;treeX++) {
							if(tree[treeX].contains(small.y)) {
//								System.out.println("small.y==large.y 在"+small.x+","+small.y+" 和"+large.x+","+large.y);
								cost--;
								break;
							}
						}
                	}else if(small.y>large.y) {
                		boolean rightslanSign = false;
						for(int treeX=small.x+1;treeX<large.x;treeX++) {
							if(tree[treeX].size()!=0) {
								for(int treeY:tree[treeX]) {
									if(treeY-small.y==small.x-treeX) {
//										System.out.println("small.y>large.y 在"+small.x+","+small.y+" 和"+large.x+","+large.y);
										rightslanSign =true;
										break;
									}
								}
								if(rightslanSign==true) {
									cost--;
									break;
								}
							}
						}
                	}else if(small.y<large.y) {
						boolean leftslanSign = false;
						for(int treeX=small.x+1;treeX<large.x;treeX++) {
							if(tree[treeX].size()!=0) {
								for(int treeY:tree[treeX]) {
									if(treeY-small.y==treeX-small.x) {
//										System.out.println("small.y<large.y 在"+small.x+","+small.y+" 和"+large.x+","+large.y);
										leftslanSign=true;
										break;
									}
								}
								if(leftslanSign==true) {
									cost--;
									break;
								}
							}
						}
                	}
                    cost++;
                }
            }
        }
        //this is due to double counting
//        cost = cost / 2;

    }

//    public int findLargest() {
//    	int max
//    	for(int i=0;i<singleCost.length;i++) {
//    		
//    	}
//    }
    
    
    public int getCost() {
        calculateCost();
        return cost;
    }
    
    public GridNode[] getGrid() {
    	return q;
    }

}
