package division;

import java.util.Arrays;
import java.util.HashMap;

public class Question436 {
    public static void main(String[] args) {
        int[][] intervals = {{1,4}, {2,3}, {3,4}};
        System.out.println(Arrays.toString(new Question436().findRightInterval(intervals)));
    }

    /**
     * 给定一组区间，对于每一个区间 i，检查是否存在一个区间 j，
     * 它的起始点大于或等于区间 i 的终点，这可以称为 j 在 i 的“右侧”。
     *
     * 对于任何区间，你需要存储满足条件的区间j 的最小索引，
     * 这意味着区间 j 有最小的起始点可以使其成为“右侧”区间。如果区间 j 不存在，则将区间 i 存储为 -1。
     * 最后，你需要输出一个值为存储的区间值的数组。
     *
     * 注意:
     * 1. 你可以假设区间的终点总是大于它的起始点。
     * 2. 你可以假定这些区间都不具有相同的起始点。
     *
     * 排序 + 二分法
     *
     * @param intervals
     * @return
     */
    public int[] findRightInterval(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0];
        }

        // 存储全部区间的起点
        int[] startArray = new int[intervals.length];
        // key 为起点，value 为该区间对应索引
        HashMap<Integer, Integer> startMap = new HashMap<>();
        for (int i = 0; i < intervals.length; i++) {
            startArray[i] = intervals[i][0];
            startMap.put(intervals[i][0], i);
        }

        // 对起点进行排序
        Arrays.sort(startArray);
        // 起点的最大值
        int maxStart = startArray[intervals.length-1];

        int[] res = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            int currEnd = intervals[i][1];
            // 得到大于等于当前终点的最小起点的索引，没有则为 -1
            int index = -1;
            if (currEnd > maxStart) {
                res[i] = index;
                continue;
            }
            // 二分查找
            int left = 0;
            int right = startArray.length-1;
            boolean hasFound = false;
            while (left < right) {
                int mid = left + (right - left)/2;
                if (startArray[mid] == currEnd) {
                    hasFound = true;
                    index = startMap.get(startArray[mid]);
                    break;
                } else if (startArray[mid] < currEnd) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            if (!hasFound) {
                index = startMap.get(startArray[left]);
            }
            res[i] = index;
        }

        return res;
    }
}
