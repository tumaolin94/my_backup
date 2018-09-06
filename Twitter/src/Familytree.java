import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
class Node{
	String name="";
	String parent_name="";
	Date dob = null;
	Date dod = null;
	long acu = 0;
	long totalage = 0;
	int totalMember = 0;
	int generation = 0;
	Node parent = null;
	List<Node> children = new ArrayList<>();
	int level = 0;
	public Node(String name, String parent_name, String dob, String dod) {
		this.name = name;
		this.parent_name = parent_name;
		try {
			this.dob = new SimpleDateFormat("yyyy-mm-dd").parse(dob);
			this.dod = new SimpleDateFormat("yyyy-mm-dd").parse(dod);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
public class Familytree {
	
//	public int caltime(Date d1, Date d2) {
//		int year = Math.abs(d1.getYear()-d2.getYear());
////		int day = Math.abs(a)
//	}
	public static void main(String[] args) {
//		List<Node> trees = new ArrayList<>();
		List<String> list = new ArrayList<>();
		Scanner input = new Scanner(System.in);
		while(input.hasNextLine()) {
			String temp = input.nextLine();
			if(temp==null||temp.length()==0) break;
//			System.out.println(temp);
			list.add(temp);

		}
//		readFileByLines("input001.txt");
		List<Node> trees = dealWithData(list);
		List<Node> an = new ArrayList<>();
		for(int i=0;i<trees.size();i++) {
			if(trees.get(i).parent_name.length()==0) {
//				System.out.println(trees.get(i).name);
				an.add(trees.get(i));
			}
			for(int j=0;j<trees.size();j++) {
				if(i==j) continue;
				if(trees.get(j).parent_name.equals(trees.get(i).name)) {
					trees.get(i).children.add(trees.get(j));
					trees.get(j).parent = trees.get(i);
//					System.out.println(trees.get(i).name+"'s son is "+trees.get(j).name);
				}
			}

		}
		int maxMem=0;
		int maxAge=0;		
		int maxLvl=0;
		for(Node root:an){
			mem=0;
			total_age=0;
			maxGen=0;
			bfs_level(root);
			maxMem=Math.max(maxMem,mem);
			maxAge=Math.max(maxAge,total_age);
			maxLvl=Math.max(maxLvl,maxGen);
		}
		System.out.println(maxMem+" "+maxAge+"" +maxLvl);
		
		long max = Integer.MIN_VALUE;
		Node resNode = null;
		for(Node root:an) {
			Node temp = bfs(root);
			if(max<temp.acu) {
				max = temp.acu;
				resNode = temp;
			}
		}
//		System.out.println(resNode.name);
		String output = "";
		while(resNode!=null) {
			if(resNode.parent!=null)
				output= "->"+resNode.name+output;
			else output=resNode.name+output;
			resNode = resNode.parent;
		}
		System.out.println(output);
		System.out.println("genration "+geRes.name+" "+geRes.generation);
		System.out.println("age "+ageRes.name+" "+geRes.totalage);
		
	}
	
	static Node geRes = null;
	static Node ageRes = null;
	static int mem;
	static int total_age;
	static int maxGen;
	public static void bfs_level(Node root){
		ArrayList<Node> Q=new ArrayList<Node>();
		HashSet<Node> visited=new HashSet<Node>();
		
		System.out.println("Enter");
		
		Q.add(root);
		visited.add(root);
		while(!Q.isEmpty()){
			Node cur=Q.get(0);
			Q.remove(0);
			
			mem++;//member
			SimpleDateFormat df = new SimpleDateFormat("yyyy");
			String year1 = df.format(cur.dod);
			String year2 = df.format(cur.dob);
//			total_age+=Math.abs(cur.dod.getYear()-cur.dob.getYear());
			total_age+=Math.abs(Integer.valueOf(year1)-Integer.valueOf(year2));
			
			for(Node child:cur.children){
				if(!visited.contains(child)){
					child.level++;
					maxGen=Math.max(child.level,maxGen);
					Q.add(child);
					visited.add(child);
				}
			}
		}
		
	}
	public static Node  bfs(Node root) {
		Queue<Node> queue = new LinkedList<>();
		long max = Integer.MIN_VALUE;
		root.acu = Math.abs(root.dod.getTime() - root.dob.getTime());
		root.generation = 1;
		root.totalage = Math.abs(root.dod.getYear() - root.dob.getYear());
		long maxGe = Integer.MIN_VALUE;
		long maxAge = Integer.MIN_VALUE;
		queue.offer(root);
		Node result = root;
		while(!queue.isEmpty()) {
			int size = queue.size();
			for(int i=0;i<size;i++) {
				Node temp = queue.poll();
				if(max<temp.acu) {
					max = temp.acu;
					result = temp;
				}
				if(maxGe<temp.generation) {
					max = temp.generation;
					geRes = temp;
				}
				if(maxAge<temp.totalage) {
					maxAge = temp.totalage;
					ageRes = temp;
				}
				for(Node child:temp.children) {
					child.acu = temp.acu + Math.abs(child.dod.getTime() - temp.dod.getTime());
					child.totalage = temp.acu + Math.abs(child.dod.getYear() - temp.dod.getYear());
					child.generation = temp.generation+1;
					queue.offer(child);
				}
			}
		}
		return result;
	}
	
	static List<String> list = new ArrayList<>();
    public static List<String> readFileByLines(String fileName) {
    	list=new ArrayList<>();
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
//            System.out.println("ReadFileByLine：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
            	list.add(tempString);
//                System.out.println("line " + line + ": " + tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        
        return list;
    }
    
    
    public static List<Node> dealWithData(List<String> list) {
    	List<Node> res = new ArrayList<>();
    	for(String str:list) {
    		if(str.contains("name,parent_name,dob,dod")) continue;
    		String[] newNode = new String[4];
    		int index = 0;
    		for(String single:str.split(",")) {
    			newNode[index++] = single;
    		}
//    		System.out.println(newNode[0]+","+newNode[1]+","+newNode[2]+","+newNode[3]);
    		res.add(new Node(newNode[0],newNode[1],newNode[2],newNode[3]));
    	}
    	return res;
    }
}
