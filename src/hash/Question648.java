package hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Question648 {
    public static void main(String[] args) {
        List<String> dict = Arrays.asList("cat", "bat", "rat");
        String sentence = "the cattle was rattled by the battery";
        System.out.println(new Question648().replaceWords(dict, sentence));
    }

    /**
     * 在英语中，我们有一个叫做 词根(root)的概念，
     * 它可以跟着其他一些词组成另一个较长的单词——我们称这个词为 继承词(successor)。
     * 例如，词根an，跟随着单词 other(其他)，可以形成新的单词 another(另一个)。
     *
     * 现在，给定一个由许多词根组成的词典和一个句子。
     * 你需要将句子中的所有继承词用词根替换掉。
     * 如果继承词有许多可以形成它的词根，则用最短的词根替换它。
     *
     * 你需要输出替换之后的句子。
     *
     * hash
     *
     * @param dict
     * @param sentence
     * @return
     */
    public String replaceWords(List<String> dict, String sentence) {
        // 每个 Set 存储以相应字母开头的词根
        List<HashSet<String>> sets = new ArrayList<>();
        // 记录每个字母开头词根的最长长度
        int[] maxLen = new int[26];
        for (int i = 0; i < 26; i++) {
            sets.add(new HashSet<>());
        }
        for (String s : dict) {
            int index = s.charAt(0) - 'a';
            sets.get(index).add(s);
            maxLen[index] = Math.max(maxLen[index], s.length());
        }

        // 将句子分成单词
        String[] words = sentence.split(" ");
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            // 找到当前单词的最短词根
            int end = Math.min(words[i].length(), maxLen[words[i].charAt(0) - 'a']);
            HashSet<String> set = sets.get(words[i].charAt(0) - 'a');
            String wordRoot = "";
            for (int j = 1; j <= end; j++) {
                if (set.contains(words[i].substring(0, j))) {
                    wordRoot = words[i].substring(0, j);
                    break;
                }
            }
            // 更新结果
            if (wordRoot.equals("")) {
                res.append(words[i]);
            } else {
                res.append(wordRoot);
            }
            if (i != words.length - 1) {
                res.append(" ");
            }
        }

        return res.toString();
    }
}
