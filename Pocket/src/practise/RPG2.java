package practise;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//class Item{
//	String name;
//	int value;
//	int maximum_stack_size;
//	public Item(String n, int v, int m) {
//		name = n;
//		value = v;
//		maximum_stack_size = m;
//	}
//}
public class RPG2 {
	public static int getMax(String[] items,Item[] item_infos, int n) {
		Map<String, Integer> map = new HashMap<>();
		Map<String, Item> itemInfo = new HashMap<>();
		for(String item: items) {
			map.put(item, map.getOrDefault(item, 0)+1);
		}
		for(Item i: item_infos) {
			itemInfo.put(i.name, i);
		}
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>((i1,i2)->{return i2 - i1;}) ;
//		PriorityQueue<Integer> pq = new PriorityQueue<Integer>((i1,i2)->{return i2 - i1;}) ;
		for(String name: map.keySet()) {
			int num = map.get(name);
			int maxSize = itemInfo.get(name).maximum_stack_size;
			int value = itemInfo.get(name).value;
			while(num>maxSize) {
				pq.offer(value * maxSize);
				num -=maxSize;
			}
			pq.offer(value * num);
		}
		int sum = 0;
		for(int i=0;i<n;i++) {
			int tmp = pq.poll();
			System.out.println(tmp);
			sum += tmp;
		}
		return sum;
	}
	public static void main(String[] args) {
		String[] strs = new String[] {"diamond", "ruby", "armor", "diamond", "diamond", "ruby", "diamond", "diamond", "diamond", "diamond", "diamond","armor"};
		Item[] items = new Item[3];
		items[0] = new Item("diamond",10,5);
		items[1] = new Item("ruby",5,5);
		items[2] = new Item("armor",25,1);
		
		System.out.println(getMax(strs,items,3));
	}
}