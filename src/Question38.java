public class Question38 {
    public static void main(String[] args) {
        System.out.println(countAndSay(1));
        System.out.println(countAndSay(2));
        System.out.println(countAndSay(3));
        System.out.println(countAndSay(4));
        System.out.println(countAndSay(5));
    }

    /**
     * 报数序列是一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：
     * 1.     1
     * 2.     11
     * 3.     21
     * 4.     1211
     * 5.     111221
     *
     * 规律如下：
     * 第1项规定是1；
     * 第2项看第1项，第1项为1，即有1个1，所以第2项为11；
     * 第3项看第2项，第二项为11，即有2个1，所以第3项为21；
     * ...
     * 第n项看第n-1项
     *
     * 给定一个正整数 n（1 ≤ n ≤ 30），输出报数序列的第 n 项。
     *
     * 利用递归
     *
     * @param n
     * @return
     */
    private static String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }

        String last = countAndSay(n-1);
        StringBuilder builder = new StringBuilder();
        int lastInt = (int) last.charAt(0) - 48;
        int num = 1;
        for (int i = 1; i < last.length(); i++) {
            int curr = (int) last.charAt(i) - 48;
            if (curr == lastInt) {
                num++;
            } else {
                builder.append(num);
                builder.append(lastInt);
                num = 1;
            }
            lastInt = curr;
        }
        builder.append(num);
        builder.append(lastInt);

        return builder.toString();
    }
}
