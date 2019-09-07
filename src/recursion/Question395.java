package recursion;

public class Question395 {
    public static void main(String[] args) {
        System.out.println(new Question395().longestSubstring("aaabb", 3));
        System.out.println(new Question395().longestSubstring("ababbc", 2));
    }

    /**
     * 找到给定字符串（由小写字符组成）中的最长子串 T ， 要求 T 中的每一字符出现次数都不少于 k 。
     * 输出 T 的长度。
     *
     * 递归
     *
     * @param s
     * @param k
     * @return
     */
    public int longestSubstring(String s, int k) {
        if (s.equals("") || s.length() < k) {
            return 0;
        }

        return length(0, s.length()-1, s.toCharArray(), k);
    }

    /**
     * 计算 chars[start, end] 中最长子串 T 的长度
     */
    private int length(int start, int end, char[] chars, int k) {
        if (end - start + 1 < k) {
            return 0;
        }
        // 统计 chars[start, end] 中各字符出现的次数
        int[] buckets = new int[26];
        for (int i = start; i <= end; i++) {
            buckets[chars[i]-'a']++;
        }
        // 缩小范围
        for (int i = start; i <= end; i++) {
            if (buckets[chars[i]-'a'] < k) {
                start++;
            } else {
                break;
            }
        }
        for (int i = end; i >= start; i--) {
            if (buckets[chars[i]-'a'] < k) {
                end--;
            } else {
                break;
            }
        }
        // 边界判断
        if (start > end) {
            return 0;
        }
        // 找到临界值（数量不足 k 的字符索引）
        for (int i = start; i <= end; i++) {
            if (buckets[chars[i]-'a'] < k) {
                return Math.max(length(start, i-1, chars, k),
                        length(i+1, end, chars, k));
            }
        }

        return end - start + 1;
    }
}
