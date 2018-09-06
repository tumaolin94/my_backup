package edu.usc.cs561;

import java.util.Arrays;
import java.util.List;

public class TestAnswer {
	int row = 0;
    int baby = 0;
    int grid[][];
    
	public boolean test(int row, int baby, int[][] origin,String address) {
		System.out.println("enter test function:");
		ReadFile rf =new ReadFile();
		this.row = row;
		this.baby=baby;
		System.out.println(address);
		List<String> list = rf.readFileByLines(address);
		dealWithDataforAnswer(list);
		System.out.println("测试盘子:");
		printPanzi(grid);
		System.out.println("测试盘子结束");
		for(int i=0;i<row;i++){
			for(int j=0;j<row;j++) {
				if(grid[i][j]==1) {
					
					if(origin[i][j]!=0) {
						System.out.println("答案是错误的");
						return false;
					}
					else {
						place(i, j, origin);
					}
					
				}
			}
		}
		System.out.println("答案是正确的");
		return true;
	}

    
    public void dealWithDataforAnswer(List<String> list) {
    	System.out.println(list.get(0));
    	if(list.get(0).equals("FAIL")) {
    		System.out.println("Answer is FAIL");
    		return ;
    	}
    	grid = new int[row][row];
    	System.out.println("row"+row+" baby"+baby);
    	
    	for(int i = 1;i<1+row;i++) {
//    		System.out.println(list.get(i));
    		 for(int j=0;j<row;j++) {
//    			 System.out.println(i+","+j);
    			 if(list.get(i).charAt(j)=='2') {
    				 grid[i-1][j]=2;
    			 }else if(list.get(i).charAt(j)=='1'){
    				 grid[i-1][j]=1;
    			 }
    			 System.out.print(grid[i-1][j]);
    		 }
//    		 System.out.println();
    	}
    	
    }
    
    public int[][] place(int tempRow, int col, int[][] grid) {
		// TODO Auto-generated method stub
    	boolean downSign = false;
    	boolean rightslanSign = false;
    	boolean leftslanSign = false;
    	int n = grid.length;


//		System.out.println("place");
//		printPanzi(newgrid);
    	for(int row=tempRow;row<grid.length;row++) {
    		if(row==tempRow) {
    			//TODO 这段要修改，因为遇到2就break了
//    			for(int j=0;j<newgrid.length;j++) {
//    				if(newgrid[row][j]==2) break;
//    				else if(newgrid[row][j]==0) newgrid[row][j]=1;
//    			}
    			grid[row][col]=1;
    			boolean left = true;
    			boolean right = true;
    			int ll = col-1;
    			int rr = col+1;
    			while(left||right) {
    				if(left) {
    					if(ll==-1||grid[row][ll]==2) left=false;
    					else grid[row][ll--]=3;
    				}
    				if(right) {
            			if(rr==grid.length||grid[row][rr]==2) right=false;
            			else grid[row][rr++]=3;
//            			if(rr>newgrid.length-1) right=false;
        				}
    			}
    		}else {
    			for(int j=0;j<grid.length;j++) {
    				if(grid[row][col]==2) downSign = true;
    				if(col-(row-tempRow)>=0&&grid[row][col-(row-tempRow)]==2) leftslanSign = true;
    				if(col+(row-tempRow)<grid.length&&grid[row][col+(row-tempRow)]==2) rightslanSign = true;
    				if(grid[row][col]==0&&downSign==false) grid[row][col]=3;
    				if(col-(row-tempRow)>=0&&grid[row][col-(row-tempRow)]==0&&leftslanSign==false) grid[row][col-(row-tempRow)]=3;
    				if(col+(row-tempRow)<grid.length&&grid[row][col+(row-tempRow)]==0&&rightslanSign==false) grid[row][col+(row-tempRow)]=3;
    			}
    		}
    		}
//		System.out.println("place原来的");
		printPanzi(grid); 
		System.out.println("");
		return grid;
	}
    
    
    public void printPanzi(int[][] grid) {
//    	for(int i=0;i<grid.length;i++) {
//    		for(int j=0;j<grid.length;j++) {
//    			System.out.print(grid[i][j]);
//    		}
//    		System.out.println();
//    	}
    }
    public static void main(String[] args) {
    	TestAnswer ta = new TestAnswer();
    	ReadFile rf1 = new ReadFile();
		List<String> list = rf1.readFileByLines("100_100_0.txt");
		
		rf1.dealWithData(list);
//		boolean status = tb.DFSoldhelper(0, rf.getBaby(), rf.getGrid());
//		boolean status = tb.DFSnewhelper(0, rf.getBaby(), rf.getGrid());
		int babynumber = rf1.getBaby();
		int[][] origingrid = rf1.getGrid();
    	
    	ta.test(origingrid.length, babynumber, origingrid,"outputsa.txt");
    }
}
