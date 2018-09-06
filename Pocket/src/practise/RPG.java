package practise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class Item{
	String name;
	int value;
	int maximum_stack_size;
	public Item(String n, int v, int m) {
		name = n;
		value = v;
		maximum_stack_size = m;
	}
}
class Items implements Comparable<Items>{
	String name;
	int value;
	int size;
	public Items(String n, int v, int m) {
		name = n;
		value = v;
		size = m;
	}
//	public int compare(Object o1, Object o2) {
//		Items t1 = (Items) o1;
//		Items t2 = (Items) o2;
//        return 
//    }
	@Override
	public int compareTo(Items t2) {
		// TODO Auto-generated method stub
		return this.size != t2.size? t2.size - this.size: t2.value - this.value; 
	}
}
public class RPG {
	public static int getMax(Map<Item, Integer> item_infos, int n) {
		
		List<Items> list = new ArrayList<>();
		for(Item i: item_infos.keySet()) {
			int num = item_infos.get(i);
			int maxSize = i.maximum_stack_size;
			int value = i.value;
			String type = i.name;
			while(num>maxSize) {
				list.add(new Items(type, value*maxSize, maxSize));
				num -= maxSize;
			}
			list.add(new Items(type, value*num, num));
		}
		int sum = 0;
		for(Items i: list) {
			System.out.println("123  " +i.name+"  "+i.value);
		}
//		PriorityQueue<Items> pq = new PriorityQueue<Items>(list) ;
		PriorityQueue<Items> pq = new PriorityQueue<Items>(list) ;
//		while(!pq.isEmpty()) {
//			System.out.println(pq.poll().value);
//		}
		
		for(int i=0;i<n;i++) {
			Items tmp = pq.poll();
			System.out.println(tmp.name+" "+tmp.value);
			sum += tmp.value;
		}
		return sum;
	}
	public static void main(String[] args) {
		String[] strs = new String[] {"diamond", "ruby", "armor", "diamond", "diamond", "ruby", "diamond", "diamond", "diamond", "diamond", "diamond","armor"};
		Item[] items = new Item[3];
		items[0] = new Item("diamond",10,5);
		items[1] = new Item("ruby",5,5);
		items[2] = new Item("armor",25,1);
		Map<Item, Integer> item_infos = new HashMap<>();
		item_infos.put(items[0],8);
		item_infos.put(items[1],2);
		item_infos.put(items[2],2);
		System.out.println(getMax(item_infos,3));
	}
}
