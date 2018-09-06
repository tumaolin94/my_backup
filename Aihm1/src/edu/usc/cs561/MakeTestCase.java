package edu.usc.cs561;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class MakeTestCase {
	public static void main(String[] args) {
		int row = 18;
		int baby = 18;
		int tree = 30;
		PrintWriter writer;
		Set<Integer> set = new TreeSet<>();
		Random randomGenerator = new Random();
		int count=0;
		
		int[] maketree = new int[tree];
 		while(set.size()<=tree) {
			int temp = randomGenerator.nextInt(row*row);
			set.add(temp);
		}
 		
		try {
			count=0;
			writer = new PrintWriter(row+"_"+baby+"_"+tree+".txt", "UTF-8");
				writer.println("BFS");
				writer.println(row);
				writer.println(baby);
				for(int i=0;i<row;i++) {
					for(int j=0;j<row;j++) {
						
							if(set.contains(i*row+j)) {
								System.out.println(count++);
								writer.print(2);
							}else writer.print(0); 
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
}
