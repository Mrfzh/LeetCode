package easy;

import java.util.ArrayList;
import java.util.List;

public class Question728 {
    public static void main(String[] args) {
        System.out.println(new Question728().selfDividingNumbers(1, 22));
    }

    /**
     * 自除数是指可以被它包含的每一位数除尽的数。
     * 例如，128 是一个自除数，因为 128 % 1 == 0，128 % 2 == 0，128 % 8 == 0。
     * 还有，自除数不允许包含 0 。
     *
     * 给定上边界和下边界数字，输出一个列表，列表的元素是边界（含边界）内所有的自除数。
     *
     * 注意：每个输入参数的边界满足 1 <= left <= right <= 10000。
     *
     * @param left
     * @param right
     * @return
     */
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> res = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            // 判断 i 是不是自除数
            boolean isValid = true;
            int temp = i;
            while (temp > 0) {
                int a = temp % 10;
                if (a == 0 || i % a != 0) {
                    isValid = false;
                    break;
                }
                temp /= 10;
            }
            if (isValid) {
                res.add(i);
            }
        }

        return res;
    }
}
