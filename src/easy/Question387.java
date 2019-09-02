package easy;

public class Question387 {
    public static void main(String[] args) {
        System.out.println(new Question387().firstUniqChar("loveleetcode"));
    }

    /**
     * 给定一个字符串，找到它的第一个不重复（在字符串中仅出现一次）的字符，并返回它的索引。
     * 如果不存在，则返回 -1。您可以假定该字符串只包含小写字母。
     *
     * @param s
     * @return
     */
    public int firstUniqChar(String s) {
        if (s.equals("")) {
            return -1;
        }

        // 按顺序存储 26 个小写字母在字符串出现的次数
        int[] bucket = new int[26];
        for (int i = 0; i < s.length(); i++) {
            bucket[s.charAt(i) - 'a']++;
        }

        // 查找第一个不重复的字符索引
        for (int i = 0; i < s.length(); i++) {
            if (bucket[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }

        return -1;
    }
}
