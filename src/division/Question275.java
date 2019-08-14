package division;

public class Question275 {
    public static void main(String[] args) {
        int[] citations = {1,1,5,6,8,9,10,11,13};
        System.out.println(new Question275().hIndex(citations));
    }

    /**
     * 给定一位研究者论文被引用次数的数组（被引用次数是非负整数），
     * 数组已经按照升序排列。编写一个方法，计算出研究者的 h 指数。
     *
     * h 指数的定义: “h 代表“高引用次数”（high citations），
     * 一名科研人员的 h 指数是指他（她）的 （N 篇论文中）有 h 篇论文分别被引用了至少 h 次。
     * （其余的 N - h 篇论文每篇被引用次数不多于 h 次。）"
     *
     * 这是 H指数 的延伸题目，本题中的 citations 数组是保证有序的。
     * 你可以优化你的算法到对数时间复杂度吗？
     *
     * 二分查找
     *
     * @param citations
     * @return
     */
    public int hIndex(int[] citations) {
        // 特殊情况
        if (citations.length == 0 || citations[citations.length-1] == 0) {
            return 0;
        }
        if (citations[0] == citations[citations.length-1]) {
            return Math.min(citations[0], citations.length);
        }
        if (citations[0] >= citations.length) {
            return citations.length;
        }
        // 二分：目标找到索引 a，满足 c[a] < n - a，c[a+1] >= n - a - 1
        // 返回 n - a - 1
        int left = 0;
        int right = citations.length - 1;
        int n = citations.length;
        while (left <= right) {
            int mid = (left + right)/2;
            if (citations[mid] < n - mid) {
                if (citations[mid+1] >= n - mid - 1) {
                    return n - mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                right = mid - 1;
            }
        }

        return 0;
    }
}
