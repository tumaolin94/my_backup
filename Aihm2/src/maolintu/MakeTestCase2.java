package maolintu;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class MakeTestCase2 {
	public MakeTestCase2(int inrow, int infruit, double intime) {
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
		Random ran = new Random();
		int x = ran.nextInt(20)+6;
		int y = ran.nextInt(4)+4;
		System.out.println(x+" "+y);
		MakeTestCase2 mt = new MakeTestCase2(22,y,300.0);
		ReadFile rf = new ReadFile();
		rf.readFileByLines("self.txt");
		char[][] grid = rf.getGrid();
		int count = rf.count;
		Agent1 ag = new Agent1();
		Agent2 ag2 = new Agent2();
		Agent2 ag22 = new Agent2();
		Agent3 ag3 = new Agent3();
		
		double time1 = rf.time*1000;
		double time2 = rf.time*1000;
		char[][] newgrid =ag.copyGrid(grid);
		int a1Score = 0;
		int a2Score = 0;
		while(count!=0&&time2>0) {
			Node node = ag2.controlTime4("player1", newgrid, time1, count, rf.fruit);
			a1Score += (count - node.count)* (count - node.count);
			count = node.count;
			System.out.println("Remaining"+count);
			System.out.println("player1 time:" + node.time/1000+" s");
			System.out.println("player1 score "+ a1Score);
			time1 = node.time;
			if(count>0) {
				node = ag.controlTime3("player2", node.grid, time2, count,rf.fruit); 
				a2Score +=(count - node.count)* (count - node.count);
				count = node.count;
				System.out.println("Remaining"+count);
				System.out.println("player2 time:" + node.time/1000+" s");
				System.out.println("player2 score "+ a2Score);
				time2 = node.time;
			}
			newgrid = node.grid;
		}
		if(time1<=0) System.out.println("player1 time out!");
		if(time2<=0) System.out.println("player2 time out!");
		System.out.println("a1= "+a1Score+", a2= "+a2Score);
	}
}
