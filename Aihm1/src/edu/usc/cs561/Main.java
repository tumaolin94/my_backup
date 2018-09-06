package edu.usc.cs561;

import java.util.List;

public class Main {

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//    	ReadFile rf = new ReadFile();
//    	
//		List<String> list = rf.readFileByLines("input.txt");
//		rf.dealWithData(list);
//		int method = rf.getMethod();
//		if(method==0) {
//			int babynumber = rf.getBaby();
//			int[][] grid = rf.getGrid();
//	    	DfsTest tb = new DfsTest(rf.getRow());
//	    	boolean status = tb.enter(babynumber, grid);
//	    	WriteFile wf = new WriteFile();
//	    	wf.WriteDataToFile(status, list,  tb.getGrid());
//		}else if(method==1) {
//			List<Integer>[] tree = rf.getTree();
//			
//			BfsTest bt = new BfsTest();
//			boolean status = bt.newBfs(tree, rf.getRow(), rf.getBaby());
//			System.out.println(status);
//	    	WriteFile wf = new WriteFile();
//	    	wf.WriteListDataToFile(status, rf.getRow(),list,  bt.bfsResult);
//		}else if(method==2) {
//			List<Integer>[] tree = rf.getTree();
//			int row = rf.getRow();
//			int baby = rf.getBaby();
//	            System.out.println("N = " + row);
//	            long sum = 0;
//	            int tollerence = 0;
//	            SimulatedAnnealing nq = new SimulatedAnnealing(tree,row,baby,tollerence,5);
//	            boolean status = nq.solve();
//	            WriteFile wf = new WriteFile();
//	            wf.WriteSADataToFile(status, rf.getRow(),list,  nq.saRes);
//		}
//	}
//	
//	public void runDfs() {
//		
//	}
//	
//	public void runBfs() {
//		
//	}
//	
//	public void runSa() {
//		
//	}

}
