package skill;

public class Question467 {
    public static void main(String[] args) {
        System.out.println(new Question467().findSubstringInWraproundString("a"));
        System.out.println(new Question467().findSubstringInWraproundString("cac"));
        System.out.println(new Question467().findSubstringInWraproundString("zab"));
    }

    /**
     * 把字符串 s 看作是“abcdefghijklmnopqrstuvwxyz”的无限环绕字符串，
     * 所以 s 看起来是这样的："...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....". 
     *
     * 现在我们有了另一个字符串 p 。你需要的是找出 s 中有多少个唯一的 p 的非空子串，
     * 尤其是当你的输入是字符串 p ，你需要输出字符串 s 中 p 的不同的非空子串的数目。 
     *
     * 注意: p 仅由小写的英文字母组成，p 的大小可能超过 10000。
     *
     * @param p
     * @return
     */
    public int findSubstringInWraproundString(String p) {
        // 这个题要换个思路，转换成求以 26 个字母结尾的最长连续子串的长度之和

        // 思路证明：在原有连续子串的基础上，增加一个字母 a 后，连续子串的长度加一
        // 非空子串的个数就增加了 n，n 为该连续子串的新长度。
        // 而规定要最长连续子串，是因为以某个字母结尾的连续子串可能有多个，最长的可以覆盖掉较短的

        int[] len = new int[26]; // len[a] 表示以字母 a 结尾的最长连续子串的长度
        int currLen = 0;     // 记录当前连续子串的长度
        for (int i = 0; i < p.length(); i++) {
            char curr = p.charAt(i);
            if (i == 0 || curr - p.charAt(i-1) == 1 ||
                    curr - p.charAt(i-1) == -25) {
                currLen++;
            } else {
                currLen = 1;
            }

            len[curr - 'a'] = Math.max(len[curr - 'a'], currLen);
        }

        int res = 0;
        for (int i : len) {
            res += i;
        }

        return res;
    }
}
