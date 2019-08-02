package sort;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Question215 {
    public static void main(String[] args) {
        int[] nums = {3,2,3,1,2,4,5,5,6};
        System.out.println(new Question215().findKthLargest_2(nums, 4));
        System.out.println(new Question215().findKthLargest_3(nums, 4));
    }

    /**
     * 三种方法由快到慢：3 -> 1 -> 2
     */

    /**
     * 在未排序的数组中找到第 k 个最大的元素。
     * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    /**
     * 方法二：使用优先队列（利用小顶堆的性质）
     */
    public int findKthLargest_2(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        for (int curr : nums) {
            heap.add(curr);
            // 将第 k + 1 大的元素移除出去
            if (heap.size() > k) {
                heap.poll();
            }
        }

        return heap.poll();
    }

    /**
     * 方法三：利用桶来存储元素（只是计算）
     */
    public int findKthLargest_3(int[] nums, int k) {
        // 计算数组的最大值和最小值
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int curr : nums) {
            if (curr < min) {
                min = curr;
            }
            if (curr > max) {
                max = curr;
            }
        }
        // 桶的数量根据两者的差值决定
        int[] bucket = new int[max - min + 1];
        // 将元素放入桶的相应位置
        for (int curr : nums) {
            bucket[curr - min]++;
        }

        // 从后往前遍历，找到第 k 大的元素
        int res = 0;
        for (int i = bucket.length-1; i >= 0; i--) {
            k -= bucket[i];
            if (k <= 0) {
                res = min + i;
                break;
            }
        }

        return res;
    }
}
