

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

public class ReadFile3 {
	static List<String> list = new ArrayList<>();
    public List<String> readFileByLines(String fileName) {
    	list=new ArrayList<>();
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
            	list.add(tempString);
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
		ReadFile3 rf = new ReadFile3();
		rf.readFileByLines("PracticeInput (6).txt");
		List<int[]> res = new LinkedList<>();
		int n=0;
		for(String str:list) {
			String[] sp = str.split(" ");
			int[] temp = new int[sp.length];
			for(int i=0;i<sp.length;i++) {
				temp[i]=Integer.parseInt(sp[i]);
			}
			n=temp.length;
			res.add(temp);
		}
		for(int j=0;j<n;j++) {
		for(int i=0;i<res.size();i++) {
			
				System.out.print(res.get(i)[j]+" ");
			}
			System.out.println();
		}
	}
}
