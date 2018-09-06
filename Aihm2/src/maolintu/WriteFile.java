package maolintu;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;


public class WriteFile {
	public static void WriteDataToFile(char[][] answer, int x, int y) {
		PrintWriter writer;
		try {
			writer = new PrintWriter("output.txt", "UTF-8");
				writer.print((char)(y+'A'));
				writer.print(x+1);
				writer.println();
				for(int i=0;i<answer.length;i++) {
					for(int j=0;j<answer.length;j++) {
						System.out.print(answer[i][j]);
						writer.print(answer[i][j]);
					}
					if(i!=answer.length-1) {
						System.out.println();
						writer.println();
					}
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
