package design;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * LeetCode 173 题
 *
 * 实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。
 * 调用 next() 将返回二叉搜索树中的下一个最小的数。
 *
 * next() 和 hasNext() 操作的时间复杂度是 O(1)，并使用 O(h) 内存，其中 h 是树的高度。
 * 你可以假设 next() 调用总是有效的，也就是说，当调用 next() 时，BST 中至少存在一个下一个最小的数。
 *
 */
public class BSTIterator {

    public static void main(String[] args) {
        Integer[] array = {7, 3, 15, null, null, 9, 20};
        TreeNode root = TreeNodeUtil.buildTree(array);
        BSTIterator iterator = new BSTIterator(root);
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
    }

//    private List<Integer> valList = new ArrayList<>();
//    private int nextIndex = 0;
//
//    public BSTIterator(TreeNode root) {
//        find(root);
//    }
//
//    private void find(TreeNode root) {
//        if (root == null) {
//            return;
//        }
//        find(root.left);
//        valList.add(root.val);
//        find(root.right);
//    }
//
//    /** @return the next smallest number */
//    public int next() {
//        return valList.get(nextIndex++);
//    }
//
//    /** @return whether we have a next smallest number */
//    public boolean hasNext() {
//        return nextIndex < valList.size();
//    }

    /**
     * 为了满足空间复杂度，可以使用栈保存节点
     */

    private Stack<TreeNode> stack = new Stack<>();

    public BSTIterator(TreeNode root) {
        TreeNode temp = root;
        while (temp != null) {
            stack.push(temp);
            temp = temp.left;
        }
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode next = stack.pop();
        // 看下它有没有右孩子（右孩子比它的父节点更小）
        TreeNode curr = next.right;
        while (curr != null) {
            stack.push(curr);
            // 有的话一路找左孩子
            curr = curr.left;
        }
        return next.val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}

