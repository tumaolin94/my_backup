package practise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Royalname {
	Map<Character, Integer> map = new HashMap<Character, Integer>();
    public Royalname() {
    	map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
    }
    class Ename {
    	String name;
    	String roman;
    	int number;
    	
    	public Ename(String name) {
    		String first = name.split(" ")[0];
    		String second = name.split(" ")[1];
    		this.name = first;
    		if(second.length() == 0|| second == null) this.number = 0;
    		else this.number = changeTonumber(second);
    		this.roman = second;
    	}

    }
    public void solve(String[] names) {
    	List<Ename> list = new ArrayList<>();
    	for(String name:names) {

    		list.add(new Ename(name));
    	}
    	Collections.sort(list, new Comparator<Ename>() {
    		
    		@Override
    	    public int compare(Ename a, Ename b)
    	    {
    	        if(!a.name.equals(b.name)) {
    	        	return a.name.compareTo(b.name);
    	        }else {
    	        	return a.number - b.number;
    	        }
    	    }
    	});
    	for (Ename temp:list) {
    		System.out.println(temp.name+" "+temp.roman);
    	}
    }
	public int changeTonumber(String s) {
		int res = map.get(s.charAt(s.length()-1));
	    for(int i=s.length()-2;i>=0;i--){
                if(map.get(s.charAt(i))<map.get(s.charAt(i+1))){
	            res = res - map.get(s.charAt(i));
	        }else{
	            res = res + map.get(s.charAt(i));
	        }
	    }
	    return res;
	}
	public static void main(String[] args) {
		Royalname rn = new Royalname();
//		String[] names = new String[3];
//		names[0] = "Philippe III";
//		names[1] = "Philippe II";
//		names[2] = "Ahilip I";
		String[] names = {"Albert II","Polo IV","Alexander V","Elizabeth XXV",
				  "Albert XL","Polo XLVI","William IX","Edward XXXIX",
				  "Elizabeth XIX"};
		rn.solve(names);
	}
}
