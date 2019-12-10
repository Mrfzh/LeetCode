package easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Question767 {
    public static void main(String[] args) {
        System.out.println(new Question767().reorganizeString("aaab"));
    }

    /**
     * 给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
     * 若可行，输出任意可行的结果。若不可行，返回空字符串。
     *
     * 注意:
     * S 只包含小写字母并且长度在[1, 500]区间内。
     *
     * @param S
     * @return
     */
    public String reorganizeString(String S) {
        if (S.length() < 2) {
            return S;
        }
        // 统计各字母的数量
        int[] nums = new int[26];
        int max = 1;
        for (char c : S.toCharArray()) {
            nums[c - 'a']++;
            if (nums[c - 'a'] > max) {
                max = nums[c - 'a'];
            }
        }
        // 不行的情况：
        // 1. 字符串长度为偶数，最多的字母超过 n/2
        // 2. 字符串长度为奇数，最多的字母超过 n/2 + 1
        if (S.length() % 2 == 0 && max > S.length()/2) {
            return "";
        }
        if (S.length() % 2 == 1 && max > S.length()/2 + 1) {
            return "";
        }

        // HashMap 记录每个数量由哪些字母
        HashMap<Integer, List<Character>> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(nums[i])) {
                List<Character> list = hashMap.get(nums[i]);
                list.add((char)('a'+i));
            } else {
                List<Character> list = new ArrayList<>();
                list.add((char)('a'+i));
                hashMap.put(nums[i], list);
            }
        }
        // 按照字母数量由多到少进行分配
        Arrays.sort(nums);
        int index = 0;  // 要插入的位置
        char[] chars = new char[S.length()];
        for (int i = nums.length-1; i >= 0; i--) {
            if (nums[i] == 0) {
                break;
            }
            if (!hashMap.containsKey(nums[i])) {
                continue;
            }
            // 插入数量为 nums[i] 的字母
            List<Character> list = hashMap.get(nums[i]);
            for (char c : list) {
                for (int j = 0; j < nums[i]; j++) {
                    // 插入 index 的位置
                    chars[index] = c;
                    index += 2;
                    if (index >= S.length()) {
                        index = 1;
                    }
                }
            }
            // 插入后从 hashMap 中删除该数量
            hashMap.remove(nums[i]);
        }

        return new String(chars);
    }
}
