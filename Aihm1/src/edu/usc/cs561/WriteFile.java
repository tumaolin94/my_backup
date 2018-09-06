package edu.usc.cs561;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;


public class WriteFile {
	public void WriteDataToFile(boolean status, List<String> list, int[][] answer) {
		PrintWriter writer;
		try {
			writer = new PrintWriter("output.txt", "UTF-8");
			int count=0;
			if(status) {
				writer.println("OK");
				for(int i=0;i<answer.length;i++) {
					for(int j=0;j<answer.length;j++) {
						if(answer[i][j]==1||answer[i][j]==2) {
							if(answer[i][j]==1) count++;
							System.out.print(answer[i][j]);
							writer.print(answer[i][j]); 
						}else {
							System.out.print(0);
							writer.print(0);
						}
					}
					System.out.println();
					writer.println();
					System.out.println("有"+count+"个");
				}
					
//					char[] temp = list.get(i+3).toCharArray();
//					if(location[i][0]>=0)
//						temp[location[i][0]]='1';
//					System.out.println(temp);
//					writer.println(temp);
			}else {
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
	
	
	
	public void WriteSADataToFile(boolean status, int row,List<String> list, GridNode[] answer) {
		PrintWriter writer;
		try {
			
			writer = new PrintWriter("outputsa.txt", "UTF-8");
			if(status) {
				char[][] res = new char[row][row];
				for(int i=3;i<list.size();i++) {
					res[i-3]=list.get(i).toCharArray();
				}
				for(GridNode bn:answer) {
					if(bn.x==-1) continue;
					res[bn.x][bn.y]='1';
				}
				writer.println("OK");
				for(int i=0;i<res.length;i++) {
					for(int j=0;j<res.length;j++) {
						System.out.print(res[i][j]);
						writer.print(res[i][j]);
					}
					
					System.out.println();
					writer.println();
				}
					
//					char[] temp = list.get(i+3).toCharArray();
//					if(location[i][0]>=0)
//						temp[location[i][0]]='1';
//					System.out.println(temp);
//					writer.println(temp);
			}else {
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
	
	public void WriteListDataToFile(boolean status, int row,List<String> list, List<GridNode> answer) {
		PrintWriter writer;
		try {
			
			writer = new PrintWriter("output.txt", "UTF-8");
			if(status) {
				char[][] res = new char[row][row];
				for(int i=3;i<list.size();i++) {
					res[i-3]=list.get(i).toCharArray();
				}
				for(GridNode bn:answer) {
					if(bn.x==-1) continue;
					res[bn.x][bn.y]='1';
				}
				writer.println("OK");
				for(int i=0;i<res.length;i++) {
					for(int j=0;j<res.length;j++) {
						System.out.print(res[i][j]);
						writer.print(res[i][j]);
					}
					System.out.println();
					writer.println();
				}
					
//					char[] temp = list.get(i+3).toCharArray();
//					if(location[i][0]>=0)
//						temp[location[i][0]]='1';
//					System.out.println(temp);
//					writer.println(temp);
			}else {
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
	
	public void WriteNewListDataToFile(boolean status, int row,List<String> list, List<Integer> answer) {
		PrintWriter writer;
		try {
			
			writer = new PrintWriter("output.txt", "UTF-8");
			if(status) {
				char[][] res = new char[row][row];
				for(int i=3;i<list.size();i++) {
					res[i-3]=list.get(i).toCharArray();
				}
				for(int bn:answer) {
					
					if(bn==-1) continue;
					
					res[bn/row][bn%row]='1';
				}
				writer.println("OK");
				for(int i=0;i<res.length;i++) {
					for(int j=0;j<res.length;j++) {
						System.out.print(res[i][j]);
						writer.print(res[i][j]);
					}
					System.out.println();
					writer.println();
				}
					
//					char[] temp = list.get(i+3).toCharArray();
//					if(location[i][0]>=0)
//						temp[location[i][0]]='1';
//					System.out.println(temp);
//					writer.println(temp);
			}else {
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
}
