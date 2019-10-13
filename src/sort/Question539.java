package sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Question539 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("11:01", "00:12", "00:08", "13:11");
        new Question539().findMinDifference(list);
        System.out.println(list);
    }

    /**
     * 给定一个 24 小时制（小时:分钟）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
     *
     * 备注:
     * 列表中时间数在 2~20000 之间。
     * 每个时间取值在 00:00~23:59 之间。
     *
     * 排序
     *
     * @param timePoints
     * @return
     */
    public int findMinDifference(List<String> timePoints) {
        // 先按照 00:00 ~ 23:59 的顺序给时间排序
        timePoints.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // 小时
                int h1 = Integer.parseInt(o1.substring(0, 2));
                int h2 = Integer.parseInt(o2.substring(0, 2));
                // 分钟
                int m1 = Integer.parseInt(o1.substring(3));
                int m2 = Integer.parseInt(o2.substring(3));

                return h1 != h2? h1 - h2 : m1 - m2;
            }
        });

        // 比较相邻的时间，找出最短间隔时间
        int min = helper(timePoints.get(0), timePoints.get(timePoints.size()-1));
        for (int i = 0; i < timePoints.size() - 1; i++) {
            min = Math.min(min, helper(timePoints.get(i), timePoints.get(i+1)));
        }

        return min;
    }

    /**
     * 找出 s1 和 s2 的时间差，要求 s2 >= s1
     */
    private int helper(String s1, String s2) {
        // 小时
        int h1 = Integer.parseInt(s1.substring(0, 2));
        int h2 = Integer.parseInt(s2.substring(0, 2));
        // 分钟
        int m1 = Integer.parseInt(s1.substring(3));
        int m2 = Integer.parseInt(s2.substring(3));
        if (m2 < m1) {
            m2 += 60;
            h2--;
        }

        int temp = (h2 - h1) * 60 + m2 - m1;
        // 有可能是 00:00, 18:00 这种情况，这时的时间差（要进可能小）就要变成 24*60 - temp 了
        return temp > 720? 1440 - temp : temp;
    }
}
