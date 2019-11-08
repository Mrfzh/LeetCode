package greedy;

import java.util.HashMap;

public class Question659 {
    public static void main(String[] args) {
        int[] nums = {1,2,3,3,4,4,5,5};
        System.out.println(new Question659().isPossible(nums));
    }

    /**
     * 输入一个按升序排序的整数数组（可能包含重复数字），你需要将它们分割成几个子序列，
     * 其中每个子序列含有的整数必须是连续的，并且至少含有三个元素。返回你是否能做出这样的分割？
     *
     * 贪心算法
     *
     * @param nums
     * @return
     */
    public boolean isPossible(int[] nums) {
        // 存储元素出现的次数，key 为元素，value 为其出现次数
        HashMap<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, 1 + countMap.getOrDefault(num, 0));
        }
        // tailMap 存储已有连续子序列的右边界的出现次数
        // key 为右边界，value 为其出现次数
        // 例如有一连续子序列为（1,2,3），则该子序列右边界为 4
        HashMap<Integer, Integer> tailMap = new HashMap<>();
        for (int x : nums) {
            if (countMap.get(x) == 0) { // 此元素已被使用了，不用管
                continue;
            } else if (tailMap.getOrDefault(x, 0) > 0) {
                // 将 x 添加到已有子序列（右边界为 x）的末尾
                // 添加后右边界为 x 的子序列数减一，右边界为 x+1 的子序列数加一
                countMap.put(x, countMap.get(x) - 1);
                tailMap.put(x, tailMap.get(x) - 1);
                tailMap.put(x+1, 1 + tailMap.getOrDefault(x+1, 0));
            } else if (countMap.getOrDefault(x+1, 0) > 0 &&
                    countMap.getOrDefault(x+2, 0) > 0) {
                // 以 x 开头，加上 x+1 和 x+2，建立一个新的连续子序列
                countMap.put(x, countMap.get(x) - 1);
                countMap.put(x+1, countMap.get(x+1) - 1);
                countMap.put(x+2, countMap.get(x+2) - 1);
                tailMap.put(x+3, 1 + tailMap.getOrDefault(x+3, 0));
            } else {
                // 上面的情况都不符合，说明 x 不能加人到合适的子序列中，返回 false
                return false;
            }
        }

        return true;
    }
}
