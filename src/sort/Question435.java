package sort;

import java.util.Arrays;

public class Question435 {
    public static void main(String[] args) {
        int[][] intervals = {{1,2},{1,2},{1,2}};
        System.out.println(new Question435().eraseOverlapIntervals_better(intervals));
    }

    /**
     * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
     *
     * 注意:
     * 1. 可以认为区间的终点总是大于它的起点。
     * 2. 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
     *
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }

        // 先排序：先按起点升序排，起点相同按终点升序排
        Arrays.sort(intervals, (o1, o2) -> o1[0] == o2[0]? o1[1] - o2[1] : o1[0] - o2[0]);

        // 表示第 i 组区间是否被去掉
        boolean[] isDelete = new boolean[intervals.length];

        int count = 0;  // 移除数量
        // 从后往前遍历
        for (int i = intervals.length-1; i >= 0; i--) {
            // 当前区间压到后面的区间，就移除
            int currEnd = intervals[i][1];
            for (int j = i+1; j < intervals.length; j++) {
                int start = intervals[j][0];
                if (start >= currEnd) {
                    break;
                } else if (!isDelete[j]) {
                    // 移除当前区间
                    count++;
                    isDelete[i] = true;
                    break;
                }
            }
        }

        return count;
    }

    /**
     * 优化：关键是在排序的基础上，从后往前遍历，记录上一遍历区间的起点
     */
    public int eraseOverlapIntervals_better(int[][] intervals) {
        if (intervals.length <= 1) {
            return 0;
        }

        // 先排序：先按起点升序排，起点相同按终点升序排
        Arrays.sort(intervals, (o1, o2) -> o1[0] == o2[0]? o1[1] - o2[1] : o1[0] - o2[0]);

        int count = 0;  // 移除数量
        int lastStart = intervals[intervals.length-1][0];   // 记录上一遍历区间的起点
        // 从后往前遍历
        for (int i = intervals.length-2; i >= 0; i--) {
            // 当前区间压到后面的区间，就移除
            int currEnd = intervals[i][1];
            if (currEnd > lastStart) {
                // 移除当前区间
                count++;
            } else {
                // 不移除当前区间，更新 lastStart
                lastStart = intervals[i][0];
            }
        }

        return count;
    }
}
