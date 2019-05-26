package dp;

public class Question10 {

    public static void main(String[] args) {
        System.out.println(isMatch_dp( "aa", "a"));
        System.out.println(isMatch_dp("aa", "a*"));
        System.out.println(isMatch_dp("ab", ".*"));
        System.out.println(isMatch_dp("aab", "c*a*b"));
        System.out.println(isMatch_dp( "mississippi", "mis*is*p*."));
        System.out.println(isMatch_dp("ab", ".*c"));
    }

    /**
     *  利用动态规划解决该问题
     */
    private static boolean isMatch_dp(String s, String p) {
        int ls = s.length();
        int lp = p.length();

        boolean [][] memory = new boolean[ls+1][lp+1];
        //memory[i][j]表示s[0..i-1]和p[0...j-1]相匹配，memory[0][0] = true
        memory[0][0] = true;    //初始条件：默认memory[0][0] = true

        for (int i = 0; i <= ls; i++) {
            for (int j = 1; j <= lp; j++) {
                //先判断当前正在处理的p字符串字符是否为'*'
                if (p.charAt(j-1) == '*') {
                    //若当前正在处理的p字符串字符为'*'
                    //则此时memory[i][j]为true(即s[0...i-1]和p[0...j-1]相匹配)的条件是
                    //1. s[0...i-1]和p[0...j-3]相匹配，这样不用管p[j-2]是什么
                    //2. 当s[0...i-2]和p[0...j-1]相匹配，这时s[0..i-1]要和p[0...j-1]相匹配，必须满足:
                    //s[i-1] == p[j-2],例如s = "aaa", p = "a*";或者p[j-2] == '.'，例如s = "aaa", p = ".*"
                    memory[i][j] = (j > 1 && memory[i][j-2]) || (i >0 && memory[i-1][j] && (s.charAt(i-1) == p.charAt(j-2)
                        || p.charAt(j-2) == '.'));
                } else {
                    //此时memory[i][j]为true(即s[0...i-1]和p[0...j-1]相匹配)的条件比较简单
                    //s[0...i-2]和p[0...j-2]相匹配，并且s[i-1] == p[j-1]或者p[j-1] == '.'
                    //例如s = "abc", p = "abc"或者s = "abc", p = "ab."
                    memory[i][j] = i > 0 && memory[i-1][j-1] && (s.charAt(i-1) == p.charAt(j-1)
                        || p.charAt(j-1) == '.');
                }
            }
        }

        return memory[ls][lp];
    }

    /**
     * 给定一个字符串 (s) 和一个字符模式 (p)。实现支持 '.' 和 '*' 的正则表达式匹配
     * '.' 匹配任意单个字符。
     * '*' 匹配零个或多个前面的元素。
     *
     * 下面是自己开始尝试的，发现用这种暴力方法连百分之十的测试用例都过不了，遂放弃
     *
     * @param s 给定的字符串
     * @param p 给定的字符模式
     * @return 字符串s是否匹配字符模式p
     */
    private static boolean isMatch(String s, String p) {
        if (s == null) {
            return p == null;
        }
        if (p == null) {
            return false;
        }
        if (p.equals(".*")) {
            return true;
        }

        //先记录下字符串s和p的长度
        int ls = s.length();
        int lp = p.length();
        int i = 0;  //s的索引
        int j = 0;  //p的索引
        //逐个元素比较
        while (i < ls && j < lp) {
            if (s.charAt(i) == p.charAt(j)) {
                i++;
                j++;
            } else {    //两个字符不相同
                if (p.charAt(j) == '.') {   //‘.’可以匹配任意字符
                    i++;
                    j++;
                }
                else if (j == 0 && p.charAt(j) == '*') { //例如 s = "aa", p = "*a"
                    return false;
                }
                else if (j < lp - 1 && p.charAt(j) != '*' && p.charAt(j+1) != '*') {
                    return false;
                }
                else if (j < lp - 1 && p.charAt(j) != '*' && p.charAt(j+1) == '*') {   //例如 s = "aa", p = "c*a"
                    i++;
                    j += 2;
                }
                else if (j > 0 && p.charAt(j) == '*' && j == lp - 1 && p.charAt(j-1) != s.charAt(i)) {    //例如 s = "aab", p = "a*"
                    return false;
                }
                else if (j > 0 && p.charAt(j) == '*' && p.charAt(j-1) == '.') {   //例如 s = "aba", p = "a.*"
                    i++;
                }
                else if (j > 0 && p.charAt(j) == '*' && p.charAt(j-1) == s.charAt(i)) {   //例如 s = "aaa", p = "a*"
                    i++;
                }
                else if (j < lp - 1 && p.charAt(j) == '*' && p.charAt(j+1) == s.charAt(i)) { //例如 s = "aab", p = "a*b"
                    i++;
                    j += 2;
                }
                else if (j < lp - 1 && p.charAt(j) == '*' && p.charAt(j+1) == '.') { //例如 s = "aab", p = "a*."
                    i++;
                    j += 2;
                }
                else if (j > 0 && p.charAt(j) == '*' && p.charAt(j-1) != s.charAt(i)) {   //例如 s = "ssi", p = "s*p"
                    return false;
                }
                System.out.println("i = " + i + ", j = " + j);
            }
        }

        //比较后字符串s剩余元素
        if (i < ls) {
            System.out.println("run 1");
            return false;
        }
        //比较后字符模板p剩余元素
        if (j < lp) {
            System.out.println("run 2");
            while (j < lp) {
                if (p.charAt(j) != '*') {
                    return false;
                }
                j++;
            }
        }

        return true;
    }
}
