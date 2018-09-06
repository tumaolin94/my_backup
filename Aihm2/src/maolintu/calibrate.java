package maolintu;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class calibrate {
	public char[][] MakeTestCase2(int inrow, int infruit) {
		int fruit = infruit;
		PrintWriter writer;
		Random randomGenerator = new Random();
 		char[][] grid = new char[inrow][inrow];
//			writer = new PrintWriter(row+"_"+fruit+"_"+time+".txt", "UTF-8");
				for(int i=0;i<inrow;i++) {
					for(int j=0;j<inrow;j++) {
						int temp = randomGenerator.nextInt(fruit);
						grid[i][j]=(char) temp;
					}
				}
		return grid;	

	}
	public calibrate() {
		long start=System.currentTimeMillis();
		char[][] grid = MakeTestCase2(10000,5);
		int init_alpha = Integer.MIN_VALUE;
		int init_beta  = Integer.MAX_VALUE;
		
		Set<Character> set = new HashSet<>();
		for(int i=0;i<grid.length;i++) {
			for(int j=0;j<grid[0].length;j++) {
				set.add(grid[i][j]);
			}
		}
		long end = System.currentTimeMillis() - start;
		System.out.println(Agent1.moveCount+" time "+end+"ms");
		PrintWriter writer;
		try {
			writer = new PrintWriter("calibrate.txt", "UTF-8");
				writer.print(end);
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		calibrate ca = new calibrate();
	}
}
