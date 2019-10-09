package double_pointer;

import java.util.Arrays;

public class Question522 {
    public static void main(String[] args) {
        String[] strs = {"aba", "cdc", "eae"};
        System.out.println(new Question522().findLUSlength(strs));
    }

    /**
     * 给定字符串列表，你需要从它们中找出最长的特殊序列。最长特殊序列定义如下：
     * 该序列为某字符串独有的最长子序列（即不能是其他字符串的子序列）。
     *
     * 子序列可以通过删去字符串中的某些字符实现，但不能改变剩余字符的相对顺序。
     * 空序列为所有字符串的子序列，任何字符串为其自身的子序列。
     *
     * 输入将是一个字符串列表，输出是最长特殊序列的长度。如果最长特殊序列不存在，返回 -1 。
     *
     * 提示：
     * 所有给定的字符串长度不会超过 10 。
     * 给定字符串列表的长度将在 [2, 50 ] 之间。
     *
     * 排序 + 双指针（判断子序列）
     *
     * @param strs
     * @return
     */
    public int findLUSlength(String[] strs) {
        // 题目可以转换为找到一个 不是 其他字符串的子序列 的 （最长）字符串
        // （因为一旦某字符串是其他字符串的子序列，那么它的子串也肯定是其他字符串的子序列，
        //  所以不用考虑字符串的子串，只需考虑字符串本身）

        // 首先按照字符串长度对各字符进行排列
        Arrays.sort(strs, (o1, o2) -> o1.length() - o2.length());

        // 从后往前遍历
        for (int i = strs.length-1; i >= 0; i--) {
            boolean isValid = true;
            // 查看 s[i] 是否为 s[j] 的子序列
            for (int j = 0; j < strs.length; j++) {
                // 是本身，或者 s[i] 的长度大于 s[j]，则跳过
                if (i == j || strs[i].length() > strs[j].length()) {
                    continue;
                }
                if (helper(strs[i], strs[j])) {
                    // 如果s[i] 是 s[j] 的子序列，说明 s[i] 不可取
                    isValid = false;
                    break;
                }
            }
            // 如果 s[i] 不为其他字符串的子序列，返回其长度
            if (isValid) {
                return strs[i].length();
            }
        }

        return -1;
    }

    /**
     * 检查 s1 是否为 s2 的子序列
     */
    private boolean helper(String s1, String s2) {
        if (s1.equals("") || s1.equals(s2)) {
            return true;
        }

        int p1 = 0; // p1 指针指向 s1 的当前字符
        // 遍历 s2，在遍历过程寻找子序列 s1
        for (int i = 0; i < s2.length(); i++) {
            if (s2.charAt(i) == s1.charAt(p1)) {
                p1++;
            }
            if (p1 == s1.length()) {
                // 此时说明 s2 中含有子序列 s1
                return true;
            }
        }

        return false;
    }
}
