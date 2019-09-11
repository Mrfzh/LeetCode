package easy;

public class Question415 {
    public static void main(String[] args) {
        System.out.println(new Question415().addStrings("111", "131"));
    }

    /**
     * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
     *
     * 注意：
     * 1. num1 和num2 的长度都小于 5100.
     * 2. num1 和num2 都只包含数字 0-9.
     * 3. num1 和num2 都不包含任何前导零。
     * 4. 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
     *
     * @param num1
     * @param num2
     * @return
     */
    public String addStrings(String num1, String num2) {
        if (num1.equals("")) {
            return num2;
        } else if (num2.equals("")) {
            return num1;
        }
        // 截取出较长数字的前面超出部分
        String pre = "";
        if (num1.length() > num2.length()) {
            int len = num1.length()-num2.length();
            pre = num1.substring(0, len);
            num1 = num1.substring(len);
        } else if (num1.length() < num2.length()) {
            int len = num2.length()-num1.length();
            pre = num2.substring(0, len);
            num2 = num2.substring(len);
        }

        // 从后往前相加
        boolean hasMore = false;    // 是否有进位
        StringBuilder builder = new StringBuilder();
        for (int i = num1.length()-1; i >= 0; i--) {
            int a = num1.charAt(i) - '0';
            int b = num2.charAt(i) - '0';
            int sum = hasMore? a+b+1 : a+b;
            if (sum >= 10) {
                hasMore = true;
                builder.insert(0, String.valueOf(sum-10));
            } else {
                hasMore = false;
                builder.insert(0, String.valueOf(sum));
            }
        }
        if (hasMore) {
            if (pre.equals("")) {
                pre = "1";
            } else {
                StringBuilder newPre = new StringBuilder();
                for (int i = pre.length()-1; i >= 0; i--) {
                    int num = pre.charAt(i) - '0';
                    if (num == 9) {
                        newPre.append("0");
                        if (i == 0) {
                            newPre.insert(0, "1");
                        }
                    } else {
                        newPre.insert(0, String.valueOf(num+1));
                        newPre.insert(0, pre.substring(0, i));
                        break;
                    }
                }
                pre = newPre.toString();
            }
        }

        return pre + builder.toString();
    }
}
