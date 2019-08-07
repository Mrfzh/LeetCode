package skill;

import java.util.ArrayList;
import java.util.List;

public class Question229 {
    public static void main(String[] args) {
        int[] nums = {1,1,1,3,3,2,2,2};
        System.out.println(new Question229().majorityElement(nums));
    }

    /**
     * 给定一个大小为 n 的数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
     *
     * 说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1)。
     *
     * 摩尔投票法（升级版）
     *
     * @param nums
     * @return
     */
    public List<Integer> majorityElement(int[] nums) {
        if (nums.length == 0) {
            return new ArrayList<>();
        }

        List<Integer> res = new ArrayList<>();
        // 确定两个候选人
        int candidateA = nums[0];
        int candidateB = nums[0];
        int countA = 0;
        int countB = 0;

        // 遍历数组
        for (int curr : nums) {
            // 投 A（不用管 B）
            if (curr == candidateA) {
                countA++;
                continue;
            }
            // 投 B（不用管 A）
            if (curr == candidateB) {
                countB++;
                continue;
            }
            // 两个都不投时，检查是否有票数为 0 的情况，有则更新候选人
            // 注意：更新候选人时，只需更新其中一个即可，另一个不用管
            // 因为它本质只有一票，只能够去其中一边
            if (countA == 0){
                candidateA = curr;
                countA = 1;
                continue;
            }
            if (countB == 0) {
                candidateB = curr;
                countB = 1;
                continue;
            }
            // 如果两个都不投，且两个的票数都不为 0，则两个的票数都减一
            // 相当于从两边各拉了一个同归于尽
            countA--;
            countB--;
        }

        // 如果两个候选人相同，直接返回该候选人即可
        if (candidateA == candidateB) {
            res.add(candidateA);
            return res;
        }
        // 看两个候选人是否都满足条件
        countA = 0;
        countB = 0;
        for (int curr : nums) {
            if (candidateA == curr) {
                countA++;
            }
            if (candidateB == curr) {
                countB++;
            }
        }
        if (countA > nums.length / 3) {
            res.add(candidateA);
        }
        if (countB > nums.length / 3) {
            res.add(candidateB);
        }
        return res;
    }
}
