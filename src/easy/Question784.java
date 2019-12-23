package easy;

import java.util.ArrayList;
import java.util.List;

public class Question784 {
    public static void main(String[] args) {
        System.out.println(new Question784().letterCasePermutation("a1b2"));
        System.out.println(new Question784().letterCasePermutation("3z4"));
        System.out.println(new Question784().letterCasePermutation("12345"));
    }

    /**
     * 给定一个字符串S，通过将字符串S中的每个字母转变大小写，我们可以获得一个新的字符串。
     * 返回所有可能得到的字符串集合。
     *
     * @param S
     * @return
     */
    public List<String> letterCasePermutation(String S) {
        if (S.length() == 0) {
            return new ArrayList<>();
        }
        List<String> others = letterCasePermutation(S.substring(1));
        List<String> res = new ArrayList<>();
        char first = S.charAt(0);
        if (Character.isDigit(first)) {
            if (others.isEmpty()) {
                res.add(String.valueOf(first));
            } else {
                for (int i = 0; i < others.size(); i++) {
                    res.add(first + others.get(i));
                }
            }
        } else {
            int dis;
            if (first >= 'a' && first <= 'z') {
                dis = first - 'a';
            } else {
                dis = first - 'A';
            }
            if (others.isEmpty()) {
                res.add(String.valueOf((char)('a' + dis)));
                res.add(String.valueOf((char)('A' + dis)));
            } else {
                for (int i = 0; i < others.size(); i++) {
                    res.add((char)('a' + dis) + others.get(i));
                    res.add((char)('A' + dis) + others.get(i));
                }
            }
        }

        return res;
    }
}
