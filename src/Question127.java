import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question127 {

    public static void main(String[] args) {
        String begin = "hit";
        String end = "cog";
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log");

        System.out.println(new Question127().ladderLength(begin, end, wordList));
    }

    private int res = 0;

    /**
     * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。
     * 转换需遵循如下规则：
     * 1. 每次转换只能改变一个字母。
     * 2. 转换过程中的中间单词必须是字典中的单词。
     * 说明:
     * 如果不存在这样的转换序列，返回 0。
     * 所有单词具有相同的长度。
     * 所有单词只由小写字母组成。
     * 字典中不存在重复的单词。
     * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        find(beginWord, endWord, wordList, 0);

        return res;
    }

    /**
     * 判断两字符是否可以转换（是否只相差一个字母）
     */
    private boolean isValid(String str1, String str2) {
        if (str1.equals(str2)) {
            return false;
        }

        int sum = 0;
        for (int i = 0; i < str1.length() && sum <= 1; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                sum++;
            }
        }

        return sum == 1;
    }

    /**
     * 从list中找出从begin到end的转换，temp为当前序列长度（不包括begin和end）
     *
     * 超时
     */
    private void find(String begin, String end, List<String> list, int temp) {
        if (isValid(begin, end)) {
            if (res == 0 || temp + 2 < res) {
                res = temp + 2;
                return;
            }
        } else if (list.size() == 0) {
            return;
        }

        for (int i = list.size()-1; i >= 0; i--) {
            if (isValid(begin, list.get(i))) {
                List<String> curr = new ArrayList<>(list);
                curr.remove(i);
                find(list.get(i), end, curr, temp+1);
            }
        }
    }
}
