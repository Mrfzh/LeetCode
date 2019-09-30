package math;

import java.util.Arrays;

public class Question492 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Question492().constructRectangle(4)));
    }

    /**
     * 作为一位web开发者， 懂得怎样去规划一个页面的尺寸是很重要的。
     * 现给定一个具体的矩形页面面积，你的任务是设计一个长度为 L 和宽度为 W 且满足以下要求的矩形的页面。
     *
     * 要求：
     * 1. 你设计的矩形页面必须等于给定的目标面积。
     * 2. 宽度 W 不应大于长度 L，换言之，要求 L >= W 。
     * 3. 长度 L 和宽度 W 之间的差距应当尽可能小。
     *
     * 你需要按顺序输出你设计的页面的长度 L 和宽度 W。
     *
     * 说明:
     * 1. 给定的面积不大于 10,000,000 且为正整数。
     * 2. 你设计的页面的长度和宽度必须都是正整数。
     *
     * @param area
     * @return
     */
    public int[] constructRectangle(int area) {
        int[] res = new int[2];
        // 从开放数开始取
        int start = (int) Math.sqrt(area);
        // 从后往前遍历会比从前往后遍历快很多
        // 可能是因为在取模运算中，摸越小越快
        for (int i = start; i > 0; i--) {
            if (area % i == 0) {
                int ano = area / i;
                res[0] = Math.max(i, ano);
                res[1] = Math.min(i, ano);
                return res;
            }
        }

        return res;
    }
}
