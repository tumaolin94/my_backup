package solution.leetcode;

import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] str = new String[5];
		str[1]=str[0]+"s";
		str[2]=""+"s";
		System.out.println(str[1]);
		System.out.println(str[2]);
		
		int[][] grid=new int[2][2];
//		grid[0][0] = 11;
		test(grid);
		System.out.println(grid[0][0]);
		
	}
	
	public static int[][] test(int[][] grid){
		int n = grid.length;
		int[][] newgrid = new int[n][n];
		newgrid = Arrays.copyOf(grid, n);
		newgrid[0][0]=123;
		printPanzi(grid);
		System.out.println();
		printPanzi(newgrid);
		return newgrid;
	}
	
    public static void printPanzi(int[][] grid) {
    	for(int i=0;i<grid.length;i++) {
    		for(int j=0;j<grid.length;j++) {
    			System.out.print(grid[i][j]);
    		}
    		System.out.println();
    	}
    }
}
