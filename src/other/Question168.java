package other;

public class Question168 {
    public static void main(String[] args) {
        System.out.println(new Question168().convertToTitle(1));
        System.out.println(new Question168().convertToTitle(28));
        System.out.println(new Question168().convertToTitle(701));
        System.out.println(new Question168().convertToTitle(703));
        System.out.println(new Question168().convertToTitle(702));
        System.out.println(new Question168().convertToTitle(52));
    }

    /**
     * 给定一个正整数，返回它在 Excel 表中相对应的列名称。
     *
     * 例如:
     *     1 -> A
     *     2 -> B
     *     3 -> C
     *     ...
     *     26 -> Z
     *     27 -> AA
     *     28 -> AB
     *     ...
     *
     * @param n
     * @return
     */
    public String convertToTitle(int n) {
        int[] a = new int[10];      // a[x] 表示 26^0 + 26^1 + ... + 26^(x-1)，若 x == 0, a[x] = 0
        int[] b = new int[10];      // b[x] 表示 26^x

        int num;     // 表示有 num 位
        for (int i = 0; ; i++) {
            b[i] = (int) Math.pow(26, i);
            if (i == 0) {
                a[i] = 0;
            } else {
                a[i] = a[i-1] + b[i-1];
            }

            if ((n - a[i])/b[i] == 0) {
                num = i;
                break;
            }
        }

        char[] chars = {'#', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        StringBuilder builder = new StringBuilder();
        // 从第 num 位开始，一直计算到第一位
        for (int i = num; i >= 1; i--) {
            int t = n / b[i-1];
            // 最多只能到 26
            if (t > 26) {
                t = 26;
            }
            n -= t * b[i-1];
            // 不能全部用尽
            if (n == 0 && i != 1) {
                n += b[i-1];
                t--;
            }
            builder.append(chars[t]);
        }

        return builder.toString();
    }
}
