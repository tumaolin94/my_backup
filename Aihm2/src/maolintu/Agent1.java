package maolintu;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

import hw2.homework;


class Node{
	char[][] grid;
	int count;
	int x, y;
	double time;
	public Node(char[][] grid, int count) {
		this.grid = grid;
		this.count = count;
	}
	public Node(int i, int j, char[][] grid, int count) {
		this.grid = grid;
		this.count = count;
		this.x = i;
		this.y = j;
	}
	public Node(char[][] grid, int count, double time) {
		this.grid = grid;
		this.count = count;
		this.time = time;
	}
 	public Node(int i, int j, char[][] grid, int count,double time) {
		this.grid = grid;
		this.count = count;
		this.x = i;
		this.y = j;
		this.time = time;
	}
}
public class Agent1 {

	
	public static char[][] copyGrid(char[][] grid){
		char[][] newgrid = new char[grid.length][grid.length];
		for(int i=0;i<grid.length;i++) {
			newgrid[i] = Arrays.copyOf(grid[i], grid.length);
		}
		return newgrid;
	}
	
	static int init_alpha = Integer.MIN_VALUE;
	static int init_beta  = Integer.MAX_VALUE;
	

	
	public static int[] alphabeta(char[][] grid, int count, int sum, int depth, int alpha, int beta) {
		char[][] newgrid = copyGrid(grid);
		int x = 0,y = 0;
			int v = Integer.MIN_VALUE;
			boolean ifContinue = true;
			int jishu = 0;
			for(int i=0;i<grid.length;i++) {
				for(int j=0;j<grid.length;j++) {
					if(grid[i][j]!='*') {
						chooseFruit(i,j,grid);
						char[][] nextgrid = copyGrid(newgrid);
						int minus = chooseFruit(i,j,nextgrid);
						moveGrid(nextgrid);
						int temp = alphabeta(jishu++, i,j, nextgrid, count - minus, sum+minus*minus, depth,alpha,beta,false);
						if(temp>v) {
							v = temp;
							x = i;
							y = j;
						}
					    if (v > alpha) alpha = v;
					    if (alpha >= beta) {
					    	ifContinue = false;
					    	break;  // beta cut-off
					    }
					}
					
				}
				if(!ifContinue) break;
			}
			
//			printGrid(nextgrid);
			
			
			return new int[] {x,y};

	}
	
	public static int[] alphabetaWithPQ(char[][] grid, int count, int sum, int depth, int alpha, int beta) {

		Comparator<Node> idComparator = new Comparator<Node>(){
			
			@Override
			public int compare(Node c1, Node c2) {
	            return (int) (c2.count - c1.count);
	        }
		};
		PriorityQueue<Node> pq = new PriorityQueue<>(10,idComparator);
		char[][] newgrid = copyGrid(grid);
		int x = 0,y = 0;
			int v = Integer.MIN_VALUE;
			int jishu = 0;
			for(int i=0;i<grid.length;i++) {
				for(int j=0;j<grid.length;j++) {
					if(grid[i][j]!='*') {
						chooseFruit(i,j,grid);
						char[][] nextgrid = copyGrid(newgrid);
						int minus = chooseFruit(i,j,nextgrid);
						moveGrid(nextgrid);
						pq.offer(new Node(i,j,nextgrid, minus));
					}
				}
			}
			while(!pq.isEmpty()) {
				Node temp = pq.poll();
				int tempV = alphabeta(jishu++, temp.x,temp.y, temp.grid, count - temp.count, sum+temp.count*temp.count, depth,alpha,beta,false);
//				System.out.println("alphabeta1: "+temp+": "+i+" , "+j+" "+tempans);
				if(tempV>v) {
//					System.out.println(temp+": "+i+" , "+j+" "+tempans);
					v = tempV;
					x = temp.x;
					y = temp.y;
				}
			    if (v > alpha) alpha = v;
			    if (alpha >= beta) {
			    	break;  // beta cut-off
			    }
			}
			
//			printGrid(nextgrid);
			
			
			return new int[] {x,y};

	}
	
	public static int alphabeta(int jishu, int x, int y, char[][] grid, int count, int sum, int depth, int alpha, int beta, boolean maximizingPlayer) {
//		System.out.println(((maximizingPlayer)?"max":"min")+" depth="+depth+" sum="+sum+": "+x+" , "+y);
//		System.out.println("depth "+depth);
//		printGrid(grid);
//		System.out.println("depth end");
		if(depth == 0|| count ==0) {
//			System.out.println(((maximizingPlayer)?"max":"min")+" depth="+depth+" sum "+sum+": "+x+" , "+y);
//			System.out.println("end depth "+depth);
//			printGrid(grid);
//			System.out.println("end depth end");			
			return sum;
		}
		char[][] newgrid = copyGrid(grid);
		int count1 = 0;
		if(maximizingPlayer) {
			int v = Integer.MIN_VALUE;
			boolean ifContinue = true;
			for(int i=0;i<grid.length;i++) {
				for(int j=0;j<grid.length;j++) {
					if(grid[i][j]!='*') {
						 chooseFruit(i,j,grid);
						
						
						char[][] nextgrid = copyGrid(newgrid);
						int minus =chooseFruit(i,j,nextgrid);
//						System.out.println(i+" , "+j+" minus:"+minus);
						moveGrid(nextgrid);
						
//						int temp = alphabeta(i, j, nextgrid, count - minus, sum, depth-1,alpha,beta,false);
//						if(temp>v) {
//							v = temp;
//							System.out.println(((maximizingPlayer)?"max":"min")+" depth="+depth+" v "+v+": "+x+" , "+y);
//							System.out.println("depth "+depth);
//							printGrid(grid);
//							System.out.println("depth end");
//						}
						v = Math.max(v, alphabeta((jishu*10)+(count1++), i, j, nextgrid, count - minus, sum+minus*minus, depth-1,alpha,beta,false));
//						System.out.println(jishu+"MAX+ for: depth v"+depth+" v:"+v+" xy "+i+","+j);
//						printGrid(newgrid);
//						System.out.println("for: depth end");
						if (v > alpha) alpha = v;
					    if (alpha >= beta) {
					    	ifContinue = false;
					    	break;  // beta cut-off
					    }
					}
				}
				if(!ifContinue) break;
			}
			return v;
		}else {
			int v = Integer.MAX_VALUE;
			boolean ifContinue = true;
			for(int i=0;i<grid.length;i++) {
				for(int j=0;j<grid[0].length;j++) {
					if(grid[i][j]!='*') {
						chooseFruit(i,j,grid);
						
						
						char[][] nextgrid = copyGrid(newgrid);
						int minus = chooseFruit(i,j,nextgrid);
						moveGrid(nextgrid);
//						System.out.println(i+" , "+j+" minus:"+minus);						
//						int temp = alphabeta(i, j, newgrid, count - minus, sum, depth-1,alpha,beta,true);
//						if(temp<v) {
//							v = temp;
//							System.out.println(((maximizingPlayer)?"max":"min")+" depth="+depth+" v "+v+": "+x+" , "+y);
//							System.out.println("depth "+depth);
//							printGrid(grid);
//							System.out.println("depth end");
//						}
						v = Math.min(v, alphabeta((jishu*10)+(count1++), i, j, nextgrid, count - minus, sum-minus*minus, depth-1,alpha,beta,true));
//						System.out.println((jishu)+"MIN+ for: depth v"+depth+" v:"+v+" xy "+i+","+j);
//						printGrid(newgrid);
//						System.out.println("for: depth end");
						if (v < beta) beta = v;
					    if (alpha >= beta) {
					    	ifContinue = false;
					    	break;  // alpha cut-off
					    }
					}
				}
				if(!ifContinue) break;
			}
			return v;
		}
	}
	//1. basic operation
	static int[][] direction = {{-1,0},{1,0},{0,1},{0,-1}};
	public static int chooseFruit(int x, int y, char[][] grid){
		int score = 0;
		score = dfs(grid[x][y],x,y,grid);
//		score = stackDfs(grid[x][y],x,y,grid);
		return score;
	}
	static int count = 0;
	public static int dfs(char fruit, int x, int y, char[][] grid) {
		if(x<0||y<0||x>=grid.length||y>=grid[0].length||grid[x][y]!=fruit||grid[x][y]=='*') return 0;
		grid[x][y]='*';
		int score = 1;
		for(int[] d:direction) {
			score +=dfs(fruit,x+d[0], y+d[1],grid);
		}
		return score;
	}
	
	public static int stackDfs(char fruit, int x, int y, char[][] grid) {
		int score = 0;
		Stack<Integer[]> stack = new Stack<>();
		stack.push(new Integer[]{x,y});
		grid[x][y]='*';
		while(!stack.isEmpty()) {
			int size = stack.size();
			for(int i=0;i<size;i++) {
			Integer[] temp = stack.pop();
//			if(grid[temp[0]][temp[1]]=='*') continue;
//			grid[temp[0]][temp[1]] = '*';
			score++;
			for(int[] d:direction) {
				int tempX = temp[0]+d[0];
				int tempY = temp[1]+d[1];
				if(tempX>=0&&tempY>=0&&tempX<grid.length&&tempY<grid.length&&grid[tempX][tempY]==fruit) {
					grid[tempX][tempY]='*';
					stack.push(new Integer[]{tempX,tempY});
				}
			}
			}
		}

		return score;
	}
	static int moveCount=0;
	public static void moveGrid(char[][] grid) {
		moveCount++;
		for(int i=0;i<grid.length;i++) {
			int index = grid.length-1;
			for(int j=grid.length-1;j>=0;j--) {
				
				if(grid[j][i]!='*') {
					grid[index--][i] = grid[j][i];
				}
			}
			while(index>=0) grid[index--][i]='*';
		}
	}
	public static void printGrid(char[][] grid) {
    	for(int i=0;i<grid.length;i++) {
    		for(int j=0;j<grid[0].length;j++) {
    			System.out.print(grid[i][j]+",");
    		}
    		System.out.println();
    	}
	}
	
	public static void controlTime() {
		ReadFile rf = new ReadFile();
		rf.readFileByLines("self.txt");
		char[][] grid = rf.getGrid();
		int n = grid.length;
		double remainTime = rf.time*1000;
		count = rf.count;
		controlTime3("player",grid,rf.time,count,rf.fruit);
	}
	
	public static void controlTimecomplete() {
		ReadFile rf = new ReadFile();
		rf.readFileByLines("self.txt");
		char[][] grid = rf.getGrid();
		int n = grid.length;
		double remainTime = rf.time*1000;
		count = rf.count;
		while(count>0) {
		Node node = controlTime3("player",grid,rf.time,count,rf.fruit);
		count = node.count;
		}
	}
	
	public static Node controlTime2(String player, char[][] ingrid, double time, int count) {
		char[][] grid = ingrid;
		int n = grid.length;
		double remainTime = time*1000;
//		System.out.println("count:"+count);
		long start=System.currentTimeMillis();   //获取开始时间  
		int[] res = {0,0};
		int layer = 1;
		if(n<=10) layer = 3;
//		char[][] newgrid = copyGrid(grid);
//		res = alphabeta(newgrid, count, 0, layer, init_alpha, init_beta);
		while(true) {
			
			long singleStart=System.currentTimeMillis(); 
			char[][] newgrid = copyGrid(grid);
//			System.out.println("循环count "+count);
			res = alphabeta(newgrid, count, 0, layer, init_alpha, init_beta);
			long singleEnd=System.currentTimeMillis() - singleStart;
			remainTime -=singleEnd;
			if(singleEnd<10000&&remainTime>=singleEnd*90&&layer<10&&remainTime>50000) {
				layer++;
//				System.out.println("time2 "+singleEnd+" remainTime "+remainTime+" layer "+layer);
			}else {
				System.out.println(layer+" remainTime "+remainTime/1000);
				break;
			}
		}
		int sc = chooseFruit(res[0],res[1],grid);
		moveGrid(grid);
		System.out.println("newgrid");
		printGrid(grid);
		System.out.println("running time： "+(System.currentTimeMillis()-start)+"ms "); 
		return new Node(grid,count-sc,remainTime/1000);
	}
	
	public static Node controlTime3(String palyer, char[][] ingrid, double time, int count, int fruit) {
		char[][] grid = ingrid;
		int n = grid.length;
		double remainTime = time;
//		System.out.println("count:"+count);
		long start=System.currentTimeMillis();   //获取开始时间  
		int[] res = {0,0};
		int layer = 3;
		if(remainTime<0)  layer = 0;
		char[][] newgrid = copyGrid(grid);
		System.out.println("agent depth" + layer);
		res = alphabetaWithPQ(newgrid, count, 0, layer, init_alpha, init_beta);

		int sc = chooseFruit(res[0],res[1],grid);
		moveGrid(grid);
		System.out.println("newgrid");
		printGrid(grid);
		long end = System.currentTimeMillis()-start;
		System.out.println("running time: "+(end)+"ms ");
		System.out.println("score "+sc);
		return new Node(grid,count-sc,(remainTime-end));
	}
	public static void main(String[] args) {
//		controlTimecomplete();
		controlTime();

	}
}
