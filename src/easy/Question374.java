package easy;

public class Question374 {
    public static void main(String[] args) {
        System.out.println(new Question374().guessNumber(10));
    }

    /**
     * 我们正在玩一个猜数字游戏。 游戏规则如下：
     * 我从 1 到 n 选择一个数字。 你需要猜我选择了哪个数字。
     * 每次你猜错了，我会告诉你这个数字是大了还是小了。
     * 你调用一个预先定义好的接口 guess(int num)，它会返回 3 个可能的结果（-1，1 或 0）：
     * -1 : 我的数字比较小
     *  1 : 我的数字比较大
     *  0 : 恭喜！你猜对了！
     *
     * 二分
     *
     * @param n
     * @return 正确的数字
     */
    public int guessNumber(int n) {
        int left = 1;
        int right = n;
        while (left < right) {
            int mid = left + (right - left)/2;
            int res = guess(mid);
            if (res == -1) {
                right = mid - 1;
            } else if (res == 1) {
                left = mid + 1;
            } else {
                return mid;
            }
        }

        return left;
    }

    private int guess(int num) {
        return Integer.compare(6, num);
    }
}
