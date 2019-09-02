package dfs;

import java.util.ArrayList;
import java.util.List;

public class Question386 {
    public static void main(String[] args) {
        System.out.println(new Question386().lexicalOrder(13));
    }

    /**
     * 给定一个整数 n, 返回从 1 到 n 的字典顺序。
     * 例如，给定 n =1 3，返回 [1,10,11,12,13,2,3,4,5,6,7,8,9] 。
     *
     * 请尽可能的优化算法的时间复杂度和空间复杂度。 输入的数据 n 小于等于 5,000,000。
     *
     * dfs
     *
     * @param n
     * @return
     */
    public List<Integer> lexicalOrder(int n) {
        if (n <= 0) {
            return new ArrayList<>();
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            if (i <= n) {
                res.add(i);
                dfs(res, n, i);
            } else {
                break;
            }
        }

        return res;
    }

    /**
     * 进行 dfs 查找
     *
     * @param res 保存结果
     * @param max 字典的最大值（包括该值）
     * @param prefix 前缀
     */
    private void dfs(List<Integer> res, int max, int prefix) {
        if (max / prefix < 10) {
            return;
        }

        int pre = prefix * 10;  // 前缀左移一位
        for (int i = 0; i < 10; i++) {
            int curr = pre + i;
            if (curr <= max) {
                res.add(curr);
                dfs(res, max, curr);
            } else {
                break;
            }
        }
    }
}
