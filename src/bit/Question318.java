package bit;

public class Question318 {
    public static void main(String[] args) {
        String[] words = {"abcw","baz","foo","bar","xtfn","abcdef"};
        System.out.println(new Question318().maxProduct(words));
    }

    /**
     * 给定一个字符串数组 words，找到 length(word[i]) * length(word[j]) 的最大值，
     * 并且这两个单词不含有公共字母。你可以认为每个单词只包含小写字母。
     * 如果不存在这样的两个单词，返回 0。
     *
     * 位运算
     *
     * @param words
     * @return
     */
    public int maxProduct(String[] words) {
        // 先将字符串表示为一个 26 位的数字，由高到低代表 z - a，
        // 位置为 1 时表示字符串含有相应字母
        int[] nums = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            int num = 0;
            String word = words[i];
            for (int j = 0; j < word.length(); j++) {
                char curr = word.charAt(j);
                num |= 1 << (curr - 'a');
            }
            nums[i] = num;
        }
        int res = 0;
        // 如果两数相与的结果为 0，说明对应的两字符串不含公共字母
        for (int i = 0; i < words.length; i++) {
            for (int j = i+1; j < words.length; j++) {
                if ((nums[i] & nums[j]) == 0) {
                    int curr = words[i].length() * words[j].length();
                    if (res < curr) {
                        res = curr;
                    }
                }
            }
        }

        return res;
    }
}
