package maolintu;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class MakeTestCase {
	public MakeTestCase(int inrow, int infruit, double intime) {
		int row = inrow;
		int fruit = infruit;
		double time = intime;
		PrintWriter writer;
		Set<Integer> set = new TreeSet<>();
		Random randomGenerator = new Random();
 		
		try {
//			writer = new PrintWriter(row+"_"+fruit+"_"+time+".txt", "UTF-8");
			writer = new PrintWriter("self.txt", "UTF-8");
				writer.println(row);
				writer.println(fruit);
				writer.println(time);
				for(int i=0;i<row;i++) {
					for(int j=0;j<row;j++) {
						int temp = randomGenerator.nextInt(fruit);
						writer.print(temp);
					}
					if(i!=row-1) {
					writer.println();
					}
				}
					

			writer.close();
			System.out.println("successful");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		MakeTestCase2 mt = new MakeTestCase2(5,3,300.0);
//		MakeTestCase mt = new MakeTestCase(26,10,300.0);
//		ReadFile rf = new ReadFile();
//		rf.readFileByLines("self.txt");
//		char[][] grid = rf.getGrid();
//		int count = rf.count;
//		Agent1 ag1 = new Agent1();
//		Agent1 ag2 = new Agent1();
//		double time1 = rf.time;
//		double time2 = rf.time;
//		char[][] newgrid =ag1.copyGrid(grid);
//		int a1Score = 0;
//		int a2Score = 0;
//		while(count!=0&&time1>0&&time2>0) {
//			Node node = ag1.controlTime3("player1", newgrid, time1, count);
//			a1Score += (count - node.count)* (count - node.count);
//			count = node.count;
//			System.out.println("Remaining"+count);
//			System.out.println("player1 time:" + node.time+" s");
//			System.out.println("player1 score "+ a1Score);
//			time1 = node.time;
//			if(count>0) {
//				node = ag2.controlTime2("player2", node.grid, time2, count); 
//				a2Score +=(count - node.count)* (count - node.count);
//				count = node.count;
//				System.out.println("Remaining"+count);
//				System.out.println("player2 time:" + node.time+" s");
//				System.out.println("player2 score "+ a2Score);
//				time2 = node.time;
//			}
//			newgrid = node.grid;
//		}
//		if(time1<=0) System.out.println("player1 time out!");
//		if(time2<=0) System.out.println("player2 time out!");
//		System.out.println("a1= "+a1Score+", a2= "+a2Score);
	}
}
