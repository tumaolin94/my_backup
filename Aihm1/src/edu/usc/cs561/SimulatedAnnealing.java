package edu.usc.cs561;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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

    public boolean solve() {
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
            	int largeIndex = currentState.largeIndex;
            	System.out.println(largeIndex);
                nextState = currentState.getNextState3();
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

    public boolean solve2() {
    	int count=0;
    	long initialTime = System.currentTimeMillis();
        while (currentState.getCost()!=0) {
        	
        	count++;
//            double temperature;
            double delta;
            double probability;
            double rand;
            tempreture =5;
            double stabilizer = 10;
            double stabilizingFactor = 1.03;
            double temp;
            for (temp = this.tempreture; (temp > 0) && (currentState.getCost() != 0); temp-=0.05) {
                for(int i=0;i<stabilizer;i++) {
//                	System.out.println(temp+"   "+i+"/"+stabilizer);
//                    System.out.println(System.currentTimeMillis()-initialTime+"ms");
                	int[] lastSingleCost = currentState.singleCost;
                    nextState = currentState.getNextState(lastSingleCost);
                    delta = currentState.getCost() - nextState.getCost();
                    probability = Math.exp(delta / temp);
                    rand = Math.random();
                    
                    char[] tempInt = changeNodeToIntArray(nextState.q);
                    Arrays.sort(tempInt);
//                    System.out.println(String.valueOf(tempInt));
                    if(System.currentTimeMillis() - initialTime>=290000) {
                    	System.out.println("Time Over");
                    	return false;
                    }
                    if(!set.add(String.valueOf(tempInt))) {
//                    	System.out.println("有重复");
                    	i--;
                    	continue;
                    }
                    
                    if (delta > 0) {
                        currentState = nextState;
                    } else if (rand <= probability) {
                        currentState = nextState;
                    }
                }
                stabilizer*=stabilizingFactor;
            }
        }
        
        if(currentState.getCost()==0) {
        saRes = currentState.getGrid();
        System.out.println("运行了"+count+"循环");
        return true;
        }
        return false;
    }
    
    public boolean solve3() {
    	if(placeFull==false) return false;
    	long initialTime = System.currentTimeMillis();
        while (currentCost!=0) {
            
            double delta;
            double probability;
            double rand;
            tempreture =50000;
            System.out.println(tempreture);
            System.out.println(System.currentTimeMillis()-initialTime+"ms");
            double temp;
            for (temp = tempreture; (temp > 0) && (currentCost != 0); temp*=0.99) {
            	System.out.println(temp);
                for(int i=0;i<10000;i++) {

                	int[] lastSingleCost = currentState.singleCost;
                    nextState = currentState.getNextState(lastSingleCost);
                    delta = currentState.getCost() - nextState.getCost();
                    probability = Math.exp(delta / temp);
                    rand = Math.random();
                    
                    char[] tempInt = changeNodeToIntArray(nextState.q);
                    Arrays.sort(tempInt);
//                    System.out.println(String.valueOf(tempInt));
                    if(System.currentTimeMillis() - initialTime>=290000) {
                    	System.out.println("Time Over");
                    	return false;
                    }
                    if(!set.add(String.valueOf(tempInt))) {
//                    	System.out.println("有重复");
                    	i--;
                    	continue;
                    }
                    
                    if (delta > 0) {
                        currentState = nextState;
                    } else if (rand <= probability) {
                        currentState = nextState;
                    }
                }
            }
        }
        
        if(currentCost==0) {
        saRes = currentState.getGrid();
        return true;
        }
        return false;
    }
    
    public boolean solve4(int baby,int row) {
    	if(placeFull==false) return false;
    	long initialTime = System.currentTimeMillis();
    	int counts = row*baby*100;
            while(currentState.getCost()!=0) {
            double delta;
            double probability;
            double rand;
            tempreture =baby*10;
            System.out.println(tempreture);
            System.out.println(System.currentTimeMillis()-initialTime+"ms");
            double temp;
            for (int i=0;i<counts;i++) {
                nextState = currentState.getNextState3();
                int nextCost = nextState.getCost();
//                System.out.println("temperature="+temp+" 当前cost"+currentCost+"  下一步cost"+nextCost);
                delta =  nextCost -currentCost;
                probability = Math.exp(-delta / tempreture);
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
                	i++;
                	continue;
                }
                if (delta < 0) {
                    currentState = nextState;
                    currentCost = nextCost;
                } else if (rand <= probability) {
                    currentState = nextState;
                    currentCost = nextCost;
                }
                tempreture/=2;
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
