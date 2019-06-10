package bit;

import java.util.ArrayList;
import java.util.List;

public class Question89 {
    public static void main(String[] args) {
        System.out.println(new Question89().grayCode_bit(4));
    }

    /**
     * 格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
     * 给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。格雷编码序列必须以 0 开头。
     *
     * @param n
     * @return
     */
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();
        result.add(0);  //初始条件

        for (int i = 1; i <= n; i++) {
            int lastNum = (int) Math.pow(2, i - 1);     //n为i-1时，序列的元素个数
            //按照规律，添加新的元素进序列
            for (int j = 0; j < lastNum; j++) {
                result.add(result.get(lastNum - j - 1) + lastNum);
            }
        }

        return result;
    }

    /**
     * 利用位运算，i的格雷码G(i) = i ^ (i >> 1)
     */
    public List<Integer> grayCode_bit(int n) {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < 1 << n; i++) {
            result.add(i ^ (i >> 1));
        }

        return result;
    }
}
