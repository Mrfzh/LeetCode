package easy;

public class Question686 {
    public static void main(String[] args) {
        System.out.println(new Question686().repeatedStringMatch("abcd",  "cdabcdab"));
    }

    /**
     * 给定两个字符串 A 和 B, 寻找重复叠加字符串A的最小次数，使得字符串B成为叠加后的字符串A的子串，
     * 如果不存在则返回 -1。
     *
     * 举个例子，A = "abcd"，B = "cdabcdab"。答案为 3， 因为 A 重复叠加三遍后为 “abcdabcdabcd”，
     * 此时 B 是其子串；A 重复叠加两遍后为"abcdabcd"，B 并不是其子串。
     *
     * 注意: A 与 B 字符串的长度在1和10000区间范围内。
     *
     * @param A
     * @param B
     * @return
     */
    public int repeatedStringMatch(String A, String B) {
        int al = A.length();
        int bl = B.length();
        if (al >= bl) {
            if (isValid(A, B)) {
                return 1;
            }
        }

        int time = 2;
        // 关键是给出终止条件
        while ((time - 2) * al < bl) {
            if (time * al < bl) {
                time++;
                continue;
            }
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < time; i++) {
                builder.append(A);
            }
            if (isValid(builder.toString(), B)) {
                return time;
            }
            time++;
        }

        return -1;
    }

    /**
     * 判断 s2 是不是 s1 的子串
     */
    private boolean isValid(String s1, String s2) {
        if (s1.length() < s2.length()) {
            return false;
        }
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        for (int i = 0; i <= s1.length() - s2.length(); i++) {
            int index2 = 0;
            boolean hasFound = true;
            for (int index1 = i; index2 < s2.length(); index1++, index2++) {
                if (c1[index1] != c2[index2]) {
                    hasFound = false;
                    break;
                }
            }
            if (hasFound) {
                return true;
            }
        }

        return false;
    }
}
