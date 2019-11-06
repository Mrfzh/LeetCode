package tree;

public class Question654 {
    public static void main(String[] args) {

    }

    /**
     * 给定一个不含重复元素的整数数组。一个以此数组构建的最大二叉树定义如下：
     * 1. 二叉树的根是数组中的最大元素。
     * 2. 左子树是通过数组中最大值左边部分构造出的最大二叉树。
     * 3. 右子树是通过数组中最大值右边部分构造出的最大二叉树。
     *
     * 通过给定的数组构建最大二叉树，并且输出这个树的根节点。
     * 
     * 提示：给定的数组的大小在 [1, 1000] 之间。
     *
     * 递归
     *
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return buildBigTree(nums, 0, nums.length - 1);
    }

    /**
     * 根据 nums[start, end]，构造一棵最大二叉树
     */
    private TreeNode buildBigTree(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        } else if (start == end) {
            return new TreeNode(nums[start]);
        }
        
        // 找到 nums[start, end] 中的最大值的索引 maxIndex
        int maxIndex = start;
        int max = nums[start];
        for (int i = start + 1; i <= end; i++) {
            if (nums[i] > max) {
                max = nums[i];
                maxIndex = i;
            }
        }
        // nums[maxIndex] 作为根节点，左子树为 nums[start, maxIndex - 1] 构成的最大二叉树
        // 右子树为 nums[maxIndex + 1, end] 构成的最大二叉树
        TreeNode res = new TreeNode(nums[maxIndex]);
        res.left = buildBigTree(nums, start, maxIndex - 1);
        res.right = buildBigTree(nums, maxIndex + 1, end);
        
        return res;
    }
}
