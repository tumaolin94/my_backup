package edu.usc.cs561;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/**
* @author Maolin Tu
* 
* This is a Depth-First-Search method for a Zookeeper Simulation
*/
public class DfsTest {
	
	private int[][] Dfsanswer; // location of lizards
	private int dfsRow;
	
	   /**
	   * This method Constructs  a DFS Objection.
	   * @param row   the total rows of the board.
	   */
	public DfsTest(int row) {
		Dfsanswer = new int[row][row];
		this.dfsRow = row;
	}

	   /**
	   * This method executes  a DFS function.
	   * @param tree The position of trees, row   the total rows of the board, baby  the number of lizards
	   * @return true  if there is at least one solution, false  if there is no solution
	   */
	public boolean DFSnewhelper(int tempRow, int tempCol, int babynumber, int[][] grid) {
		if(tempRow == dfsRow&&babynumber==0) {
			System.out.println("tempRow == dfsRow&&babynumber==0");
			this.Dfsanswer = Arrays.copyOf(grid, dfsRow);
			return true;
		}else if(tempRow==dfsRow&&babynumber!=0){
			return false;
		}else{
			if(babynumber==0) {
				this.Dfsanswer = Arrays.copyOf(grid, dfsRow);
				System.out.println("babynumber==0");
				return true;
			}
			int count=0;
			int trow = tempRow;
//			TODO: 现在状况是为了避免有一行全是2会返回fail的情况,会继续往下走。但产生了结果是如果全是3也会继续往下走
			while(trow<dfsRow&&count==0) {
				
            	for (int col = tempCol; col < dfsRow; col++) {  

            		if(grid[trow][col]==0) {
//            		System.out.println("此时的"+tempRow+","+tempCol);
                	count++;
//                	System.out.println(babynumber+"在"+trow+","+col);
                    int[][] newgrid  = dfsPlace(trow, col, grid);
//                    printPanzi(newgrid);
                    if(DFSnewhelper(trow , col+1, babynumber-1,newgrid)) {
                    	
                    	System.out.println(babynumber+"在"+trow+","+col);
                    	System.out.println("此时的"+tempRow+","+tempCol);
                    	for(int i=0;i<newgrid.length;i++) {
                		for(int j=0;j<newgrid.length;j++) {
                			System.out.print(newgrid[i][j]);
                		}
                		System.out.println();
                	}
                    	return true; 
                    }
//                	if(DFSnewhelper(trow + 1, 0, babynumber-1,newgrid)) return true;  
            		}  
            	}
            	trow++;
            	tempCol=0;
			}
         return false;
        }  
	}
	
	
	
	
	public int DFSCounter(int tempRow, int tempCol, int babynumber, int[][] grid) {
		//System.out.println("第"+tempRow+"行，还剩下   "+babynumber);
		if(tempRow == dfsRow&&babynumber==0) {
//			for(int i=0;i<row;i++) {
//				for(int j=0;j<row;j++)
//				//System.out.println("1111location "+grid[i][j]);
//			}
			this.Dfsanswer = Arrays.copyOf(grid, dfsRow);
			System.out.println(1);
			return 1;
		}else if(tempRow==dfsRow&&babynumber!=0){
			System.out.println(0);
			return 0;
		}else{
			if(babynumber==0) {
				//System.out.println("2222location");
//				for(int i=0;i<row;i++) {
//					for(int j=0;j<row;j++)
//						//System.out.print(grid[i][j]);
//					//System.out.println();
//				}
					//System.out.println("\n");
				this.Dfsanswer = Arrays.copyOf(grid, dfsRow);
				System.out.println(1);
				return 1;
			}
			int count=0;
			int trow = tempRow;
//			TODO: 现在状况是为了避免有一行全是2会返回fail的情况,会继续往下走。但产生了结果是如果全是3也会继续往下走
			int left=0;
			int right=0;
			while(trow<dfsRow&&count==0) {
				
            	for (int col = tempCol; col < dfsRow; col++) {  

            		if(grid[trow][col]==0) {
                	count++;
                    
                    int[][] newgrid  = dfsPlace(trow, col, grid);
                    left=DFSCounter(tempRow , col+1, babynumber-1,newgrid); 
                    
                	right=DFSCounter(trow + 1, 0, babynumber-1,newgrid);  
                	System.out.println(left+","+right);
            		}  
            	}
            	trow++;
            	tempCol=0;
			}
         return left+right;
        }  
	}
	/**
	 * place
	 * 
	 * */	
    public int[][] dfsPlace(int tempRow, int col, int[][] grid) {
		// TODO Auto-generated method stub

    	int n = grid.length;
		int[][] newgrid = new int[n][n];
		for(int i=0;i<n;i++) {
			newgrid[i] = Arrays.copyOf(grid[i], n);
		}

//		System.out.println("place");
//		printPanzi(newgrid);
    	for(int row=tempRow;row<newgrid.length;row++) {
    		if(row==tempRow) {
    			//TODO 这段要修改，因为遇到2就break了
//    			for(int j=0;j<newgrid.length;j++) {
//    				if(newgrid[row][j]==2) break;
//    				else if(newgrid[row][j]==0) newgrid[row][j]=1;
//    			}
    			newgrid[row][col]=1;
    			boolean left = true;
    			boolean right = true;
    			int ll = col-1;
    			int rr = col+1;
    			while(left||right) {
    				if(left) {
    					if(ll==-1||newgrid[row][ll]==2) left=false;
    					else newgrid[row][ll--]=3;
    				}
    				if(right) {
            			if(rr==newgrid.length||newgrid[row][rr]==2) right=false;
            			else newgrid[row][rr++]=3;
//            			if(rr>newgrid.length-1) right=false;
        				}
    			}
    		}
    		}
    	int leftSlanCol = col-1;
    	int rightSlanCol = col+1;
    	int rrow = tempRow+1;
    	while(leftSlanCol>=0&&rrow<grid.length&&newgrid[rrow][leftSlanCol]!=2) {
    		newgrid[rrow++][leftSlanCol--]=3;
    	}
    	rrow = tempRow+1;
    	while(rightSlanCol<grid.length&&rrow<grid.length&&newgrid[rrow][rightSlanCol]!=2) {
    		newgrid[rrow++][rightSlanCol++]=3;
    	}    	
    	rrow = tempRow+1;
    	while(col<grid.length&&rrow<grid.length&&newgrid[rrow][col]!=2) {
    		newgrid[rrow++][col]=3;
    	}    
//		System.out.println("place原来的");
//		printPanzi(newgrid);    	
//		System.out.println("");
		return newgrid;
	}

	/**
	 * newplace
	 * 
	 * */	
    public int[][] newPlace(int tempRow, int col, int[][] grid) {
		// TODO Auto-generated method stub
    	boolean downSign = false;
    	boolean rightslanSign = false;
    	boolean leftslanSign = false;
    	int n = grid.length;
		int[][] newgrid = new int[n][n];
		for(int i=0;i<n;i++) {
			newgrid[i] = Arrays.copyOf(grid[i], n);
		}

    	for(int row=tempRow;row<newgrid.length;row++) {
    		if(row==tempRow) {
    			newgrid[row][col]=1;
    			boolean left = true;
    			boolean right = true;
    			int ll = col-1;
    			int rr = col+1;
    			while(left||right) {
    				if(left) {
    					if(ll==-1||newgrid[row][ll]==2) left=false;
    					else newgrid[row][ll--]=3;
    				}
    				if(right) {
            			if(rr==newgrid.length||newgrid[row][rr]==2) right=false;
            			else newgrid[row][rr++]=3;
//            			if(rr>newgrid.length-1) right=false;
        				}
    			}
    		}else {
    			for(int j=0;j<newgrid.length;j++) {
    				if(newgrid[row][col]==2) downSign = true;
    				if(col-(row-tempRow)>=0&&newgrid[row][col-(row-tempRow)]==2) leftslanSign = true;
    				if(col+(row-tempRow)<newgrid.length&&newgrid[row][col+(row-tempRow)]==2) rightslanSign = true;
    				if(newgrid[row][col]==0&&downSign==false) newgrid[row][col]=3;
    				if(col-(row-tempRow)>=0&&newgrid[row][col-(row-tempRow)]==0&&leftslanSign==false) newgrid[row][col-(row-tempRow)]=3;
    				if(col+(row-tempRow)<newgrid.length&&newgrid[row][col+(row-tempRow)]==0&&rightslanSign==false) newgrid[row][col+(row-tempRow)]=3;
    			}
    		}
    		}
		return newgrid;
	}
    
    
    public void printBoard(int[][] grid) {
    	for(int i=0;i<grid.length;i++) {
    		for(int j=0;j<grid.length;j++) {
    			System.out.print(grid[i][j]);
    		}
    		System.out.println();
    	}
    }


    
  public int[][] getDfsAnswer() {
	return this.Dfsanswer;
}
    public static void main(String[] args) {
    	ReadFile rf = new ReadFile();
		long start=System.currentTimeMillis();   //获取开始时间  
		List<String> list = rf.readFileByLines("20_20_30.txt");
		rf.dealWithData(list);
		int babynumber = rf.getBaby();
		int[][] grid = rf.getGrid();
    	DfsTest tb = new DfsTest(rf.getRow());
    	boolean status = tb.enter(babynumber, grid);
    	WriteFile wf = new WriteFile();
    	wf.WriteDataToFile(status, list,  tb.getDfsAnswer());
		long end=System.currentTimeMillis(); //获取结束时间  
    	TestAnswer ta = new TestAnswer();
    	ta.test(tb.getDfsAnswer().length, babynumber, grid,"output.txt");
		System.out.println("程序运行时间： "+(end-start)+"ms");   
    }
    
    public boolean enter(int babynumber, int[][] grid) {
//    	Testbefore tb = new Testbefore(8);
    	ReadFile rf = new ReadFile();
		
    	boolean status = DFSnewhelper(0, 0, babynumber, grid);
    	return status;
    }


}
