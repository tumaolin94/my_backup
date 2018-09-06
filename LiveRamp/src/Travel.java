import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Travel {

	public static int lengthOfLongestSubstringKDistinct(int[] a) {
		if(a.length==0) return 0;
		Set<Integer> set = new HashSet<>();
		for(int as: a) {
			set.add(as);
		}
		int k = set.size();
        int l = 0;
        int num = 0;
        int min =a.length;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<a.length;i++){
        	
            if(!map.containsKey(a[i])||map.get(a[i])==0) {
            	num++;
            }
            map.put(a[i], map.getOrDefault(a[i], 0)+1);
            if(num==k){
            	System.out.println(l+" "+map.get(a[l]));
                while(map.get(a[l])>1) {
                	map.put(a[l], map.get(a[l])-1);
                	l++;
                }
                System.out.println(l+" "+i);
                min = Math.min(min,i - l +1);
            }
            
        }
        return min;
    }
	public static void main(String[] args) {
		System.out.println(lengthOfLongestSubstringKDistinct(new int[] {1,2,4,4,4,4,4,3,3,3}));
	}
}
