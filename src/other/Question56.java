package other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Question56 {
    public static void main(String[] args) {
        int [][] intervals = {{1,4}, {4,5}};
        int [][] result = merge(intervals);
        for (int i = 0; i < result.length; i++) {
            int [] curr = result[i];
            System.out.println(Arrays.toString(curr));
        }
    }

    /**
     * 给出一个区间的集合，请合并所有重叠的区间。
     *
     * 思路：先根据区间的起点进行排序（解题关键），排好序后就遍历各区间进行合并，
     * 当要将某区间合并进去的时候，只需考虑上一个合并好的区间即可。
     *
     * @param intervals
     * @return
     */
    private static int[][] merge(int[][] intervals) {
        //边界判断
        if (intervals.length <= 1) {
            return intervals;
        }

        //先按起点位置进行排序
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        //利用list存储合并好的区间
        List<int[]> result = new ArrayList<>();
        //初始时将第一个区间放入list中
        result.add(intervals[0]);
        //记录上一合并好的区间在list中的位置
        int last = 0;
        //遍历并合并后面各区间
        for (int i = 1; i < intervals.length; i++) {
            //上一合并好的区间的起点和终点
            int lastStart = result.get(last)[0];
            int lastEnd = result.get(last)[1];
            //当前要合并的区间的起点和终点
            int start = intervals[i][0];
            int end = intervals[i][1];
            //如果左边重合
            if (lastStart == start) {
                if (end > lastEnd) {
                    result.set(last, new int[] {start, end});
                }
            } else {    //如果左边不重合
                if (start > lastEnd) {
                    result.add(new int[] {start, end});
                    last++;
                } else {
                    if (end > lastEnd) {
                        result.set(last, new int[] {lastStart, end});
                    }
                }
            }
        }

        return result.toArray(new int[0][]);
    }

}
