package maolintu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ReadFile {
	static List<String> list = new ArrayList<>();
	static int count = 0;
    public List<String> readFileByLines(String fileName) {
    	list=new ArrayList<>();
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
//            System.out.println("ReadFileByLine：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
//            int line = 1;
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
            	list.add(tempString);
//                System.out.println("line " + line + ": " + tempString);
//                line++;
            }
            reader.close();
        	
        	row = Integer.parseInt(list.get(0));
        	fruit = Integer.parseInt(list.get(1));
        	time = Double.parseDouble(list.get(2));
        	grid = new char[row][row];
        	for(int i = 3;i<3+row;i++) {
        		 for(int j=0;j<row;j++) {
        			 grid[i-3][j]=(list.get(i).charAt(j));
        			 if(grid[i-3][j]!='*') count++;
        		 }
        	}
        	
        	for(int i=0;i<row;i++) {
        		for(int j=0;j<row;j++) {
        			System.out.print(grid[i][j]+" ");
        		}
        		System.out.println();
        	}
            
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
    int fruit = 0;
    char grid[][];
    double time = 0.0;

    
    public int getRow() {
    	return this.row;
    }
    
    public int getFruit(){
    	return this.fruit;
    }
    
    
    public char[][] getGrid(){
    	return this.grid;
    }

	public static void main(String[] args) {
		ReadFile rf = new ReadFile();
		rf.readFileByLines("input1.txt");
//		rf.dealWithData(list);
	}
}
