package double_pointer;

import java.util.Arrays;

public class Question475 {
    public static void main(String[] args) {
        int[] houses = {1,2,3,4,5,1};
        int[] heaters = {1,2,3,4,5,1};
        System.out.println(new Question475().findRadius(houses, heaters));
    }

    /**
     * 冬季已经来临。 你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。
     *
     * 现在，给出位于一条水平线上的房屋和供暖器的位置，找到可以覆盖所有房屋的最小加热半径。
     *
     * 所以，你的输入将会是房屋和供暖器的位置。你将输出供暖器的最小加热半径。
     *
     * 说明:
     * 1. 给出的房屋和供暖器的数目是非负数且不会超过 25000。
     * 2. 给出的房屋和供暖器的位置均是非负数且不会超过10^9。
     * 3. 只要房屋位于供暖器的半径内(包括在边缘上)，它就可以得到供暖。
     * 4. 所有供暖器都遵循你的半径标准，加热的半径也一样。
     *
     * 双指针
     *
     * @param houses
     * @param heaters
     * @return
     */
    public int findRadius(int[] houses, int[] heaters) {
        if (houses.length == 0 || heaters.length == 0) {
            return 0;
        }
        // 先排序
        Arrays.sort(houses);
        Arrays.sort(heaters);

        int houseP = 0;     // 指向当前房屋
        int heaterP = 0;    // 指向当前供暖器
        int res = 0;    // 输出结果
        while (houseP < houses.length) {
            // 找出离当前房屋最近的供暖器
            int min = Math.abs(houses[houseP] - heaters[heaterP]);
            for (int i = heaterP+1; i < heaters.length; i++) {
                int dis = Math.abs(houses[houseP] - heaters[i]);
                if (dis > min) {    // 这里必须是 >，不能是 >=
                    break;
                } else {
                    min = dis;
                    heaterP = i;
                }
            }
            res = Math.max(res, min);
            houseP++;
        }

        return res;
    }
}
