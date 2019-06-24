package recursion;

import java.util.ArrayList;
import java.util.List;

public class Question109 {
    public static void main(String[] args) {

    }

    /**
     * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
     * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
     *
     * 链表转数组 + 递归
     *
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        List<Integer> list = new ArrayList<>();
        ListNode temp = head;
        while (temp != null) {
            list.add(temp.val);
            temp = temp.next;
        }
        Integer[] nums = list.toArray(new Integer[0]);

        return helper(nums, 0, nums.length-1);
    }

    /**
     * 将nums[start, end]转换为平衡二叉查找树
     */
    private TreeNode helper(Integer[] nums, int start, int end) {
        if (start > end) {
            return null;
        }

        int middle = (end - start) / 2 + start;     //效果和 (start + end) / 2一样，不过这样可以防止溢出
        TreeNode res = new TreeNode(nums[middle]);
        res.left = helper(nums, start, middle - 1);
        res.right = helper(nums, middle + 1, end);

        return res;
    }
}
