package sort;

import java.util.Arrays;

public class Question274 {
    public static void main(String[] args) {
        int[] citations = {3,0,6,1,5};
        System.out.println(new Question274().hIndex(citations));
    }

    /**
     * 给定一位研究者论文被引用次数的数组（被引用次数是非负整数）。
     * 编写一个方法，计算出研究者的 h 指数。
     *
     * h 指数的定义: “h 代表“高引用次数”（high citations），
     * 一名科研人员的 h 指数是指他（她）的 （N 篇论文中）有 h 篇论文分别被引用了至少 h 次。
     * （其余的 N - h 篇论文每篇被引用次数不多于 h 次。）”
     *
     * 说明: 如果 h 有多种可能的值，h 指数是其中最大的那个。
     *
     * @param citations
     * @return
     */
    public int hIndex(int[] citations) {
        if (citations.length == 0) {
            return 0;
        }

        // 先排序
        Arrays.sort(citations);
        // 从后往前遍历，指定第 a 个数小于 a
        for (int i = citations.length-1; i >= 0; i--) {
            if (citations[i] < citations.length - i) {
                return citations.length - i - 1;
            }
        }

        return citations.length;
    }
}
