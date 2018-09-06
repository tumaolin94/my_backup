
import java.util.*;
public class S2 {
    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        String str = in.nextLine();
        System.out.println(sol("AAB"));
    }
    
    public static int sol(String str){
        List<Integer> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder(str);
        helper(sb, list);
        return list.size();
    }
    public static void helper(StringBuilder sb, List<Integer> list){
        if(sb.length() == 1 ) {
            list.add(1);
            return;
        }
        if(judge(sb.toString())){
            list.add(1);
        }
        for(int i = 0; i < sb.length();i++){
            char tmp = sb.charAt(i);
            sb.deleteCharAt(i);
            helper(sb, list);
            sb.insert(i, tmp);
        }
        
    }
    public static boolean judge(String str){
        char[] c = str.toCharArray();
        int i = 0;
        int j = c.length - 1;
        while(i < j){
            if(c[i++]!=c[j--]) return false;
        }
        return true;
    }
}
