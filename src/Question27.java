import java.util.Arrays;

public class Question27 {
    public static void main(String[] args) {
        int [] nums = {0,1,2,2,3,0,4,2};
        System.out.println(removeElement_doublePoint(nums, 2));
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。
     *
     * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     *
     * @param nums 给定数组
     * @param val 要删除的值
     * @return 新数组的长度
     */
    private static int removeElement(int[] nums, int val) {
        if (nums.length == 0) {
            return 0;
        }

        //先排序
        Arrays.sort(nums);

        int right = nums.length - 1;    //存储最右边元素索引
        int left = -1;  //存储第一个val元素的位置
        int valNum = 0; //val元素数量
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == val) {
                if (left == -1) {
                    left = i;
                }
                valNum++;
            }
            if (left != -1 && nums[i] != val) {
                break;
            }
        }

        while (valNum > 0) {
            nums[left] = nums[right];
            left++;
            valNum--;
            right--;
        }

        return right + 1;
    }

    /**
     * 双指针法
     */
    private static int removeElement_doublePoint(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];  //nums[i]用于存储新数组
                i++;
            }
        }

        return i;
    }
}
