package maolintu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Stack;


public class Agent2 {

	
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
							System.out.println(v);
							v = temp;
							x = i;
							y = j;
						}
					    if (v > alpha) alpha = v;
					    if (alpha >= beta) {
					    	break;  // beta cut-off
					    }
					}
					
				}
			}
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
			int limitition=0;
			while(!pq.isEmpty()) {
				Node temp = pq.poll();
				int tempV = alphabeta(jishu++, temp.x,temp.y, temp.grid, count - temp.count, sum+temp.count*temp.count, depth,alpha,beta,false);
//				System.out.println("alphabeta1: "+temp+": "+i+" , "+j+" "+tempans);
				if(tempV>v) {
					System.out.println(tempV);
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
	public static int[] alphabetaWithPQ2(char[][] grid, int count, int sum, int depth, int alpha, int beta) {

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
		if(depth == 0|| count ==0) {
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
						moveGrid(nextgrid);
						v = Math.max(v, alphabeta((jishu*10)+(count1++), i, j, nextgrid, count - minus, sum+minus*minus, depth-1,alpha,beta,false));
						alpha = Math.max(alpha, v);
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
						v = Math.min(v, alphabeta((jishu*10)+(count1++), i, j, nextgrid, count - minus, sum-minus*minus, depth-1,alpha,beta,true));
						beta = Math.min(beta, v);
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
		return score;
	}
	static int xcount = 0;
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
//		grid[x][y]='*';
		while(!stack.isEmpty()) {
			int size = stack.size();
			for(int i=0;i<size;i++) {
			Integer[] temp = stack.pop();
			if(grid[temp[0]][temp[1]]=='*') continue;
			grid[temp[0]][temp[1]] = '*';
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
	public static void controlTimecomplete() {
		ReadFile rf = new ReadFile();
		Random ran = new Random();
		int x = ran.nextInt(25)+1;
		int y = ran.nextInt(8)+1;
		System.out.println(x+" "+y);
		System.out.println(21+" "+4);
//		MakeTestCase2 mt = new MakeTestCase2(14,3,300.0);
		rf.readFileByLines("self.txt");
		char[][] grid = rf.getGrid();
		int n = grid.length;
		double remainTime = rf.time*1000;
		long start=System.currentTimeMillis();   //获取开始时间  
		int count = rf.count;
		while(count>0&&remainTime>0) {
		Node node = controlTime3("player",grid,remainTime,count,rf.fruit);
		count = node.count;
		remainTime = node.time;
		System.out.println(remainTime/1000+" s");
		}
		System.out.println("total time: "+(System.currentTimeMillis()-start)+"ms");
	}
	
	public static void enter() {
		ReadFile rf = new ReadFile();
		Random ran = new Random();
		int x = ran.nextInt(25)+1;
		int y = ran.nextInt(8)+1;
		System.out.println(x+" "+y);
		System.out.println(21+" "+4);
//		MakeTestCase2 mt = new MakeTestCase2(14,3,300.0);
		rf.readFileByLines("self.txt");
		char[][] grid = rf.getGrid();
		int n = grid.length;
		double remainTime = rf.time*1000;
		System.out.println(rf.time);
		long start=System.currentTimeMillis();   //获取开始时间  
		int count = rf.count;
		Node node = controlTime3("player",grid,remainTime,count,rf.fruit);
		count = node.count;
		remainTime = node.time;
		System.out.println(remainTime/1000+" s");
		System.out.println("total time: "+(System.currentTimeMillis()-start)+"ms");
	}
	
	public static void main(String[] args) {
		enter();
//		controlTimecomplete();
	}
	
	public static Node controlTime3(String palyer, char[][] ingrid, double time, int count, int fruit) {
		long start=System.currentTimeMillis();   //获取开始时间  
		char[][] grid = ingrid;
		int n = grid.length;
		int f = fruit;
		double remainTime = time;
		//TODO: Time 1000 relationship
		double rt = remainTime/300000.0;
		double rc = (double)(count*1.0)/(n*n);
		char[][] countgrid = copyGrid(grid);
		
		
		int branch =0;
		for(int i=0;i<grid.length;i++) {
			for(int j=0;j<grid.length;j++) {
				if(countgrid[i][j]!='*') {
					chooseFruit(i,j,countgrid);
					branch++;
				}
			}
		}
		System.out.println("count:"+count+" branch:"+branch);
		int[] res = {0,0};
		int depth = 1;
		if(remainTime>200000) {
			if(branch*count<=625) {
				depth=7;
				if(3*rt<rc) {
					depth -=4;
				}else if(1.8*rt<rc) {
					depth -=2;
				}else if(depth>1&&1.4*rt<rc) {
					depth -=1;
				}
			}else if(branch*count<=3250){
				depth = 5;
				if(2.5*rt<rc) {
					depth -=4;
				}else if(1.8*rt<rc) {
					depth -=2;
				}else if(depth>1&&1.4*rt<rc) {
					depth -=1;
				}
			}else if(branch*count<=5500){
				depth = 4;
				if(2.5*rt<rc) {
					depth -=4;
				}else if(1.8*rt<rc) {
					depth -=2;
				}else if(depth>1&&1.4*rt<rc) {
					depth -=1;
				}
			}else{
				depth = 3;
				if(1.8*rt<rc) {
					depth -=2;
				}else if(depth>1&&1.5*rt<rc) {
					depth -=1;
				}
			}
		}else if(remainTime>100000) {
			if(branch*count<=625) {
				depth=7;
				if(3*rt<rc) {
					depth -=4;
				}else if(1.8*rt<rc) {
					depth -=2;
				}else if(depth>1&&1.4*rt<rc) {
					depth -=1;
				}
			}else if(branch*count<=3250){
				depth = 5;
				if(2.5*rt<rc) {
					depth -=4;
				}else if(1.8*rt<rc) {
					depth -=2;
				}else if(depth>1&&1.4*rt<rc) {
					depth -=1;
				}
			}else if(branch*count<=5500){
				depth = 4;
				if(2.5*rt<rc) {
					depth -=4;
				}else if(1.8*rt<rc) {
					depth -=2;
				}else if(depth>1&&1.4*rt<rc) {
					depth -=1;
				}
			}else if(branch*count<=50000){
				depth = 3;
				if(1.8*rt<rc) {
					depth -=2;
				}else if(depth>1&&1.5*rt<rc) {
					depth -=1;
				}
			}
		}else if(remainTime>45000) {
			if(branch*count<=625) {
				depth=5;
				if(3*rt<rc) {
					depth -=4;
				}else if(1.8*rt<rc) {
					depth -=2;
				}else if(depth>1&&1.4*rt<rc) {
					depth -=1;
				}
			}else{
				depth = 3;
				if(1.8*rt<rc) {
					depth -=2;
				}else if(depth>1&&1.4*rt<rc) {
					depth -=1;
				}
			}
		}
		
		if(remainTime<10000) {
			depth = 0;
		}
//		加一个功能，至少要知道剩余时间
		char[][] newgrid = copyGrid(grid);
		System.out.println("rt= "+rt+" rc= "+rc+" depth= "+depth);
		int alpha = Integer.MIN_VALUE;
		int beta  = Integer.MAX_VALUE;
		res = alphabetaWithPQ(newgrid, count, 0, depth, alpha, beta);
		int sc = chooseFruit(res[0],res[1],grid);
		moveGrid(grid);
		System.out.println("newgrid");
		printGrid(grid);
		long end = System.currentTimeMillis()-start;
		System.out.println("running time: "+(end)+"ms ");
		System.out.println("score "+sc);
		return new Node(grid,count-sc,(remainTime-end));
	}
	public static int readCalibrate() {
		int res = 1500;
        File file = new File("calibrate.txt");
        
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
            	res = Integer.parseInt(tempString);
                System.out.println( ": " + tempString);
//                line++;
            }
            reader.close();
        	
        	System.out.println( "res: " + res);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        
        return res;
	}
	public static Node controlTime4(String palyer, char[][] ingrid, double time, int count, int fruit) {
		int newca = readCalibrate();
		int calibrate = 1500;
		double per = calibrate/(newca*1.0);
		System.out.println("calibrate "+calibrate);
		System.out.println("newca "+newca);
		System.out.println("per "+per);
		long start=System.currentTimeMillis();   //获取开始时间  
		char[][] grid = ingrid;
		int n = grid.length;
		int f = fruit;
		double remainTime = time;
		//TODO: Time 1000 relationship
		double rt = remainTime/300000.0;
		double rc = (double)(count*1.0)/(n*n);
		char[][] countgrid = copyGrid(grid);
		
		
		int branch =0;
		for(int i=0;i<grid.length;i++) {
			for(int j=0;j<grid.length;j++) {
				if(countgrid[i][j]!='*') {
					chooseFruit(i,j,countgrid);
					branch++;
				}
			}
		}
		System.out.println("count:"+count+" branch:"+branch);
		int[] res = {0,0};
		int depth = 1;
		if(remainTime>250000) {
			if(branch*count<=825*per) {
				depth=7;
				if(3*rt<rc) {
					depth -=4;
				}else if(1.8*rt<rc) {
					depth -=2;
				}else if(depth>1&&1.4*rt<rc) {
					depth -=1;
				}
			}else if(branch*count<=3550*per){
				depth = 5;
				if(2.5*rt<rc) {
					depth -=4;
				}else if(1.8*rt<rc) {
					depth -=2;
				}else if(depth>1&&1.4*rt<rc) {
					depth -=1;
				}
			}else if(branch*count<=6500*per){
				depth = 4;
				if(2.5*rt<rc) {
					depth -=4;
				}else if(1.8*rt<rc) {
					depth -=2;
				}else if(depth>1&&1.4*rt<rc) {
					depth -=1;
				}
			}else if(branch*count<=180000*per){
				depth = 3;
				if(1.8*rt<rc) {
					depth -=2;
				}else if(depth>1&&1.5*rt<rc) {
					depth -=1;
				}
			}else {
				depth = 2;
			}}else if(remainTime>200000) {
			if(branch*count<=725*per) {
				depth=7;
				if(3*rt<rc) {
					depth -=4;
				}else if(1.8*rt<rc) {
					depth -=2;
				}else if(depth>1&&1.4*rt<rc) {
					depth -=1;
				}
			}else if(branch*count<=3550*per){
				depth = 5;
				if(2.5*rt<rc) {
					depth -=4;
				}else if(1.8*rt<rc) {
					depth -=2;
				}else if(depth>1&&1.4*rt<rc) {
					depth -=1;
				}
			}else if(branch*count<=6000*per){
				depth = 4;
				if(2.5*rt<rc) {
					depth -=4;
				}else if(1.8*rt<rc) {
					depth -=2;
				}else if(depth>1&&1.4*rt<rc) {
					depth -=1;
				}
			}else if(branch*count<=155000*per){
				depth = 3;
				if(depth>1&&rt<rc) {
					depth -=1;
				}
			}else {
				depth = 2;
			}
		}else if(remainTime>100000) {
			if(branch*count<=625*per) {
				depth=7;
				if(3*rt<rc) {
					depth -=4;
				}else if(1.8*rt<rc) {
					depth -=2;
				}else if(depth>1&&1.4*rt<rc) {
					depth -=1;
				}
			}else if(branch*count<=3250*per){
				depth = 5;
				if(2.5*rt<rc) {
					depth -=4;
				}else if(1.5*rt<rc) {
					depth -=2;
				}else if(depth>1&&1.2*rt<rc) {
					depth -=1;
				}
			}else if(branch*count<=5500*per){
				depth = 4;
				if(2.5*rt<rc) {
					depth -=4;
				}else if(1.5*rt<rc) {
					depth -=2;
				}else if(depth>1&&1.2*rt<rc) {
					depth -=1;
				}
			}else if(branch*count<=60000*per){
				depth = 3;
				if(1.5*rt<rc) {
					depth -=2;
				}else if(depth>1&&1.2*rt<rc) {
					depth -=1;
				}
			}
		}else if(remainTime>45000) {
			if(branch*count<=625*per) {
				depth=5;
				if(3*rt<rc) {
					depth -=4;
				}else if(1.8*rt<rc) {
					depth -=2;
				}else if(depth>1&&1.4*rt<rc) {
					depth -=1;
				}
			}else{
				depth = 3;
				if(1.8*rt<rc) {
					depth -=2;
				}else if(depth>1&&1.4*rt<rc) {
					depth -=1;
				}
			}
		}
		
		if(remainTime<10000) {
			depth = 0;
		}
		char[][] newgrid = copyGrid(grid);
		System.out.println("rt= "+rt+" rc= "+rc+" depth= "+depth);
		int alpha = Integer.MIN_VALUE;
		int beta  = Integer.MAX_VALUE;
		res = alphabetaWithPQ(newgrid, count, 0, depth, alpha, beta);
		int sc = chooseFruit(res[0],res[1],grid);
		moveGrid(grid);
		printGrid(grid);
		long end = System.currentTimeMillis()-start;
		//System.out.println("running time: "+(end)+"ms ");
		//System.out.println("score "+sc);
		return new Node(res[0], res[1], grid,count-sc,(remainTime-end));
	}
}
