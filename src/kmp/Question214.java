package kmp;

public class Question214 {
    public static void main(String[] args) {
        System.out.println(shortestPalindrome("aacecaaa"));
        System.out.println(shortestPalindrome("abcd"));
        System.out.println(shortestPalindrome_KMP("aacecaaa"));
        System.out.println(shortestPalindrome_KMP("abcd"));
    }

    /**
     * 给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。
     *
     * @param s
     * @return
     */
    private static String shortestPalindrome(String s) {
        if (s.equals("") || s.length() == 1) {
            return s;
        }
        //如果整个字符串是一个回文字符串
        if (isValid(s)) {
            return s;
        }

        int index = -1;  //存储不是回文的首字母下标
        for (int i = s.length()-1; i >= 2; i--) {
            if (isValid(s.substring(0, i))) {
                //说明前i个字符是回文，而前i+1个字符不是回文
                index = i;
                break;
            }
        }

        if (index == -1) {   //整个字符串都不是回文的
            return new StringBuilder(s.substring(1)).reverse().toString() + s;
        } else {
            return new StringBuilder(s.substring(index)).reverse().toString() + s;
        }
    }

    /**
     * 判断字符串s是否是回文字符串
     */
    private static boolean isValid(String s) {
        return s.equals(new StringBuilder(s).reverse().toString());
    }

    /**
     * 利用KMP算法解决
     */
    private static String shortestPalindrome_KMP(String s) {
        if (s.equals("") || s.length() == 1) {
            return s;
        }

        String temp = s + "#" + new StringBuilder(s).reverse().toString() + "#";
        int [] next = getNext(temp);
        int index = next[temp.length()-1];  //不构成回文子串的第一个字符下标

        return new StringBuilder(s.substring(index)).reverse().toString() + s;
    }

    private static int [] getNext(String P) {
        int [] next = new int[P.length()];
        next[0] = -1;
        int k = -1;     //存储当前next[j]对应的k值

        int j = 0;
        while (j < P.length()-1) {
            if (k == -1 || P.charAt(k) == P.charAt(j)) { //隐含条件P[0 ... k-1] == P[j-k ... j-1]
                //当k == -1时，next[j++] = 0，例如next[1] = 0，或者前j字符没有相同前缀后缀时也为0。
                //当P[k] == P[j]时，由于P[0 ... k-1] == P[j-k ... j-1]，则此时P[0 ... k] == P[j-k ... j]，所以此时next[j+1] = k+1
                next[++j] = ++k;
            } else {
                //当P[k] != P[j]时，next[j+1]必定小于k，此时可以缩小k的值（最小小到-1）
                k = next[k];
            }
        }

        return next;
    }
}
