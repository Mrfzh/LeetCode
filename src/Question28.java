public class Question28 {
    public static void main(String[] args) {
        System.out.println(strStr_KMP("hello", "ll"));
        System.out.println(strStr_KMP("aaaaa", "bba"));
        System.out.println(strStr_KMP("hello", "ol"));
    }

    /**
     * 给定一个 haystack 字符串和一个 needle 字符串，
     * 在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
     * 当 needle 是空字符串时我们应当返回 0
     *
     * @param haystack
     * @param needle
     * @return
     */
    private static int strStr(String haystack, String needle) {
        if (needle.equals("")) {
            return 0;
        }

        for (int i = 0; i < haystack.length(); i++) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                int temp = i+1;
                boolean isFound = true;
                for (int j = 1; j < needle.length(); j++, temp++) {
                    try {
                        if (!(haystack.charAt(temp) == needle.charAt(j))) {
                            isFound = false;
                            break;
                        }
                    } catch (Exception e) {
                        return -1;
                    }
                }
                if (isFound) {
                    return i;
                }
            }
        }

        return -1;
    }

    /**
     * 利用KMP算法求解
     *
     * 需要进行匹配的字符（即haystack字符串），称为主串，简称T
     * 模式（Pattern）字符串（即needle字符串），简称P
     *
     * 假设主串T用i指针遍历，而模式串P用j指针遍历。在暴力算法中，如果T[i] != P[j]，i需要回溯到i-j+1的位置，而j变为0.
     * 而在KMP算法中，则是利用已经部分匹配的有效信息，i指针不回溯，通过修改j指针，让模式串移动到有效的位置。
     *
     * KMP算法的重点是如何求j指针移动后的位置，假设j指针移动后的位置为k。
     * 当T[i] != P[j]时，已知P[0 ... j-1] == T[i-j ... i-1]，若P[0 ... k-1] == P[j-k ... j-1]
     * 则P[0 ... k-1] == T[i-k ... i-1]，此时只需将j指针移动到k，而i指针不需要移动，即可继续进行比较。
     * 所以此时问题转化为求令P[0 ... k-1] == P[j-k ... j-1]的k的最大值。
     *
     * 这里引入一个next数组，next[j]表示由模式串P的前j个字符组成的子串的最长相同前缀后缀的长度（有点绕，举个例子，
     * 假设P前5个字符组成的子串为"abcab"，则最长相同前缀后缀为"ab"，其长度为2，则next[5] = 2）。
     * 另外，定义next[j] = k，即next[j]也表示假设T[i] != P[j]时，j指针要移动的位置。（所以其实上面的例子中，
     * next[5] = 2是表示假设P中第6个字符不匹配时，j指针移动到2这个位置，注意位置从0开始表示，也就是说j应指向'c'）
     * 特殊情况：next[0] = -1（此时j不用动，i向后移一位）；next[1] = 0（此时i不用动，j回溯到第0位）
     *
     * （吐槽一下：其实next数组的含义真不太好表达，自己看了两篇博客后，有了一些自己的理解，get到了点，
     * 但要把这些表达出来让别人听懂就真的是有点难度，毕竟每个人的思维都不一样）
     */
    private static int strStr_KMP(String haystack, String needle) {
        if (needle.equals("")) {
            return 0;
        }

        char [] T = haystack.toCharArray();
        char [] P = needle.toCharArray();
        int [] next = getNext(needle);
        int i = 0;
        int j = 0;

        while (i < haystack.length() && j < needle.length()) {
            if (j == -1 || T[i] == P[j]) {  //当j == -1时，i向后移动一位，j也要归零
                i++;
                j++;
            } else {    //T[i] != P[j]时，i指针不用动，j指针移动到相应的位置
                j = next[j];
            }
        }

        if (j == needle.length()) { //说明在主串T中找到了模式串P
            return i - j;
        } else {
            return -1;
        }
    }

    /**
     * 根据模式串P获取next数组，next[j]表示P[j]匹配失败后，j指针应指向的位置。
     *
     * @param P 模式串
     * @return next数组
     */
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
