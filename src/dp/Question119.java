package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question119 {
    public static void main(String[] args) {
        System.out.println(new Question119().getRow(3));
    }

    /**
     * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
     *
     * 动态规划
     *
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow(int rowIndex) {
        if (rowIndex == 0) {
            return Arrays.asList(1);
        } else if (rowIndex == 1) {
            return Arrays.asList(1, 1);
        }

        List<Integer> last = Arrays.asList(1, 1);
        List<Integer> res = null;
        for (int i = 2; i <= rowIndex; i++) {
            res = new ArrayList<>();
            res.add(1);
            for (int j = 1; j < i; j++) {
                res.add(last.get(j-1) + last.get(j));
            }
            res.add(1);
            last = res;
        }

        return res;
    }
}
