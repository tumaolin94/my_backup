package solution.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


public class Binarysearch {
	public Binarysearch() {

	}

	public int binary(int[] nums, int target) {
		int start = 0;
		int end = nums.length - 1;
		while (start < end) {
			int mid = start + (end - start) / 2;
			if (nums[mid] == target)
				return mid;
			else if (target < nums[mid])
				end = mid - 1;
			else
				start = mid + 1;
		}

		return start;
	}

    public boolean isPossible(int[] nums) {
        Map<Integer,Integer> freq = new HashMap<>();
        Map<Integer,Integer> appendFreq = new HashMap<>();
        
        for(int i:nums) freq.put(i,freq.getOrDefault(i,0)+1);
        
        for(int i:nums){
            if(freq.getOrDefault(i,0)==0) continue;
            
            else if(appendFreq.getOrDefault(i,0)>0){
                appendFreq.put(i,appendFreq.getOrDefault(i,0)-1);
                appendFreq.put(i+1,appendFreq.getOrDefault(i+1,0)+1);
            }else if(freq.getOrDefault(i+1,0)>0&&freq.getOrDefault(i+2,0)>0){
                freq.put(i+1,freq.getOrDefault(i+1,0)-1);
                freq.put(i+2,freq.getOrDefault(i+2,0)-1);      
                appendFreq.put(i+3,appendFreq.getOrDefault(i+3,0)+1);
            }else return false;
            freq.put(i,freq.getOrDefault(i,0)-1);
        }
        
        return true;
    }
    static int x = 0;

    static boolean increment()
    {
       x++;
       return true;
    }
    @SuppressWarnings("finally")
	private static String getValue()
    {
       try
      {
          return "try";
       }
       finally
       {
          return "finally";
       }
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getValue());
//		Binarysearch bs = new Binarysearch();
//		int[] nums = new int[] { 1,1,2,2,3,3,4};
////		System.out.println(bs.isPossible(nums));
//		List<String> ss = new ArrayList<>();
//		ss.add("hot");
//		ss.add("dot");
//		ss.add("dog");
//		ss.add("lot");
//		ss.add("log");
//		ss.add("cog");
//		System.out.println(bs.ladderLength("hit", "cog", ss));

	}
	
	 public int ladderLength(String beginWord, String endWord, List<String> wordList) {
	        int count=1;
	        Queue<String> queue = new LinkedList<>();
	        List<String> visited = new ArrayList<>();
	        
	        queue.offer(beginWord);
	        queue.offer(null);
	        visited.add(beginWord);
	        while(!queue.isEmpty()){
	            String str = queue.poll();
	            if(str!=null){
	                System.out.println(str.length());
	                char[] temp = str.toCharArray();
	            for(int i=0;i<beginWord.length();i++){
	                for(char c='a';c<'z';c++){
	                    temp[i]=c;
	                    String newword = new String(temp);
	                    if(newword.equals(endWord)) return count+1;
	                    
	                    if(wordList.contains(newword)&&!visited.contains(newword)){
	                        queue.offer(newword);
	                        visited.add(newword);
	                    }
	                }               
	            }
	            }
	            else {
	                count++;
	                System.out.println(count);
	                if(!queue.isEmpty()) queue.add(null);
	            }
	        }
	        
	        
	        
	        
	        
	        return 0;
	    }
	
	public int newInteger(int n,int k) {
	   int ans =0;
	   int base =1;
	   while(n>0) {
		   ans += n%k*base;
		   n/=k;
		   base*=10;
	   }
	   return ans;
	}

}
