package array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class Question720 {
    public static void main(String[] args) {
        String[] words = {"a", "banana", "app", "appl", "ap", "apply", "apple"};
        System.out.println(new Question720().longestWord(words));
    }

    /**
     * 给出一个字符串数组words组成的一本英语词典。从中找出最长的一个单词，
     * 该单词是由words词典中其他单词逐步添加一个字母组成。
     * 若其中有多个可行的答案，则返回答案中字典序最小的单词。
     *
     * 若无答案，则返回空字符串。
     *
     * 注意:
     * 所有输入的字符串都只包含小写字母。
     * words数组长度范围为[1,1000]。
     * words[i]的长度范围为[1,30]。
     *
     * 排序 + hash
     *
     * @param words
     * @return
     */
    public String longestWord(String[] words) {
        // 先排序，按照字符个数由少到多排序，字符个数相同时按照字典序由高到低排序
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() > o2.length()? 1 : o1.length() < o2.length() ? -1 :
                        -o1.compareTo(o2);
            }
        });
        // HashSet 存储满足条件的单词
        HashSet<String> hashSet = new HashSet<>();
        String res = "";
        for (String word : words) {
            if (word.length() - res.length() >= 2) {    // 提前结束
                break;
            }
            if (word.length() == 1 ||
                    hashSet.contains(word.substring(0, word.length()-1))) {
                res = word;
                hashSet.add(word);
            }
        }

        return res;
    }
}
