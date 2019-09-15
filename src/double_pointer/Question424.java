package double_pointer;

import javafx.util.Pair;

public class Question424 {
    public static void main(String[] args) {
        System.out.println(new Question424().characterReplacement_simplify("AABABBA", 1));
    }

    /**
     * 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，
     * 总共可最多替换 k 次。在执行上述操作后，找到包含重复字母的最长子串的长度。
     *
     * @param s
     * @param k
     * @return
     */
    public int characterReplacement(String s, int k) {
        if (s.equals("")) {
            return 0;
        } else if (s.length() == 1) {
            return 1;
        }

        int left = 0;
        int right = 1;
        char[] chars = s.toCharArray();
        // nums 数组记录 字符串 s 在 [left, right] 范围内各字母的数量
        int[] nums = new int[26];
        nums[chars[0] - 'A']++;
        nums[chars[1] - 'A']++;
        int max = 1;                // 保存最大值
        char lastMax = chars[0];    // 上一最多数量字母
        while (right < chars.length) {
            int n = right-left+1;   // 当前窗口的大小
            Pair<Integer, Character> pair = count(nums, n, lastMax);
            int maxCount = pair.getKey();
            lastMax = pair.getValue();
            // 判断当前窗口是否满足条件
            if (n - maxCount <= k) {
                // 满足条件时更新 max, 右指针后移
                max = n;
                right++;
                if (right < chars.length) {
                    nums[chars[right] - 'A']++;
                }
            } else {
                // 不满足条件时，左右指针后移
                left++;
                right++;
                if (right < chars.length) {
                    nums[chars[left-1] - 'A']--;
                    nums[chars[right] - 'A']++;
                }
            }
        }

        return max;
    }

    /**
     * 根据 nums 数组以及字母个数 n 求其中最多数量的字母，返回其数量及该字母
     */
    private Pair<Integer, Character> count(int[] nums, int n, char lastMax) {
        if (nums[lastMax - 'A'] > (n >> 1)) {
            return new Pair<>(nums[lastMax - 'A'], lastMax);
        }

        int max = Integer.MIN_VALUE;
        char maxChar = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
                maxChar = (char) ('A' + i);
            }
        }

        return new Pair<>(max, maxChar);
    }

    /**
     * 简化：双指针 + 滑动窗口
     */
    public int characterReplacement_simplify(String s, int k) {
        if (s.equals("")) {
            return 0;
        }

        int start = 0;
        int maxCount = 0;   // 用于计算窗口大小，窗口大小不能超过 maxCount + k
        int[] nums = new int[26];   // 计算当前窗口内字母数量
        int max = 0;    // 存储当前最长子串的长度
        for (int end = 0; end < s.length(); end++) {
            maxCount = Math.max(maxCount, ++nums[s.charAt(end) - 'A']);

            // 保证窗口大小不超过 maxCount + k
            while (end - start + 1 > maxCount + k) {
                nums[s.charAt(start) - 'A']--;
                start++;
            }

            // 更新 max，其值为窗口大小（可能增加也可能不变）
            max = end - start + 1;
        }

        return max;
    }
}
