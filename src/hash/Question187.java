package hash;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Question187 {
    public static void main(String[] args) {
        System.out.println(new Question187()
                .findRepeatedDnaSequences( "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
    }

    /**
     * 编写一个函数来查找所有出现超过一次的10个字母长的序列（子串）。
     *
     * 用两个 HashSet
     *
     * @param s
     * @return
     */
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> res = new ArrayList<>();
        if (s.length() <= 10) {
            return res;
        }

        HashSet<String> resSet = new HashSet<>();
        HashSet<String> containSet = new HashSet<>();

        for (int i = 0; i <= s.length() - 10; i++) {
            String curr = s.substring(i, i + 10);
            // HashSet 的 contain 比 HashMap 快，所以用 HashSet 存储遍历过的
            if (containSet.contains(curr)) {
                // 用 HashSet 保存结果可去重
                resSet.add(curr);
            } else {
                containSet.add(curr);
            }
        }

        res.addAll(resSet);
        return res;
    }
}
