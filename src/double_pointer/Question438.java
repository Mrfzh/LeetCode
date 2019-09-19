package double_pointer;

import java.util.ArrayList;
import java.util.List;

public class Question438 {
    public static void main(String[] args) {
        System.out.println(new Question438().findAnagrams("cbaebabacd", "abc"));
    }

    /**
     * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，
     * 返回这些子串的起始索引。
     *
     * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
     *
     * 说明：
     * 1. 字母异位词指字母相同，但排列不同的字符串。(排序也可以相同)
     * 2. 不考虑答案输出的顺序。
     *
     * 双指针
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s.length() < p.length()) {
            return res;
        }

        // 将字符串 p 的字符按序存入数组 pa
        int[] pa = new int[26];
        for (char c : p.toCharArray()) {
            pa[c - 'a']++;
        }

        int start = 0;
        int end = 0;
        while (start <= s.length()-p.length()) {
            char endChar = s.charAt(end);
            if (pa[endChar - 'a'] == 0) {
                // 若 pa 中已经没有该字符，说明当前 start 不符合条件，start 后移
                // 先考虑边界情况：若此时 start == end，start 和 end 同时后移
                if (start == end) {
                    start++;
                    end++;
                    continue;
                }
                // 先记录下 start 对应的字符
                char oldStart = s.charAt(start);
                // start 后移，oldStart 回归 pa 数组。
                // 在后移过程中，若 oldStart != endChar，说明 pa 中还是没有 endChar，继续后移
                boolean hasFound = false;
                while (start < end) {
                    if (oldStart != endChar) {
                        pa[oldStart - 'a']++;
                        start++;
                        oldStart = s.charAt(start);
                    } else {
                        hasFound = true;
                        start++;
                        end++;  // end 指针也要后移
                        break;
                    }
                }
                if (!hasFound) {
                    // 如果前面的数都没有 endChar，说明 endChar 不是 p 中的字符，跳过 endChar
                    end++;
                    start = end;
                }
            } else {
                // 在 pa 中找到 endChar，则从 pa 中拿出一个 endChar，然后 end 后移
                pa[endChar - 'a']--;
                // 判断当前 [start, end] 是否已经满足模式 p
                if (end - start + 1 == p.length()) {
                    // 说明该 start 满足，添加进 res
                    res.add(start);
                    // start 后移
                    pa[s.charAt(start) - 'a']++;
                    start++;
                }
                end++;
            }
        }

        return res;
    }
}
