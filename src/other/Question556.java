package other;

import java.util.Arrays;

public class Question556 {
    public static void main(String[] args) {
        System.out.println(new Question556().nextGreaterElement(1999999999));
    }

    /**
     * 给定一个32位正整数 n，你需要找到最小的32位整数，其与 n 中存在的位数完全相同，
     * 并且其值大于n。如果不存在这样的32位整数，则返回-1。
     *
     * @param n
     * @return
     */
    public int nextGreaterElement(int n) {
        String nStr = String.valueOf(n);
        // 找到位置 i，满足 s[i+1],s[i+2],... 是一个递减（可相等）序列，且 s[i] < s[i+1]
        int i = find(nStr);
        if (i == -1) {
            return -1;
        }
        // 从 s[i+1],s[i+2],... 中找到比 s[i] 大的最小数 s[j]，并与 s[i] 交换
        int j = i;
        for (int k = i+1; k < nStr.length(); k++) {
            if (nStr.charAt(k) > nStr.charAt(i)) {
                j = k;
            } else {
                break;
            }
        }
        char[] chars = nStr.toCharArray();
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        // 从小到大排列 s[i+1],s[i+2],...
        Arrays.sort(chars, i+1, chars.length);

        int res;
        try {
            res = Integer.parseInt(String.valueOf(chars));
        } catch (Exception e) {
            // 如果抛出异常，说明该数不在整数范围内，返回 -1
            return -1;
        }
        return res;
    }

    /**
     * 找到位置 i，满足 s[i+1],s[i+2],... 是一个递减（可相等）序列，且 s[i] < s[i+1]
     * 若找不到则返回 -1
     */
    private int find(String nStr) {
        for (int i = nStr.length() - 1; i > 0; i--) {
            if (nStr.charAt(i) > nStr.charAt(i-1)) {
                return i-1;
            }
        }

        return -1;
    }
}
