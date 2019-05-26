package dp;

public class Question5 {

    public static void main(String[] args) {
//        System.out.println(longestPalindrome_violence("ccc"));
        System.out.println(longestPalindrome_dp("ccc"));
    }

    /**
     * 返回该字符串s的最长回文子串
     * 方法：暴力法
     * 字符串反转参考：https://blog.csdn.net/m0_37961948/article/details/72235216
     *
     * @param s
     * @return
     */
    private static String longestPalindrome_violence(String s) {

        String result = null;

        //反转字符串s，得到s1
        String s1 = new StringBuilder(s).reverse().toString();

        //找到s和s1中的相同子串（需要检查子串的索引是否与反向子串的原始索引一致，若不一致则舍弃）
        //判断该子串是否比之前的最长子串更长，若是则更新最长子串

        loop : for (int i = 0; i < s.length()-1; i++) {  //最差情况下，s.length-1趟遍历
            for (int j = 0, k = s.length()-i; k != s.length()+1; j++, k++) {
                String temp = s.substring(j, k);
                if (s1.contains(temp)) {    //如果s1中包含该子串
                    //先检查子串的索引是否与反向子串的原始索引一致
                    if (s.charAt(j) == s1.charAt(s.length()-k) && s.charAt(k-1) == s1.charAt(s.length()-1-j)) {
                        //一致地话返回该子串
                        result = temp;
                        break loop; //跳出所有循环
                    }
                }
            }
        }


        if (result == null && s.equals("")) {
            result = s;
        }
        if (result == null && s.length()>=1) {
            result = String.valueOf(s.charAt(0));
        }

        return result;
    }

    /**
     * 返回该字符串s的最长回文子串
     * 方法：动态规划
     * 参考：https://www.jianshu.com/p/a7741619dd58
     *
     * @param s
     * @return
     */
    private static String longestPalindrome_dp(String s) {
        //思路:
        //创建一个布尔类型的二维数组d[][]，d[i][j]表示第i个字母到第j个字母是否为回文子串
        //边界：d[i][i] == true
        //状态转移：d[i][j] = (d[i+1][j-1] || j - i < 3) && s.charAt(i) == s.charAt(j)

        //根据状态转移，有以下结论:
        //1. 只要j - i < 3，即字符串小于等于3个字母，再加上s.charAt(i) == s.charAt(j)，就一定是回文，例如"aba"
        //2. d[i+1][j-1]有效的条件:i+1 <= j-1即j - i >= 2
        //3. 应i递减，j递增

        if (s == null || s.equals("") || s.length() == 1) {
            return s;
        }

        int n = s.length();
        boolean[][] d = new boolean[n][n];
        //记录最终返回的最长子串的左右边界
        int left = 0;
        int right = 0;

        for (int i = n-2; i >= 0; i--) {
            d[i][i] = true;
            for (int j = i+1; j < n; j++) {
                d[i][j] = (j - i < 3 || d[i+1][j-1]) && s.charAt(i) == s.charAt(j);  //状态转移
                if (d[i][j] && j-i > right-left) {  //判断是否更新最长子串
                    left = i;
                    right = j;
                }
            }
        }

        return s.substring(left, right+1);
    }

}


