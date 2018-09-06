package n.queens;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.usc.cs561.GridNode;


public class SimulatedAnnealing {
	List<GridNode[]> saList = new ArrayList<>();
    int saRow;
    SimulatedAnnealingState currentState, nextState;
    int tollerenceCost;
    List<Integer>[] saTree;
    double tempreture;
    public GridNode saRes[] ;
    int currentCost;
    boolean placeFull = true;
    Set<String> set = new HashSet<>();
    public SimulatedAnnealing(List<Integer>[] tree,int row, int babynumber,int tollerence, double tempreture) {
        this.saRow = row;
        this.tollerenceCost = tollerence;
        this.tempreture = tempreture;
        this.saTree = tree;
        currentState = new SimulatedAnnealingState(tree, row, babynumber);
        this.placeFull = currentState.placeFull;
        System.out.println(currentState.placeFull);
        if(placeFull==false) return;
        currentCost = currentState.getCost();

    }

    public boolean solve(int row, int baby) {
    	if(placeFull==false) return false;
    	long initialTime = System.currentTimeMillis();
        while (currentCost!=0) {
            
            double delta;
            double probability;
            double rand;
            tempreture =tempreture;
            System.out.println(tempreture);
            System.out.println(System.currentTimeMillis()-initialTime+"ms");
            double temp;
            int ccc=0;
            for (temp = tempreture; (currentCost != 0); temp*=0.99) {
            	 System.out.println(temp);
            	for(int i=0;i<baby&& (currentCost != 0);i++) {
                nextState = currentState.getNextState();
                int nextCost = nextState.getCost();
//                System.out.println("temperature="+temp+" 当前cost"+currentCost+"  下一步cost"+nextCost);
                delta =  nextCost -currentCost;
                probability = Math.exp(-delta / temp);
//                System.out.println(probability);
                rand = Math.random();
                
                if(System.currentTimeMillis() - initialTime>=290000) {
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
                if(currentCost==0) {
                    saRes = currentState.getGrid();
                    return true;
                    }
            }
            }
        }
        
        if(currentCost==0) {
        	System.out.println("set.size()"+set.size());
        saRes = currentState.getGrid();
        return true;
        }
        return false;
    }

    public boolean solve2() {
    	if(placeFull==false) return false;
    	long initialTime = System.currentTimeMillis();
        while (currentCost!=0) {
            
            double delta;
            double probability;
            double rand;
            tempreture =tempreture*1.05;
            System.out.println(tempreture);
            System.out.println(System.currentTimeMillis()-initialTime+"ms");
            double temp;
            for (temp = tempreture; (temp > 0) && (currentCost != 0); temp*=0.9995) {
                nextState = currentState.getNextState();
                int nextCost = nextState.getCost();
//                System.out.println("temperature="+temp+" 当前cost"+currentCost+"  下一步cost"+nextCost);
                delta =  nextCost -currentCost;
                probability = Math.exp(-delta / temp);
//                System.out.println(probability);
                rand = Math.random();
                
                if(System.currentTimeMillis() - initialTime>=290000) {
                	System.out.println("Time Over");
                	return false;
                }
                
                char[] tempInt = changeNodeToIntArray(nextState.q);
                Arrays.sort(tempInt);
                
                if(!set.add(String.valueOf(tempInt))) {
//                	System.out.println("发生了什么");
                	temp*=1/0.9995;
                	continue;
                }
                if (delta < 0) {
                    currentState = nextState;
                    currentCost = nextCost;
                } else if (rand <= probability) {
                    currentState = nextState;
                    currentCost = nextCost;
                }
            }
        }
        
        if(currentCost==0) {
        saRes = currentState.getGrid();
        return true;
        }
        return false;
    }
    
    public char[] changeNodeToIntArray(GridNode[] q) {
    	char[] res = new char[q.length];
    	for(int i=0;i<q.length;i++) {
    		res[i]=(char)(q[i].x*saRow+q[i].y);
    	}
    	return res;
    }
    

}
