package other;

public class Question14 {
    public static void main(String[] args) {
        String [] strs = {"","b"};
        System.out.println(longestCommonPrefix(strs));
    }

    /**
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     * 如果不存在公共前缀，返回空字符串 ""。
     *
     * @param strs 给定的字符串数组
     * @return 最长公共前缀
     */
    private static String longestCommonPrefix(String[] strs) {
        String result = "";
        //考虑边界情况
        if (strs.length == 0) {
            return result;
        }

        //先找出最短的字符串
        String shortest = strs[strs.length-1];
        int min = 10000;
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].length() < min) {
                min = strs[i].length();
                shortest = strs[i];
            }
        }
        if (shortest.equals("")) {
            return result;
        }

        //遍历数组，找到最长前缀
        boolean isBreak = false;
        for (int i = 0; i < shortest.length() && !isBreak; i++) {
            String curr = shortest.substring(0, i+1);
            for (int j = 0; j < strs.length; j++) {
                if (!strs[j].substring(0, i+1).equals(curr)) {    //前i+1个字符不匹配
                    isBreak = true;
                }
            }
            if (!isBreak) { //前i+1个字符匹配，更新result
                result = curr;
            }
        }

        return result;
    }
}
