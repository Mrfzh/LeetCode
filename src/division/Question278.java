package division;

public class Question278 {
    public static void main(String[] args) {
        System.out.println(new Question278().firstBadVersion(3));
    }

    /**
     * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
     *
     * 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。
     * 实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
     *
     * 二分查找
     *
     * @param n
     * @return
     */
    public int firstBadVersion(int n) {
        if (n == 1 || isBadVersion(1)) {
            return 1;
        }
        int left = 1;
        int right = n;
        while (left <= right) {
//            int mid = (left >> 1) + (right >> 1); // 避免溢出
//            if ((left & 1) == 1 && (right & 1) == 1) mid++; // 两个都是奇数时，要加 1
            int mid = left + (right - left)/2;  // 另一种写法

            if (isBadVersion(mid)) {
                if (!isBadVersion(mid-1)) {
                    return mid;
                } else {
                    right = mid - 1;
                }
            } else {
                left = mid + 1;
            }
        }

        return 0;
    }

    private boolean isBadVersion(int v) {
        return v >= 3;
    }
}
