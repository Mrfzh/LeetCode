package easy;

public class Question383 {
    public static void main(String[] args) {
        System.out.println(new Question383().canConstruct("a", "b"));
        System.out.println(new Question383().canConstruct("aa", "ab"));
        System.out.println(new Question383().canConstruct("aa", "aab"));
    }

    /**
     * 给定一个赎金信 (ransom) 字符串和一个杂志(magazine)字符串，判断第一个字符串ransom能不能
     * 由第二个字符串magazines里面的字符构成。如果可以构成，返回 true ；否则返回 false。
     * (题目说明：为了不暴露赎金信字迹，要从杂志上搜索各个需要的字母，组成单词来表达意思。)
     *
     * 注意：
     * 你可以假设两个字符串均只含有小写字母。
     *
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] bucket = new int[26];     // 依次存放 magazine 中所含 26 个小写字母的数量
        for (int i = 0; i < magazine.length(); i++) {
            char curr = magazine.charAt(i);
            bucket[curr - 'a']++;
        }
        // 扫描 ransom 所需字母
        for (int i = 0; i < ransomNote.length(); i++) {
            int index = ransomNote.charAt(i) - 'a';
            if (bucket[index] != 0) {
                bucket[index]--;
            } else {
                return false;
            }
        }

        return true;
    }
}
