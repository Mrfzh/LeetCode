package easy;

import javafx.util.Pair;

public class Question537 {
    public static void main(String[] args) {
        System.out.println(new Question537().complexNumberMultiply("1+-1i", "1+-1i"));
    }

    /**
     * 给定两个表示复数的字符串。
     * 返回表示它们乘积的字符串。注意，根据定义 i2 = -1 。
     *
     * 注意:
     * 1. 输入字符串不包含额外的空格。
     * 2. 输入字符串将以 a+bi 的形式给出，其中整数 a 和 b 的范围均在 [-100, 100] 之间。
     *  输出也应当符合这种形式。
     *
     * @param a
     * @param b
     * @return
     */
    public String complexNumberMultiply(String a, String b) {
        Pair<Integer, Integer> parseA = getAB(a);
        int aa = parseA.getKey();
        int ab = parseA.getValue();
        Pair<Integer, Integer> parseB = getAB(b);
        int ba = parseB.getKey();
        int bb = parseB.getValue();

        return String.valueOf(aa * ba - ab * bb) +
                "+" +
                String.valueOf(aa * bb + ab * ba) +
                "i";
    }

    /**
     * 获得 a+bi 中的 a 和 b，其中 Pair 的 key 为 a，value 为 b
     */
    private Pair<Integer, Integer> getAB(String str) {
        StringBuilder builder = new StringBuilder();
        int a = 0;
        int b = 0;
        for (char c : str.toCharArray()) {
            if (c == '+') {
                a = Integer.parseInt(builder.toString());
                builder = new StringBuilder();
                continue;
            }
            if (c == 'i') {
                b = Integer.parseInt(builder.toString());
                break;
            }
            builder.append(c);
        }

        return new Pair<>(a, b);
    }

}
