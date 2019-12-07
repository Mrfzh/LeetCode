package greedy;

import java.util.ArrayList;
import java.util.List;

public class Question763 {
    public static void main(String[] args) {
        System.out.println(new Question763().partitionLabels("ababcbacadefegdehijhklij"));
    }

    /**
     * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。
     * 返回一个表示每个字符串片段的长度的列表。
     *
     * 注意:
     * S的长度在[1, 500]之间。
     * S只包含小写字母'a'到'z'。
     *
     * 贪心法
     *
     * @param S
     * @return
     */
    public List<Integer> partitionLabels(String S) {
        // 先遍历一次，找到所有字母的最后一次出现位置
        int[] nums = new int[26];
        for (int i = 0; i < S.length(); i++) {
            nums[S.charAt(i) - 'a'] = i;
        }

        List<Integer> res = new ArrayList<>();
        int start = 0;
        while (start < S.length()) {
            // 找到 S[start] 出现的最后位置
            int end = nums[S.charAt(start) - 'a'];
            for (int i = start+1; i < end; i++) {
                if (nums[S.charAt(i) - 'a'] > end) {
                    // 更新 end
                    end = nums[S.charAt(i) - 'a'];
                }
            }
            // start 到 end 为一组
            res.add(end - start + 1);
            start = end+1;
        }

        return res;
    }
}
