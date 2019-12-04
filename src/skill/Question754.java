package skill;

public class Question754 {
    public static void main(String[] args) {
        System.out.println(new Question754().reachNumber(3));
        System.out.println(new Question754().reachNumber(2));
        System.out.println(new Question754().reachNumber(1));
        System.out.println(new Question754().reachNumber(5));
        System.out.println(new Question754().reachNumber(9));
        System.out.println(new Question754().reachNumber(14));
    }

    /**
     * 在一根无限长的数轴上，你站在0的位置。终点在target的位置。
     * 每次你可以选择向左或向右移动。第 n 次移动（从 1 开始），可以走 n 步。
     *
     * 返回到达终点需要的最小移动次数。
     *
     * 注意:
     * target是在[-10^9, 10^9]范围中的非零整数。
     *
     * 找规律
     *
     * @param target
     * @return
     */
    public int reachNumber(int target) {
        int res;
        target = Math.abs(target);  // 正负都一样
        // 找到 n，使得 1+2+...+n <= target, 1+2+...+n+n+1 > target
        int a = (int) Math.sqrt(target * 2);
        for (int i = a-1; ; i++) {
            if (sum(i) == target) {
                res = i;
                break;
            }
            if (sum(i) < target && sum(i+1) > target) {
                int b = sum(i+1) - target;  // 走 i+1 步时，超出的范围
                // 若超出的是偶数，则最终走 i+1 步即可
                // 若超出的是奇数，则根据 i+1 的奇偶性分两种情况：
                // 若 i+1 为偶数，则下一位为奇数（回退后的差距也为奇数），最终走 i+2 步即可
                // 若 i+1 为奇数，则下一位为偶数（回退后还要走两步），最终走 i+3 步
                res = (b % 2 == 0)? i+1 : ((i+1) % 2 == 0)? i+2 : i+3;
                break;
            }
        }
        return res;
    }

    /**
     * 计算 1+2+...+n 的和
     */
    private int sum(int n) {
        if (n == 0) {
            return 0;
        }
        return (1 + n) * n / 2;
    }
}
