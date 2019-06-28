package dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Question118 {
    public static void main(String[] args) {
        System.out.println(new Question118().generate(5));
    }

    /**
     * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
     *
     * 动态规划
     *
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        if (numRows == 0) {
            return new ArrayList<>();
        }

        List<List<Integer>> res = new ArrayList<>();
        res.add(Collections.singletonList(1));
        for (int i = 1; i < numRows; i++) {
            List<Integer> list = new ArrayList<>();
            list.add(1);
            for (int j = 1; j < i; j++) {
                list.add(res.get(i-1).get(j-1) + res.get(i-1).get(j));
            }
            list.add(1);
            res.add(list);
        }

        return res;
    }
}
