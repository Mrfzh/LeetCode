package easy;

public class Question504 {
    public static void main(String[] args) {
        System.out.println(new Question504().convertToBase7(100));
        System.out.println(new Question504().convertToBase7(-7));
    }

    /**
     * 给定一个整数，将其转化为7进制，并以字符串形式输出。
     *
     * 注意: 输入范围是 [-1e7, 1e7] 。
     *
     * @param num
     * @return
     */
    public String convertToBase7(int num) {
        if (num == 0) {
            return "0";
        }

        // 在七进制中，由低到高底数为 7^0, 7^1, ...
        // 先确定最高位的底数，在由高到低确定各位的数字

        // 注意：由于有负数，所以先将负数转换为正数
        boolean isNegative = false; // 是否为负数
        if (num < 0) {
            isNegative = true;
            num = -num;
        }

        int a = 1;  // 底数
        int i = 0;  // 底数的次幂
        while (num >= a) {
            a *= 7;
            i++;
        }
        a /= 7; // 最高位底数
        i--;    // 最高位底数的次幂

        // 由高到低确定各位的数字
        StringBuilder builder = new StringBuilder();
        if (isNegative) {
            builder.append("-");
        }
        for (int j = i; j >= 0; j--) {
            int curr = num / a;
            // 七进制数最多的数字为 6
            if (curr > 6) {
                curr = 6;
            }
            builder.append(String.valueOf(curr));
            // 更新剩余数和底数
            num = num - a*curr;
            a /= 7;
        }

        return builder.toString();
    }
}
