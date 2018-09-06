package edu.usc.cs561;

import java.util.List;

import edu.usc.cs561.ReadFile;
import edu.usc.cs561.TestAnswer;
import edu.usc.cs561.WriteFile;


public class SaTest {

    public static void main(String[] args) throws Exception{
        long time;
        int tollerence = 0;
        ReadFile rf = new ReadFile();
        List<String> list = rf.readFileByLines("100_100_0.txt");
//        List<String> list = rf.readFileByLines("500.txt");
		rf.dealWithData(list);
		List<Integer>[] tree = rf.getTree();
		int row = rf.getRow();
		int baby = rf.getBaby();
            System.out.println("N = " + row);
            long sum = 0;
                time = System.currentTimeMillis();
            	SimulatedAnnealing nq = new SimulatedAnnealing(tree,row,baby,tollerence,5000);
            	boolean status = nq.solve();
            	WriteFile wf = new WriteFile();
            	wf.WriteSADataToFile(status, rf.getRow(),list,  nq.saRes);
                time = System.currentTimeMillis()-time;
                System.out.println("程序运行时间： "+time+"ms");
                sum+=time;
            time = System.currentTimeMillis();
            time = System.currentTimeMillis()-time;
//            nq.show();
            System.out.println("xierujieguo");
        	TestAnswer ta = new TestAnswer();
    		
    		int babynumber = rf.getBaby();
    		int[][] origingrid = rf.getGrid();
        	
        	ta.test(origingrid.length, babynumber, origingrid,"outputsa.txt");
//            System.out.println("程序平均运行时间： "+sum/5+"ms");
//        }

    }
}
