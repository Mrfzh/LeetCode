package other;

public class Question67 {
    public static void main(String[] args) {
        System.out.println(addBinary("1010", "1011"));
    }

    /**
     *  给定两个二进制字符串，返回他们的和（用二进制表示）。
     *  输入为非空字符串且只包含数字 1 和 0。
     *
     * @param a
     * @param b
     * @return
     */
    private static String addBinary(String a, String b) {
        int max;   //a、b中较长字符的长度
        StringBuilder builder = new StringBuilder();
        //用0填充较短的字符
        if (a.length() < b.length()) {
            max = b.length();
            int num = b.length() - a.length();
            for (int i = 0; i < num; i++) {
                builder.append("0");
            }
            builder.append(a);
            a = builder.toString();
        } else if (a.length() > b.length()) {
            max = a.length();
            int num = a.length() - b.length();
            for (int i = 0; i < num; i++) {
                builder.append("0");
            }
            builder.append(b);
            b = builder.toString();
        } else {
            max = a.length();
        }

        StringBuilder result = new StringBuilder();
        boolean hasCarry = false;   //是否存在进位
        for (int i = max - 1; i >= 0; i--) {
            if (a.charAt(i) == '1' && b.charAt(i) == '1' && hasCarry) {
                result.append("1");
            } else if (a.charAt(i) == '1' && b.charAt(i) == '1' && !hasCarry) {
                result.append("0");
                hasCarry = true;
            } else if (a.charAt(i) == '1' && b.charAt(i) == '0' && hasCarry) {
                result.append("0");
            } else if (a.charAt(i) == '1' && b.charAt(i) == '0' && !hasCarry) {
                result.append("1");
            } else if (a.charAt(i) == '0' && b.charAt(i) == '1' && hasCarry) {
                result.append("0");
            } else if (a.charAt(i) == '0' && b.charAt(i) == '1' && !hasCarry) {
                result.append("1");
            } else if (a.charAt(i) == '0' && b.charAt(i) == '0' && hasCarry) {
                result.append("1");
                hasCarry = false;
            } else if (a.charAt(i) == '0' && b.charAt(i) == '0' && !hasCarry) {
                result.append("0");
            }
        }
        if (hasCarry) {
            result.append("1");
        }

        return result.reverse().toString();
    }
}
