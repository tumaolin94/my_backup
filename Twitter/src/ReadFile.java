

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class ReadFile {
	static List<String> list = new ArrayList<>();
    public List<String> readFileByLines(String fileName) {
    	list=new ArrayList<>();
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
    
    public List<Node> dealWithData(List<String> list) {
    	List<Node> res = new ArrayList<>();
    	for(String str:list) {
    		if(str.contains("name,parent_name,dob,dod")) continue;
    		String[] newNode = new String[4];
    		int index = 0;
    		for(String single:str.split(",")) {
    			newNode[index++] = single;
    		}
    		System.out.println(newNode[0]+","+newNode[1]+","+newNode[2]+","+newNode[3]);
    		res.add(new Node(newNode[0],newNode[1],newNode[2],newNode[3]));
    	}
    	return res;
    }
    
    public int getRow() {
    	return this.row;
    }
    
    public int getBaby(){
    	return this.baby;
    }
    
    public List<Integer>[] getTree(){
    	return this.tree;
    }
    
    public int[][] getGrid(){
    	return this.grid;
    }
    public int getMethod() {
    	return this.method;
    }
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);  
        String content = s.next();  
        while(true){  
            if(content != null){  
                System.out.println(content);  
                content = s.next();  
            }else{  
                break;  
            }  
        }  
	}
}
