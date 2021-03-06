package edu.usc.cs561;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ReadFile {
	static List<String> list = new ArrayList<>();

	public List<String> readFileByLines(String fileName) {
		list = new ArrayList<>();
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			System.out.println("ReadFileByLine：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				list.add(tempString);
				System.out.println("line " + line + ": " + tempString);
				line++;
			}
			reader.close();
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

		return list;
	}

	int row = 0;
	int baby = 0;
	List<Integer>[] tree;
	int grid[][];
	int method = -1;

	public void dealWithData(List<String> list) {
		if (list.get(0).equals("DFS")) {
			System.out.println("DFS");
			method = 0;
		} else if (list.get(0).equals("BFS")) {
			System.out.println("BFS");
			method = 1;
		} else if (list.get(0).equals("SA")) {
			System.out.println("SA");
			method = 2;
		}
		row = Integer.parseInt(list.get(1));
		tree = new LinkedList[row];
		grid = new int[row][row];
		baby = Integer.parseInt(list.get(2));
		System.out.println("row" + row + " baby" + baby);

		for (int i = 3; i < 3 + row; i++) {
			tree[i - 3] = new LinkedList<>();
			int count = 0;
			for (int j = 0; j < row; j++) {
				if (list.get(i).charAt(j) == '2') {
					tree[i - 3].add(j);
					grid[i - 3][j] = 2;
				}
			}
		}

		for (int i = 0; i < row; i++) {
			for (int j : tree[i]) {
				System.out.print("row" + i + " " + j + ",");
			}
			System.out.println();
		}
	}

	public int getRow() {
		return this.row;
	}

	public int getBaby() {
		return this.baby;
	}

	public List<Integer>[] getTree() {
		return this.tree;
	}

	public int[][] getGrid() {
		return this.grid;
	}

	public int getMethod() {
		return this.method;
	}

	public static void main(String[] args) {
		ReadFile rf = new ReadFile();
		rf.readFileByLines("input1.txt");
		rf.dealWithData(list);
	}
}
