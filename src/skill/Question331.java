package skill;

public class Question331 {
    public static void main(String[] args) {
        String preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#";
//        String preorder = "1,#";
//        String preorder = "9,#,93,#,9,9,#,#,#";
        System.out.println(new Question331().isValidSerialization(preorder));
    }

    /**
     * 序列化二叉树的一种方法是使用前序遍历。当我们遇到一个非空节点时，我们可以记录下这个节点的值。
     * 如果它是一个空节点，我们可以使用一个标记值记录，例如 #。
     *
     * 给定一串以逗号分隔的序列，验证它是否是正确的二叉树的前序序列化。
     *
     * @param preorder
     * @return
     */
    public boolean isValidSerialization(String preorder) {
        preorder = preorder.trim();
        if (preorder.equals("")) {
            return false;
        }

        int num = 0;    // # 数量
        // 从后往前遍历
        for (int i = preorder.length()-1; i >= 0; i--) {
            char curr = preorder.charAt(i);
            if (curr == ',') {
                continue;
            } else if (curr == '#') {
                num++;
            } else {
                // 数字可能不止一位
                for (int j = i-1; j >= 0; j--) {
                    if (!Character.isDigit(preorder.charAt(j))) {
                        break;
                    }
                    i--;
                }
                // 将一个数字和两个 # 转换为一个 #
                if (num < 2) {
                    return false;
                }
                num--;
            }
        }

        // 如果最终只剩下一个 #，则为 true
        return num == 1;
    }
}
