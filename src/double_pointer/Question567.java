package double_pointer;

public class Question567 {
    public static void main(String[] args) {
        System.out.println(new Question567().checkInclusion("ab", "eidboaoo"));
    }

    /**
     * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
     *
     * 换句话说，第二个字符串是否存在一个连续子串，其包含 s1 的所有字符。
     *
     * 注意：
     * 1. 输入的字符串只包含小写字母
     * 2. 两个字符串的长度都在 [1, 10,000] 之间
     *
     * 双指针 + 数组和二进制记录
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        if (s2.length() < s1.length()) {
            return false;
        }
        // record[a] 表示 s1 中相应字符（a == 0 表示字符 a；a == 1 表示字符 b，以此类推）
        // 还需个数，若 record[a] <= 0，说明该字符已在 s2 中找到
        // 当所有 record 中的元素都为 0 时，表示在 s2 中找到符合条件的子串，返回 true
        int[] record = new int[26];
        for (char c : s1.toCharArray()) {
            record[c - 'a']++;
        }
        for (int i = 0; i < s1.length(); i++) {
            record[s2.charAt(i) - 'a']--;
        }
        // bit 为二进制数，它由低到高表示 record[0], record[1], ...
        // 若 record[a] <= 0，则对应的二进制位为 0，否则为 1
        // 当 bit == 0 时，说明所有 record 中的元素都为 0
        int bit = 0;
        for (int i = 0; i < record.length; i++) {
            if (record[i] > 0) {
                bit = bit | (1 << i);
            }
        }
        if (bit == 0) {
            return true;
        }
        // start，end 指向 s2 的当前遍历区间
        int start = 1;
        int end = s1.length();
        while (end < s2.length()) {
            // s2[start - 1] 在 record 中加一，并更新 bit
            int index = s2.charAt(start - 1) - 'a';
            record[index]++;
            if (record[index] == 1) {
                bit = updateBit(bit, index, 1);
            }
            // s2[end] 在 record 中减一，并更新 bit
            index = s2.charAt(end) - 'a';
            record[index]--;
            if (record[index] == 0) {
                bit = updateBit(bit, index, 0);
            }
            if (bit == 0) {
                return true;
            }
            start++;
            end++;
        }

        return false;
    }

    /**
     * 更新 bit 的第 i 位（从 0 开始）为 num
     */
    private int updateBit(int bit, int i, int num) {
        if (num == 1) {
            return bit | (1 << i);
        } else {
            if ((bit & (1 << i)) == 0) {
                return bit;
            }
            return bit - (1 << i);
        }
    }
}
