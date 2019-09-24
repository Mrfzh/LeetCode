package double_pointer;

import java.util.Arrays;

public class Question455 {
    public static void main(String[] args) {
        int[] g = {1,2};
        int[] s = {1,2,3};
        System.out.println(new Question455().findContentChildren(g, s));
    }

    /**
     * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
     * 对每个孩子 i ，都有一个胃口值 gi ，这是能让孩子们满足胃口的饼干的最小尺寸；
     * 并且每块饼干 j ，都有一个尺寸 sj 。如果 sj >= gi ，我们可以将这个饼干 j 分配给孩子 i ，
     * 这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
     *
     * 注意：
     * 你可以假设胃口值为正。
     * 一个小朋友最多只能拥有一块饼干。
     *
     * 双指针
     *
     * @param g
     * @param s
     * @return
     */
    public int findContentChildren(int[] g, int[] s) {
        if (g.length == 0 || s.length == 0) {
            return 0;
        }

        // 分别对 g 和 s 排序
        Arrays.sort(g);
        Arrays.sort(s);

        int gPoint = 0;     // 指向当前要满足的孩子
        int sPoint = 0;     // 指向当前饼干
        int count = 0;
        while (gPoint < g.length && sPoint < s.length) {
            // 若能够满足当前孩子，两指针同时后移，否则 sPoint 后移
            if (s[sPoint] >= g[gPoint]) {
                count++;
                gPoint++;
                sPoint++;
            } else {
                sPoint++;
            }
        }

        return count;
    }
}
