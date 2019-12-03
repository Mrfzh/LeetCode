package easy;

import java.util.Arrays;

public class Question748 {
    public static void main(String[] args) {
        String[] words = {"looks", "pest", "stew", "show"};
        System.out.println(new Question748().shortestCompletingWord("1s3 456", words));
    }

    /**
     * 如果单词列表（words）中的一个单词包含牌照（licensePlate）中所有的字母，那么我们称之为完整词。
     * 在所有完整词中，最短的单词我们称之为最短完整词。
     *
     * 单词在匹配牌照中的字母时不区分大小写，比如牌照中的 "P" 依然可以匹配单词中的 "p" 字母。
     * 我们保证一定存在一个最短完整词。当有多个单词都符合最短完整词的匹配条件时取单词列表中最靠前的一个。
     *
     * 牌照中可能包含多个相同的字符，比如说：对于牌照 "PP"，单词 "pair" 无法匹配，但是 "supper" 可以匹配。
     *
     * 注意:
     * 牌照（licensePlate）的长度在区域[1, 7]中。
     * 牌照（licensePlate）将会包含数字、空格、或者字母（大写和小写）。
     * 单词列表（words）长度在区间 [10, 1000] 中。
     * 每一个单词 words[i] 都是小写，并且长度在区间 [1, 15] 中。
     *
     * @param licensePlate
     * @param words
     * @return
     */
    public String shortestCompletingWord(String licensePlate, String[] words) {
        // 找出牌照的所有字母
        int[] record = new int[26];
        int num = 0;
        for (char c : licensePlate.toCharArray()) {
            if ('a' <= c && c <= 'z') {
                record[c - 'a']++;
                num++;
            } else if ('A' <= c && c <= 'Z') {
                record[c - 'A']++;
                num++;
            }
        }
        // 查看 word 是否为完整词
        String res = "";
        for (String word : words) {
            if (word.length() < num) {
                continue;
            }
            int currNum = 0;
            int[] recTemp = Arrays.copyOfRange(record, 0, record.length);
            for (char c : word.toCharArray()) {
                if (recTemp[c - 'a'] != 0) {
                    recTemp[c - 'a']--;
                    currNum++;
                    if (currNum == num) {
                        if (res.equals("") || res.length() > word.length()) {
                            res = word;
                        }
                    }
                }
            }
        }

        return res;
    }
}
