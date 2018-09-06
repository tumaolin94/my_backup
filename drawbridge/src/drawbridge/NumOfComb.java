package drawbridge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NumOfComb {
//	Arithmetic progression
	public static int numOfComb(int num) {
		if (num <= 0) {
			return 0;
		}
		int count = 0;
		for (int k = 2; k * (k - 1) / 2 < num; k++) {
			System.out.println(k+" "+(k * (k - 1) / 2)+" "+((num - k * (k - 1) / 2)));
			if ((num - k * (k - 1) / 2) % k == 0) {
				count++;
			}
		}
		return count;
	}
	public static int intersection(int[][] intervals) {
		int minEnd = Integer.MAX_VALUE;
		int maxStart = Integer.MIN_VALUE;
		for(int[] interval: intervals) {
			minEnd = Math.min(minEnd, interval[1]);
			maxStart = Math.max(maxStart, interval[0]);
		}
		return maxStart - minEnd;
	}
    public static int intersectionSizeTwo(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });

        List<int[]> list = new ArrayList<>();
        for (int[] interval : intervals) {
        	System.out.println(interval[0]+" "+interval[1]);
           while (!list.isEmpty() && interval[1] <= list.get(list.size() - 1)[1]) {
                list.remove(list.size() - 1);
            }
            list.add(interval);
        }
        for(int[] i: list) {
        	System.out.println(i[0]+" "+i[1]);
        }
        int count = 0;
        int end1 = -1;
        int end2 = -1;

        for (int i = 0; i < list.size(); i++) {
            int start = list.get(i)[0];
            if (start <= end1) {
                continue;
            } else if (start > end2) {
                end2 = list.get(i)[1];
                end1 = end2 - 1;
                count += 2;
            } else {
                end1 = end2;
                end2 = list.get(i)[1];
                count += 1;
            }
        }

        return count;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println(numOfComb(5));
		System.out.println(intersectionSizeTwo(new int[][] {{1,3},{1,4},{2,5}}));
	}

}
