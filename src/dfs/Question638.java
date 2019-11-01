package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question638 {
    public static void main(String[] args) {
        List<Integer> price = Arrays.asList(2,5);
        List<Integer> sp1 = Arrays.asList(3,0,5);
        List<Integer> sp2 = Arrays.asList(1,2,10);
        List<List<Integer>> special = new ArrayList<>();
        special.add(sp1);
        special.add(sp2);
        List<Integer> needs = Arrays.asList(3,2);
        System.out.println(new Question638().shoppingOffers(price, special, needs));
    }

    /**
     * 在LeetCode商店中， 有许多在售的物品。然而，也有一些大礼包，每个大礼包以优惠的价格捆绑销售一组物品。
     *
     * 现给定每个物品的价格，每个大礼包包含物品的清单，以及待购物品清单。请输出确切完成待购清单的最低花费。
     *
     * 每个大礼包的由一个数组中的一组数据描述，最后一个数字代表大礼包的价格，
     * 其他数字分别表示内含的其他种类物品的数量。
     *
     * 任意大礼包可无限次购买。
     *
     * 说明:
     * 你不可以购买超出待购清单的物品，即使更便宜。
     *
     * dfs
     * PS：做了两个小时才通过，太菜了o(╥﹏╥)o
     *
     * @param price 商品价格
     * @param special 大礼包价格
     * @param needs 购物清单
     * @return
     */
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special,
                              List<Integer> needs) {
        return minPrice(price, needs, special, 0);
    }

    /**
     * 根据所需清单 need，判断是否可以购买 n 个大礼包 special
     * 只要任意一个 need[i] < n * special[i]，就不可以购买
     */
    private boolean isValid(List<Integer> special, List<Integer> need, int n) {
        for (int i = 0; i < need.size(); i++) {
            if (need.get(i) < n * special.get(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 求所需清单 need 是大礼包 special 的多少倍，若无结果则返回 -1
     * 返回 0 表示 need 和 special 相等
     */
    private int multi(List<Integer> special, List<Integer> need) {
        int res = -1;
        for (int i = 0; i < need.size(); i++) {
            if (special.get(i) > need.get(i)) {
                return -1;
            }
            if (special.get(i) == 0 && need.get(i) != 0) {
                return -1;
            }
            if (special.get(i) == 0 && need.get(i) == 0) {
                if (res == -1) {
                    res = 0;
                }
                continue;
            }
            if (res == -1) {
                res = need.get(i) / special.get(i);
                continue;
            }
            if (res * special.get(i) != need.get(i)) {
                res = -1;
                break;
            }
        }
        return res;
    }

    /**
     * 从 special[start] 开始购买大礼包，求满足购物清单 needs 的最低价钱
     */
    private int minPrice(List<Integer> price, List<Integer> needs,
                         List<List<Integer>> special, int start) {
        // 初始状态下，min 为各个商品单独买时的价格总和
        int min = 0;
        for (int i = 0; i < needs.size(); i++) {
            min += price.get(i) * needs.get(i);
        }
        // 当遍历都最后一个大礼包时，进行返回
        if (start == special.size() - 1) {
            int m = multi(special.get(start), needs);
            if (m == -1) {
                // 最多可购买 n 个大礼包
                int n = 1;
                for ( ; ; n++) {
                    if (!isValid(special.get(start), needs, n)) {
                        n--;
                        break;
                    }
                }
                int p = n * special.get(start).get(special.get(start).size() - 1);
                // 剩下的按单价买
                for (int i = 0; i < needs.size(); i++) {
                    p += price.get(i) * (needs.get(i) - n * special.get(start).get(i));
                }
                return Math.min(min, p);
            } else {
                return Math.min(min, m * special.get(start).get(special.get(start).size() - 1));
            }
        }

        // special[start] 购买 0, 1, ... 份
        for (int i = 0; ; i++) {
            if (!isValid(special.get(start), needs, i)) {
                break;
            }
            // 购买 i 个 special[start] 所需价钱
            int currPrice = special.get(start).get(special.get(start).size() - 1) * i;
            List<Integer> newNeeds = new ArrayList<>();
            // 判断 newNeeds 是否全是 0
            boolean isAllZero = true;
            for (int j = 0; j < needs.size(); j++) {
                int curr = needs.get(j) - special.get(start).get(j) * i;
                if (curr != 0) {
                    isAllZero = false;
                }
                newNeeds.add(curr);
            }
            if (!isAllZero) {
                currPrice += minPrice(price, newNeeds, special, start + 1);
            }
            min = Math.min(min, currPrice);
        }

        return min;
    }
}
