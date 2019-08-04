package hash;

import java.util.HashSet;
import java.util.TreeSet;

public class Question220 {
    public static void main(String[] args) {
        int[] nums = {-1, 2147483647};
        System.out.println(new Question220().containsNearbyAlmostDuplicate(nums, 1, 2147483647));
    }

    /**
     * 给定一个整数数组，判断数组中是否有两个不同的索引 i 和 j，
     * 使得 nums [i] 和 nums [j] 的差的绝对值最大为 t，并且 i 和 j 之间的差的绝对值最大为 ķ。
     *
     * hash 法
     *
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums.length < 2) {
            return false;
        }

        // 一开始写的是 k <= t*2，遇到 t 很大时直接溢出了，看来要慎用乘法
        boolean isBaseOnK = k/2 <= t;   // 判断等会遍历时，是否基于 k
        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            int curr = nums[i];
            if (isBaseOnK) {
                // 基于 k 时，遍历集合
                for (int currInt : hashSet) {
                    // 防止溢出（坑死）
                    long abs = curr < currInt ? (long) currInt - (long) curr : (long) curr - (long) currInt;
                    if (abs <= t) {
                        return true;
                    }
                }
            } else {
                // 基于 t 时，查看集合是否含有 [nums[i] - t, nums[i] + t ] 范围内的数
                for (int j = nums[i] - t; j <= nums[i] + t; j++) {
                    if (hashSet.contains(j)) {
                        return true;
                    }
                }
            }
            hashSet.add(curr);
            // 集合只需存有最近的 k 个数
            if (hashSet.size() > k) {
                hashSet.remove(nums[i - k]);
            }
        }

        return false;
    }

    /**
     * 官方题解：利用 TreeSet（emmm，比我上面的慢）
     */
    public boolean containsNearbyAlmostDuplicate_2(int[] nums, int k, int t) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            Integer s = treeSet.ceiling(nums[i]);
            if (s != null && s <= nums[i] + t) {
                return true;
            }

            Integer g = treeSet.floor(nums[i]);
            if (g != null && nums[i] <= g + t) {
                return true;
            }

            treeSet.add(nums[i]);
            if (treeSet.size() > k) {
                treeSet.remove(nums[i-k]);
            }
        }

        return false;
    }
}
