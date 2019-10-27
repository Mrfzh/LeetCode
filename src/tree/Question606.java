package tree;

public class Question606 {
    public static void main(String[] args) {

    }

    /**
     * 你需要采用前序遍历的方式，将一个二叉树转换成一个由括号和整数组成的字符串。
     *
     * 空节点则用一对空括号 "()" 表示。
     * 而且你需要省略所有不影响字符串与原始二叉树之间的一对一映射关系的空括号对。
     *
     * @param t
     * @return
     */
    public String tree2str(TreeNode t) {
        if (t == null) {
            return "";
        }
        if (t.left == null && t.right == null) {
            return String.valueOf(t.val);
        } else if (t.left == null) {
            return t.val + "()(" + tree2str(t.right) + ")";
        } else if (t.right == null) {
            return t.val + "(" + tree2str(t.left) + ")";
        } else {
            return t.val + "(" + tree2str(t.left) + ")(" + tree2str(t.right) + ")";
        }
    }
}
