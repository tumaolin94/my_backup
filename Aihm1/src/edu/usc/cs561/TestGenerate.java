package edu.usc.cs561;

import java.util.Random;

public class TestGenerate {
	public static void main(String args[]) {
		Random randomGenerator = new Random();
		for(int i=0;i<20;i++) {
			System.out.println(randomGenerator.nextInt(4));
		}
	}
}
