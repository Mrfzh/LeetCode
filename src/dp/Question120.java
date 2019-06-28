package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question120 {
    public static void main(String[] args) {
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3, 4));
        triangle.add(Arrays.asList(6, 5, 1));
        triangle.add(Arrays.asList(4, 1, 1, 3));
        System.out.println(new Question120().minimumTotal(triangle));
    }

    /**
     * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
     *
     * 动态规划
     *
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        if (n == 0) {
            return 0;
        }

        int[] dp = new int[n];    //dp[a] 表示当前到第a个数的最短路径和
        dp[0] = triangle.get(0).get(0);

        int temp;       //暂存上一个数
        for (int i = 1; i < n; i++) {
            temp = dp[0];
            dp[0] = dp[0] + triangle.get(i).get(0);
            for (int j = 1; j < i; j++) {
                int min = Math.min(temp, dp[j]);
                temp = dp[j];
                dp[j] = min + triangle.get(i).get(j);
            }
            dp[i] = temp + triangle.get(i).get(i);
        }

        //找到最小值
        int min = dp[0];
        for (int i = 1; i < n; i++) {
            if (min > dp[i]) {
                min = dp[i];
            }
        }

        return min;
    }
}
