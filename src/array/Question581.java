package array;

public class Question581 {
    public static void main(String[] args) {
        int[] nums = {1,3,5,4,2};
        System.out.println(new Question581().findUnsortedSubarray(nums));
    }

    /**
     * 给定一个整数数组，你需要寻找一个连续的子数组，
     * 如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
     *
     * 你找到的子数组应是最短的，请输出它的长度。
     *
     * 说明 :
     * 输入的数组长度范围在 [1, 10,000]。
     * 输入的数组可能包含重复元素 ，所以升序的意思是<=。
     *
     * 需要一定技巧，用了一个小时左右才接出来，不过时间还不错，击败了 100%
     *
     * @param nums
     * @return
     */
    public int findUnsortedSubarray(int[] nums) {
        // 分别从前面和后面找到符合升序的连续子数组
        // （1）从前往后找一个连续子数组 nums[0 ... a] 满足升序且 nums[a] > nums[a+1]
        int last = nums[0];
        int a = -1;
        for (int i = 0; i < nums.length; i++) {
            if (last > nums[i]){
                a = i-1;
                break;
            }
            last = nums[i];
        }
        if (a == -1) {
            return 0;   // 整个数组都是升序，无需重组
        }
        // （2）从后往前找到一个连续子数组 nums[b ... n-1] 满足升序且 nums[b] < nums[b-1]
        last = nums[nums.length-1];
        int b = -1;
        for (int i = nums.length-1; i >= 0; i--) {
            if (last < nums[i]) {
                b = i+1;
                break;
            }
            last = nums[i];
        }

        // 找出 min 和 max，它们分别表示为要排序数组的最小和最大值
        // 然后 b 后移，直到满足 nums[b] >= max；a 前移，直到满足 nums[a] <= min
        // 这时要排序的连续子数组为 nums[a+1 ... b-1]

        int min, max;
        // 确定 min 和 max 要分两种情况：
        // （1）b - a == 1 时，min = nums[b], max = nums[a]
        // （2）b - a != 1 时，min 和 max 在 nums[a+1 ... b-1] 中找
        if (b - a == 1) {
            min = nums[b];
            max = nums[a];
        } else {
            min = max = nums[a+1];
            for (int i = a+2; i <= b-1; i++) {
                if (nums[i] < min) {
                    min = nums[i];
                }
                if (nums[i] > max) {
                    max = nums[i];
                }
            }
            // 注意：最后还要将 min 和 nums[b]，max 和 nums[a] 进行比较
            min = Math.min(min, nums[b]);
            max = Math.max(max, nums[a]);
        }

        // b 后移，直到满足 nums[b] >= max
        for (int i = b+1; ; i++) {
            if (i == nums.length || nums[i] >= max) {
                b = i;
                break;
            }
        }
        // a 前移，直到满足 nums[a] <= min
        for (int i = a-1; ; i--) {
            if (i == -1 || nums[i] <= min) {
                a = i;
                break;
            }
        }

        // 要排序的连续子数组为 nums[a+1 ... b-1]
        return b - a - 1;
    }
}
