package studyjava;

import java.util.*;


public class Solution1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        String dic = in.nextLine();
        String[] dics = dic.split(" ");
        String rev = new StringBuffer(input).reverse().toString();
        System.out.println(solution(input, rev, dics));
	}
	public static int solution(String input, String rev, String[] wordList) {
        Set<String> dirt = new HashSet<>();
        for(String word: wordList) {
        	dirt.add(word);
        }
        Queue<String> queue = new LinkedList<>();
        int res = 0;
        Set<String> visited = new HashSet<>();
        queue.offer(input);
        
        while(!queue.isEmpty()){
            int size = queue.size();
            res++;
            for(int i=0;i<size;i++){
                String str = queue.poll();
                if(str.equals(rev)){
                    return res;
                }
                char[] temp = str.toCharArray();
                int length = str.length();
                for(int j=0;j<length;j++){
                    char ch = temp[j];
                    for(char c='a';c<='z';c++){
                        if(ch==c) continue;
                        temp[j]=c;
                        String newword = new String(temp);
                        if(dirt.contains(newword)&&visited.add(newword)){
                            queue.offer(newword);
                        }
                    }
                    temp[j]=ch;
                }
                
            }
        }
        
        return 0;
    }
}
