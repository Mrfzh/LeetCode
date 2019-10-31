package double_pointer;

public class Question633 {
    public static void main(String[] args) {
        System.out.println(new Question633().judgeSquareSum(5));
        System.out.println(new Question633().judgeSquareSum(3));
    }

    /**
     * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a^2 + b^2 = c。
     *
     * 双指针
     *
     * @param c
     * @return
     */
    public boolean judgeSquareSum(int c) {
        int a = (int) Math.sqrt(c);
        if (a * a == c) {
            return true;
        }

        // 双指针
        int start = 1;
        int end = a;
        while (start <= end) {
            int sum = start * start + end * end;
            if (sum == c) {
                return true;
            } else if (sum > c) {
                end--;
            } else {
                start++;
            }
        }

        return false;
    }
}
