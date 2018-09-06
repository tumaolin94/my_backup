import java.util.*;

public class Jingchi1{

    static int max = 0;
	public static void main(String[] args){
    	Scanner in = new Scanner(System.in);
		int n = in.nextInt();	
        int a = in.nextInt();
        Map<Integer, List<Integer>> city = new HashMap<>();
        Map<Integer, Integer> people = new HashMap<>();
        for(int i = 1; i <= n; i++) {
        	List<Integer> list = new LinkedList<>();
        	list.add(i);
        	city.put(i, list);
        	people.put(i, i);
        }
        for(int i = 0; i < a; i++){
        	String indicator = in.next();
            if(indicator.equals("T")) {
            	int from = in.nextInt();
            	int to = in.nextInt();
//            	if(city.containsKey(from) && city.get(from).size() != 0) {
            		List<Integer> peoples = city.get(from);
            		List<Integer> too = city.get(to);
            		city.put(from, new LinkedList<>());
            		for(int p: peoples) {
            			people.put(p, to);
            			too.add(p);
            		}
            		city.put(to, too);
//            	}else{
//            		continue;
//            	}
            }else {
            	// Q
            	int search = in.nextInt();
            	int c = people.get(search);
            	System.out.println(c+" "+city.get(c).size());
            }
        }
    }
}
    
