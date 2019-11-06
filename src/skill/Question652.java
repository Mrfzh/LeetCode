package skill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Question652 {
    public static void main(String[] args) {

    }

    /**
     * 给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
     *
     * 两棵树重复是指它们具有相同的结构以及相同的结点值。
     *
     * 关键：将子树序列化为字符串，并用 HashMap 记录出现次数
     *
     * @param root
     * @return
     */
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        serializeTree(root);
        return res;
    }

    private List<TreeNode> res = new ArrayList<>();
    // 记录树（序列化后）的出现次数
    private HashMap<String, Integer> hashMap = new HashMap<>();

    /**
     * 序列化树 root，返回该树序列化后的字符串
     */
    private String serializeTree(TreeNode root) {
        if (root == null) {
            return "#";
        }
        String val = root.val + serializeTree(root.left) +
                serializeTree(root.right);
        if (!hashMap.containsKey(val)) {
            hashMap.put(val, 1);
        } else if (hashMap.get(val) == 1) {
            // 说明存在其他的树 root，将它加入结果中
            res.add(root);
            // 更新出现次数，之后再出现第三次就不用管它
            hashMap.put(val, 2);
        }

        return val;
    }
}
