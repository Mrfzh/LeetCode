package back;

import java.util.ArrayList;
import java.util.List;

public class Question131 {
    public static void main(String[] args) {
        System.out.println(new Question131().partition("aab"));
    }

    List<List<String>> res = new ArrayList<>();

    /**
     * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
     * 返回 s 所有可能的分割方案
     *
     * 回溯
     *
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        if (s.equals("")) {
            res.add(new ArrayList<>());
            return res;
        }

        back(s, new ArrayList<>(), 0);

        return res;
    }

    /**
     * 从字符串s的第start位开始，查找回文子串，currList为当前已有的回文子串
     */
    private void back(String s, List<String> currList, int start) {
        for (int i = start; i < s.length(); i++) {
            if (isValid(s.substring(start, i+1))) {
                List<String> list = new ArrayList<>(currList);
                list.add(s.substring(start, i+1));
                if (i == s.length() - 1) {
                    res.add(list);
                    return;
                } else {
                    back(s, list, i+1);
                }
            }
        }
    }

    /**
     * 判断字符串str是否为回文串
     */
    private boolean isValid(String str) {
        StringBuilder builder = new StringBuilder(str);
        return str.equals(builder.reverse().toString());
    }
}
