package edu.usc.cs561;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


public class TestList {
	static class bfsNode{
		int x;
		int y;
		bfsNode next;
		public bfsNode(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Set<String> set = new HashSet<>();
		Map<String,Integer> map = new HashMap<>();
		String s1="123";
		String s2=new String("123");
		int[] i1 = new int[3];
		i1[0]=1;
		i1[1]=2;
		i1[2]=3;
		int[] i2 = {1,2,3};
		i2.toString();
		System.out.println(i1.toString());
		System.out.println(String.valueOf(i1));
		System.out.println(i2.toString());
		System.out.println(String.valueOf(i2));
		System.out.println(set.add(i1.toString()));
		System.out.println(set.add(i2.toString()));
		System.out.println(map.put(s1,1));
		System.out.println(map.containsKey(s2));
	}

}
