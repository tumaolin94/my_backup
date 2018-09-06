package practise;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class PathFinding2 {
	static class Node{
		int x;
		int y;
		int key;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
			this.key = 0;
		}
	}
	public static int pathfind(char[][] matrix) {
		int res = -1;
		int m = matrix.length;
		int n = matrix[0].length;
		Queue<Node> queue = new LinkedList<>();
		boolean[][][] visited = new boolean[m][n][1];
		boolean[][] v2 = new boolean[m*n][1<<26];
		Node start = new Node(0,0);
		Node end = new Node(0,0);
		Node temp = start;
		for(int i=0;i<matrix.length;i++) {
			for(int j = 0;j<matrix[0].length;j++) {
				if(matrix[i][j]=='2') {
					start.x = i;
					start.y = j;
				}
				if(matrix[i][j]=='3') {
					end.x = i;
					end.y = j;
				}
			}
		}
		queue.offer(start);
		int[][] dirs = new int[][] {{-1,0},{1,0},{0,-1},{0,1}};
		while(!queue.isEmpty()) {
			int size = queue.size();
			res++;
			for(int i=0;i<size;i++) {
				temp = queue.poll();
				int x = temp.x;
				int y = temp.y;
				if(x == end.x&&y==end.y) return res;
//				System.out.println("  "+x+","+y+": "+matrix[x][y]);
				int tempKey = temp.key;
				for(int[] dir: dirs) {
					int new_x = x+dir[0];
					int new_y = y+dir[1];
					int new_key = tempKey;
					int new_position = new_x * n + new_y;
					if(new_x<0||new_x>=m||new_y<0||new_y>=n||matrix[new_x][new_y]=='0'||v2[new_position][tempKey]) continue;
					
					if(matrix[new_x][new_y]>='a'&&matrix[new_x][new_y]<='z') {
						new_key = tempKey|1<<(matrix[new_x][new_y]-'a');
					}
					if(v2[new_position][new_key]==false) {
						v2[new_position][new_key] = true;
						Node newNode = new Node(new_x,new_y);
						newNode.key = new_key;
						if(matrix[new_x][new_y]<='Z'&&matrix[new_x][new_y]>='A'){
//							System.out.println("Door is "+matrix[new_x][new_y]);
//							System.out.println("current keys"+ new_key);
//							System.out.println("match:"+(new_key&(1<<(matrix[new_x][new_y]-'A'))));
		                    if((new_key&(1<<(matrix[new_x][new_y]-'A'))) != 0) {
		                    	queue.offer(newNode);
		                    }
		                          
		                }  
		                else queue.offer(newNode);    
					}

				}
			}

		}
		
		return -1;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Answer: "+ pathfind(new char[][] {
			{'0','z','1'},
			{'0','1','2'},
			{'0','0','Z'},
			{'0','1','3'}
		}));
		System.out.println("Answer: "+ pathfind(new char[][] {
			{'1','a','1'},
			{'1','x','2'},
			{'1','0','A'},
			{'1','1','X'},
			{'3','1','1'}
		}));
		System.out.println("Answer: "+ pathfind(new char[][] {
			{'0','a','1'},
			{'0','0','2'},
			{'0','0','A'},
			{'0','1','B'},
			{'3','1','1'}
		}));
		System.out.println("Answer: "+ pathfind(new char[][] {
			{'0','D','1','1'},
			{'c','a','0','1'},
			{'C','2','A','1'},
			{'1','B','0','1'},
			{'3','B','d','1'}
		}));
	}

}
