
public class Hacking {
	public static void main(String args[]) {
		String ori = "Otjfvknou kskgnl, K mbxg iurtsvcnb ksgq hoz atv. Vje xcxtyqrl vt ujg smewfv vrmcxvtg rwqr ju vhm ytsf elwepuqyez. -Atvt hrqgse, Cnikg";
		System.out.println(ori.substring(ori.length()-18, ori.length()));
		String bijiao = ori.substring(ori.length()-18, ori.length());
		String old = "Your friend, Alice";
		int[] key = new int[old.length()];
		int index=0;
		for(int i=0;i<key.length;i++) {
			if(old.charAt(i)==' '||old.charAt(i)==',')continue;
			key[index]=(bijiao.charAt(i)+26-old.charAt(i))%26;
			System.out.print(" "+key[index]);
			index++;
		}
		StringBuilder sb = new StringBuilder(ori);
		StringBuilder res = new StringBuilder();
		sb=sb.reverse();
		System.out.println();
		System.out.println(sb);
		int[] kkey = {2,8,0,2,2,1,5};
		index=0;
		for(int i=0;i<sb.length();i++) {
			if(sb.charAt(i)>='a'&&sb.charAt(i)<='z') {
				System.out.print((char)(sb.charAt(i)-kkey[index]));
				res.append((char)('a'+((sb.charAt(i)-'a')+26-kkey[index])%26));
				index=(index+1)%kkey.length;
			}else if(sb.charAt(i)>='A'&&sb.charAt(i)<='Z'){
				System.out.print((char)(sb.charAt(i)-kkey[index]));
				res.append((char)('A'+((sb.charAt(i)-'A')+26-kkey[index])%26));
				index=(index+1)%kkey.length;
			}else {
			
				res.append(sb.charAt(i));
			}
		}
		System.out.println();
		System.out.println(res.reverse());
	}
}
