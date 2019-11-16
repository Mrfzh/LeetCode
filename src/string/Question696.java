package string;

public class Question696 {
    public static void main(String[] args) {
        System.out.println(new Question696().countBinarySubstrings("00110011"));
        System.out.println(new Question696().countBinarySubstrings("10101"));
    }

    /**
     * 给定一个字符串 s，计算具有相同数量0和1的非空(连续)子字符串的数量，
     * 并且这些子字符串中的  所有0和所有1都是“连”在一起  的。
     *
     * 重复出现的子串要计算它们出现的次数。
     *
     * 注意：
     * s.length 在1到50,000之间。
     * s 只包含“0”或“1”字符。
     *
     * @param s
     * @return
     */
    public int countBinarySubstrings(String s) {
        // 遍历字符串，以两个字符构成的子串为中心，向外扩展
        int res = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) != s.charAt(i+1)) {
                res++;
                // 向外扩展
                char leftNum = s.charAt(i);
                char rightNum = s.charAt(i+1);
                int left = i - 1;
                int right = i + 2;
                while (left >= 0 && right < s.length()) {
                    if (s.charAt(left) == leftNum && s.charAt(right) == rightNum) {
                        res++;
                        left--;
                        right++;
                    } else {
                        break;
                    }
                }
            }
        }

        return res;
    }
}
