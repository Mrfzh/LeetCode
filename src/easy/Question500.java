package easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question500 {
    public static void main(String[] args) {
        String[] words = {"Hello", "Alaska", "Dad", "Peace"};
        System.out.println(Arrays.toString(new Question500().findWords(words)));
    }

    /**
     * 给定一个单词列表，只返回可以使用在键盘同一行的字母打印出来的单词。键盘如下图所示。
     *
     * @param words
     * @return
     */
    public String[] findWords(String[] words) {
        // record 数组记录了各字符在键盘中的行号
        int[] record = new int[26];
        record['q'-'a'] = record['w'-'a'] = record['e'-'a'] = record['r'-'a'] = record['t'-'a'] = record['y'-'a'] =
                record['u'-'a'] = record['i'-'a'] = record['o'-'a'] = record['p'-'a'] = 1;
        record['a'-'a'] = record['s'-'a'] = record['d'-'a'] = record['f'-'a'] = record['g'-'a'] = record['h'-'a'] =
                record['j'-'a'] = record['k'-'a'] = record['l'-'a'] = 2;
        record['z'-'a'] = record['x'-'a'] = record['c'-'a'] = record['v'-'a'] = record['b'-'a'] = record['n'-'a'] =
                record['m'-'a'] = 3;

        // list 存储符合条件的单词
        List<String> list = new ArrayList<>();
        for (String word : words) {
            int lastRow = -1;
            boolean valid = true;
            for (int i = 0; i < word.length(); i++) {
                char currChar = word.charAt(i);
                int currRow = currChar >= 'a'? record[currChar - 'a'] : record[currChar - 'A'];
                if (lastRow != -1 && currRow != lastRow) {
                    valid = false;
                    break;
                }
                lastRow = currRow;
            }
            if (valid) {
                list.add(word);
            }
        }

        return list.toArray(new String[list.size()]);
    }
}
