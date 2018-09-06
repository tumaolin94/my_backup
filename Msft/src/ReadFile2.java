

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ReadFile2 {
	static List<String> list = new ArrayList<>();
    public List<String> readFileByLines(String fileName) {
    	list=new ArrayList<>();
        File file = new File(fileName);
        BufferedReader reader = null;
        int line=0;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
            	list.add(tempString);
//                System.out.println("line " + line + ": " + tempString);
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

    
    
	public static void main(String[] args) {
		ReadFile2 rf = new ReadFile2();
		rf.readFileByLines("How-many-boxes-will-fit-_InputForSubmission_2.txt");
		int count=0;
		for(int i=1;i<list.size();i++) {
			String[] temp = list.get(i).replace(';', ',').split(",");
			int r = Integer.parseInt(temp[0]);
			int n = Integer.parseInt(temp[1]);
			int l = Integer.parseInt(temp[2]);
			
			if(n==3) {
				double rr = (Math.sqrt(3)*l)/3.0;
//				System.out.println(rr);
				if(r>=rr) count++;
			}else if(n==4) {
				double rr = (Math.sqrt(2)*l)/2.0;
//				System.out.println(rr);
				if(rr<=r) count++;
			}
			
		}		System.out.println(count);
		}
	
}
