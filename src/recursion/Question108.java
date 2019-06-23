package recursion;

public class Question108 {
    public static void main(String[] args) {

    }

    /**
     * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
     * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
     *
     * 递归
     *
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    /**
     * 将nums[start, end]转换为平衡二叉查找树
     */
    private TreeNode helper(int[] nums, int start, int end) {
        int n = end - start + 1;
        if (n == 0) {
            return null;
        } else if (n == 1) {
            return new TreeNode(nums[start]);
        } else if (n == 2) {
            TreeNode node = new TreeNode(nums[end]);
            node.left = new TreeNode(nums[start]);
            return node;
        }

        int middle = (end - start) / 2 + start;     //效果和 (start + end) / 2一样，不过这样可以防止溢出
        TreeNode res = new TreeNode(nums[middle]);
        res.left = helper(nums, start, middle - 1);
        res.right = helper(nums, middle + 1, end);

        return res;
    }
}
