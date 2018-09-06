package n.queens;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Set;


public class homework {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		homework hw = new homework();

		List<String> list = hw.readFileByLines("input.txt");
		hw.dealWithData(list);
		int method = hw.getMethod();
		if (method == 0) {
			int babynumber = hw.getBaby();
			int[][] grid = hw.getGrid();
			hw.DfsTest(hw.getRow());
			boolean status = hw.DFSnewhelper(0, 0, babynumber, grid);
			hw.WriteDataToFile(status, list, hw.getDfsAnswer());
		} else if (method == 1) {
			List<Integer>[] tree = hw.getTree();

			boolean status = hw.newBfs(tree, hw.getRow(), hw.getBaby());
			System.out.println(status);
			hw.WriteListDataToFile(status, hw.getRow(), list, hw.bfsResult);
		} else if (method == 2) {
			List<Integer>[] tree = hw.getTree();
			int row = hw.getRow();
			int baby = hw.getBaby();
			int tollerence = 0;
			hw.SaMethod(tree, row, baby, tollerence, baby);
			boolean status = hw.solve(row, baby);
			hw.WriteSADataToFile(status, hw.getRow(), list, hw.saRes);
		}
	}

	public class GridNode {
		public int x, y;

		public GridNode(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static List<String> RFlist = new ArrayList<>();
	int row = 0;
	int baby = 0;
	List<Integer>[] tree;
	int grid[][];
	int method = -1;

	/**
	 * readFileBylines
	 */
	public List<String> readFileByLines(String fileName) {
		RFlist = new ArrayList<>();
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				RFlist.add(tempString);
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

		return RFlist;
	}

	/**
	 * dealWithData
	 */
	@SuppressWarnings("unchecked")
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

		for (int i = 3; i < 3 + row; i++) {
			tree[i - 3] = new LinkedList<>();
			for (int j = 0; j < row; j++) {
				if (list.get(i).charAt(j) == '2') {
					tree[i - 3].add(j);
					grid[i - 3][j] = 2;
				}
			}
		}
	}

	public void WriteDataToFile(boolean status, List<String> list, int[][] answer) {
		PrintWriter writer;
		try {
			writer = new PrintWriter("output.txt", "UTF-8");
			if (status) {
				writer.println("OK");
				for (int i = 0; i < answer.length; i++) {
					for (int j = 0; j < answer.length; j++) {
						if (answer[i][j] == 1 || answer[i][j] == 2) {
							writer.print(answer[i][j]);
						} else {
							writer.print(0);
						}
					}
					if (i != answer.length - 1) {
						writer.println();
					}
				}
			} else {
				writer.println("FAIL");

			}

			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void WriteSADataToFile(boolean status, int row, List<String> list, GridNode[] answer) {
		PrintWriter writer;
		try {

			writer = new PrintWriter("output.txt", "UTF-8");
			if (status) {
				char[][] res = new char[row][row];
				for (int i = 3; i < list.size(); i++) {
					res[i - 3] = list.get(i).toCharArray();
				}
				for (GridNode bn : answer) {
					if (bn.x == -1)
						continue;
					res[bn.x][bn.y] = '1';
				}
				writer.println("OK");
				for (int i = 0; i < res.length; i++) {
					for (int j = 0; j < res.length; j++) {
						writer.print(res[i][j]);
					}
					if (i != res.length - 1) {
						writer.println();
					}
				}

			} else {
				writer.println("FAIL");

			}

			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void WriteListDataToFile(boolean status, int row, List<String> list, List<GridNode> answer) {
		PrintWriter writer;
		try {

			writer = new PrintWriter("output.txt", "UTF-8");
			if (status) {
				char[][] res = new char[row][row];
				for (int i = 3; i < list.size(); i++) {
					res[i - 3] = list.get(i).toCharArray();
				}
				for (GridNode bn : answer) {
					if (bn.x == -1)
						continue;
					res[bn.x][bn.y] = '1';
				}
				writer.println("OK");
				for (int i = 0; i < res.length; i++) {
					for (int j = 0; j < res.length; j++) {
						writer.print(res[i][j]);
					}
					if (i != res.length - 1) {
						writer.println();
					}
				}
			} else {
				writer.println("FAIL");

			}

			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * DFS part
	 */
	private int[][] Dfsanswer; // location of baby
	private int dfsRow;

	public void DfsTest(int row) {
		Dfsanswer = new int[row][row];
		this.dfsRow = row;
	}

	public boolean DFSnewhelper(int tempRow, int tempCol, int babynumber, int[][] grid) {
		if (tempRow == dfsRow && babynumber == 0) {

			this.Dfsanswer = Arrays.copyOf(grid, dfsRow);
			return true;
		} else if (tempRow == dfsRow && babynumber != 0) {
			return false;
		} else {
			if (babynumber == 0) {
				this.Dfsanswer = Arrays.copyOf(grid, dfsRow);
				return true;
			}
			int count = 0;
			int trow = tempRow;
			while (trow < dfsRow && count == 0) {

				for (int col = tempCol; col < dfsRow; col++) {

					if (grid[trow][col] == 0) {
						count++;
						int[][] newgrid = dfsPlace(trow, col, grid);
						if (DFSnewhelper(trow, col + 1, babynumber - 1, newgrid))
							return true;
					}
				}
				trow++;
				tempCol = 0;
			}
			return false;
		}
	}

	/**
	 * dfsPlace
	 * 
	 */
	public int[][] dfsPlace(int tempRow, int col, int[][] grid) {
		// TODO Auto-generated method stub
		int n = grid.length;
		int[][] newgrid = new int[n][n];
		for (int i = 0; i < n; i++) {
			newgrid[i] = Arrays.copyOf(grid[i], n);
		}

		for (int row = tempRow; row < newgrid.length; row++) {
			if (row == tempRow) {
				newgrid[row][col] = 1;
				boolean left = true;
				boolean right = true;
				int ll = col - 1;
				int rr = col + 1;
				while (left || right) {
					if (left) {
						if (ll == -1 || newgrid[row][ll] == 2)
							left = false;
						else
							newgrid[row][ll--] = 3;
					}
					if (right) {
						if (rr == newgrid.length || newgrid[row][rr] == 2)
							right = false;
						else
							newgrid[row][rr++] = 3;
					}
				}
			}
		}
		int leftSlanCol = col - 1;
		int rightSlanCol = col + 1;
		int rrow = tempRow + 1;
		while (leftSlanCol >= 0 && rrow < grid.length && newgrid[rrow][leftSlanCol] != 2) {
			newgrid[rrow++][leftSlanCol--] = 3;
		}
		rrow = tempRow + 1;
		while (rightSlanCol < grid.length && rrow < grid.length && newgrid[rrow][rightSlanCol] != 2) {
			newgrid[rrow++][rightSlanCol++] = 3;
		}
		rrow = tempRow + 1;
		while (col < grid.length && rrow < grid.length && newgrid[rrow][col] != 2) {
			newgrid[rrow++][col] = 3;
		}
		return newgrid;
	}

	public int[][] getDfsAnswer() {
		return this.Dfsanswer;
	}

	/**
	 * BFS part
	 * 
	 */
	GridNode bfsRes = new GridNode(0, 0);
	List<GridNode> bfsResult = new ArrayList<>();

	/**
	 * newBFS
	 */
	public boolean newBfs(List<Integer>[] tree, int row, int baby) {
		Queue<List<GridNode>> queue = new LinkedList<>();
		GridNode root = new GridNode(-1, -1);
		List<GridNode> rootlist = new ArrayList<>();
		rootlist.add(root);
		queue.offer(rootlist);
		while (!queue.isEmpty()) {
			int size = queue.size();
			System.out.println(size);
			for (int i = 0; i < size; i++) {
				List<GridNode> tempList = queue.poll();
				GridNode tempNode = tempList.get(tempList.size() - 1);
				int tempRow = (tempNode.x == -1 || tree[tempNode.x].size() == 0) ? tempNode.x + 1 : tempNode.x;

				int count = 0;
				while (count == 0 && tempRow < row) {
					int tempCol = (tempRow == tempNode.x) ? tempNode.y : 0;
					while (tempCol < row) {
						// TODO:
						// System.out.println(tempRow);
						if (tree[tempRow].contains(tempCol) && count > 0) {
							break;
						} else if ((tree[tempRow].size() == 0 || (!tree[tempRow].contains(tempCol)))
								&& isBfsPlace(tempList, tempRow, tempCol, tree)) {
							tempList.add(new GridNode(tempRow, tempCol));
							if (tempList.size() == baby + 1) {
								bfsResult = tempList;
								return true;
							}
							queue.offer(new ArrayList<>(tempList));
							tempList.remove(tempList.size() - 1);
							count++;
						}
						tempCol++;
					}
					if (count == 0) {
						tempRow++;
					}
				}
			}
		}
		return false;
	}

	public static boolean isBfsPlace(List<GridNode> temp, int x, int y, List<Integer>[] tree) {
		boolean downSign = true;
		boolean leftSign = true;
		boolean rightslanSign = true;
		boolean leftslanSign = true;
		if (temp.size() == 1 && temp.get(0).x == -1) {
			return true;
		} else {

			for (int i = 1; i < temp.size(); i++) {
				GridNode tempNode = temp.get(i);
				int tempX = tempNode.x;
				int tempY = tempNode.y;
				if (Math.abs(tempX - x) == Math.abs(tempY - y) || tempY == y || tempX == x) {
					if (tempX == x) {
						leftSign = false;
						if (tree[x].size() != 0) {
							for (int treeY : tree[x]) {
								if (treeY > tempY && treeY < y) {
									leftSign = true;
									break;
								}
							}
						}
					} else if (tempY == y) {
						downSign = false;
						for (int treeX = tempX; treeX < x; treeX++) {
							if (tree[treeX].contains(y)) {
								downSign = true;
								break;
							}
						}
					} else if (tempY < y) {
						leftslanSign = false;
						for (int treeX = tempX + 1; treeX < x; treeX++) {
							if (tree[treeX].size() != 0) {
								for (int treeY : tree[treeX]) {
									if (treeY - tempY == treeX - tempX) {
										leftslanSign = true;
										break;
									}
								}
								if (leftslanSign == true)
									break;
							}
						}
					} else if (tempY > y) {
						rightslanSign = false;
						for (int treeX = tempX + 1; treeX < x; treeX++) {
							if (tree[treeX].size() != 0) {
								for (int treeY : tree[treeX]) {
									if (treeY - tempY == tempX - treeX) {
										rightslanSign = true;
										break;
									}
								}
								if (rightslanSign == true)
									break;
							}
						}
					}
				}
			}
		}

		return (downSign && leftSign && rightslanSign && leftslanSign);
	}

	/**
	 * 
	 * SA part
	 */

	List<GridNode[]> saList = new ArrayList<>();
	int saRow;
	SaState currentState;
	SaState nextState;
	int tollerenceCost;
	List<Integer>[] saTree;
	double tempreture;
	public GridNode saRes[];
	int currentCost;
	boolean placeFull = true;
	Set<String> set = new HashSet<>();

	public void SaMethod(List<Integer>[] tree, int row, int babynumber, int tollerence, double tempreture) {
		this.saRow = row;
		this.tollerenceCost = tollerence;
		this.tempreture = tempreture;
		this.saTree = tree;
		currentState = new SaState(tree, row, babynumber);
		this.placeFull = currentState.placeFull;
		System.out.println(currentState.placeFull);
		if (placeFull == false)
			return;
		currentCost = currentState.getCost();

	}

	public boolean solve(int row, int baby) {
		if (placeFull == false)
			return false;
		long initialTime = System.currentTimeMillis();
		while (currentCost != 0) {

			double delta;
			double probability;
			double rand;
			double temp;
			for (temp = tempreture; (currentCost != 0); temp *= 0.99) {
				for (int i = 0; i < (baby) && (currentCost != 0); i++) {
					nextState = currentState.getNextState();
					int nextCost = nextState.getCost();
					delta = nextCost - currentCost;
					probability = Math.exp(-delta / temp);
					rand = Math.random();

					if (System.currentTimeMillis() - initialTime >= 290000) {
						System.out.println("Time Over");
						return false;
					}

					if (delta < 0) {
						currentState = nextState;
						currentCost = nextCost;
					} else if (rand <= probability) {
						currentState = nextState;
						currentCost = nextCost;
					}
					if (currentCost == 0) {
						saRes = currentState.getGrid();
						return true;
					}
				}
			}
		}

		if (currentCost == 0) {
			System.out.println("set.size()" + set.size());
			saRes = currentState.getGrid();
			return true;
		}
		return false;
	}

	public char[] changeNodeToIntArray(GridNode[] q) {
		char[] res = new char[q.length];
		for (int i = 0; i < q.length; i++) {
			res[i] = (char) (q[i].x * saRow + q[i].y);
		}
		return res;
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

	public class SaState {

		Random randomGenerator = new Random();
		List<Integer>[] tree;
		int row;
		int cost;
		GridNode board[];
		int[] number;
		int babynumber;
		boolean placeFull = true;

		public boolean ifNoBabyAndNoTree(List<Integer>[] tree, GridNode[] q, int x, int y) {
			for (GridNode node : q) {
				if (node != null && node.x == x && node.y == y) {
					return false;
				}
			}
			for (int tre : tree[x]) {
				if (tre == y) {
					return false;
				}
			}
			return true;
		}

		public SaState(List<Integer>[] tree, int row, int babynumber) {
			this.row = row;
			this.board = new GridNode[babynumber];
			this.tree = tree;
			this.babynumber = babynumber;
			int count = 0;
			number = new int[row];
			for (int i = 0; i < row && count < babynumber; i++) {
				for (int j = 0; j < row && count < babynumber; j++) {
					if (ifNoBabyAndNoTree(tree, board, i, j)) {
						board[count] = new GridNode(i, j);
						number[i]++;
						count++;
					}
				}
			}
			if (count < babynumber)
				placeFull = false;
		}

		public SaState(int row, GridNode q[], int babynumber, List<Integer>[] tree, int[] number) {
			this.row = row;
			this.board = q;
			cost = 0;
			this.babynumber = babynumber;
			this.tree = tree;
			this.number = number;
		}

		public SaState getNextState() {
			int i;
			GridNode[] nextStateBoard = new GridNode[babynumber];
			int rand = randomGenerator.nextInt(babynumber);
			for (i = 0; i < babynumber; i++) {
				nextStateBoard[i] = new GridNode(board[i].x, board[i].y);
				if (rand == i) {
					int tempX = randomGenerator.nextInt(row);
					int tempY = randomGenerator.nextInt(row);
					while ((tempX == board[i].x && tempY == board[i].y)
							|| ifNoBabyAndNoTree(tree, nextStateBoard, tempX, tempY) == false) {
						tempX = randomGenerator.nextInt(row);
						tempY = randomGenerator.nextInt(row);
					}
					nextStateBoard[i] = new GridNode(tempX, tempY);
					number[board[i].x]--;
					number[tempX]++;
				}
			}

			return new SaState(row, nextStateBoard, babynumber, tree, number);
		}

		public void calculateCost() {
			int i, j;
			cost = 0;
			for (i = 0; i < babynumber; i++) {

				for (j = 0; j < babynumber; j++) {
					if (i == j)
						continue;
					if (board[i].x == board[j].x// same row
							|| board[i].y == board[j].y // same column
							|| (Math.abs(board[i].x - board[j].x) == Math.abs(board[i].y - board[j].y)) // same diagonal
					) {
						GridNode small = board[i];
						GridNode large = board[j];
						if (small.x > large.x) {
							small = board[j];
							large = board[i];
						}
						if (small.x == large.x) {
							int smallY = Math.min(small.y, large.y);
							int largeY = Math.max(small.y, large.y);
							if (tree[small.x].size() != 0) {
								for (int treeY : tree[small.x]) {
									if (treeY > smallY && treeY < largeY) {
										cost--;
										break;
									}
								}
							}
						} else if (small.y == large.y) {
							for (int treeX = small.x + 1; treeX < large.x; treeX++) {
								if (tree[treeX].contains(small.y)) {
									cost--;
									break;
								}
							}
						} else if (small.y > large.y) {
							boolean rightslanSign = false;
							for (int treeX = small.x + 1; treeX < large.x; treeX++) {
								if (tree[treeX].size() != 0) {
									for (int treeY : tree[treeX]) {
										if (treeY - small.y == small.x - treeX) {
											rightslanSign = true;
											break;
										}
									}
									if (rightslanSign == true) {
										cost--;

										break;
									}
								}
							}
						} else if (small.y < large.y) {
							boolean leftslanSign = false;
							for (int treeX = small.x + 1; treeX < large.x; treeX++) {
								if (tree[treeX].size() != 0) {
									for (int treeY : tree[treeX]) {
										if (treeY - small.y == treeX - small.x) {
											leftslanSign = true;
											break;
										}
									}
									if (leftslanSign == true) {
										cost--;
										break;
									}
								}
							}
						}
						cost++;
					}
				}

			}

		}

		public int getCost() {
			calculateCost();
			return cost;
		}

		public GridNode[] getGrid() {
			return board;
		}

	}
}
