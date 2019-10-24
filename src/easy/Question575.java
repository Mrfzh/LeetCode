package easy;

import java.util.Arrays;

public class Question575 {
    public static void main(String[] args) {
        int[] candies = {1,1,2,3};
        System.out.println(new Question575().distributeCandies(candies));
    }

    /**
     * 给定一个偶数长度的数组，其中不同的数字代表着不同种类的糖果，每一个数字代表一个糖果。
     * 你需要把这些糖果平均分给一个弟弟和一个妹妹。返回妹妹可以获得的最大糖果的种类数。
     *
     * 注意:
     * 数组的长度为[2, 10,000]，并且确定为偶数。
     * 数组中数字的大小在范围[-100,000, 100,000]内。
     *
     * @param candies
     * @return
     */
    public int distributeCandies(int[] candies) {
        // 先排序
        Arrays.sort(candies);
        // 寻找其中值不同的数的个数
        int last = candies[0];
        int count = 1;
        for (int i = 1; i < candies.length; i++) {
            if (candies[i] != last) {
                count++;
            }
            last = candies[i];
        }

        return Math.min(candies.length / 2, count);
    }
}
