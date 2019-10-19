package easy;

public class Question558 {
    public static void main(String[] args) {

    }

    /**
     * 四叉树是一种树数据，其中每个结点恰好有四个子结点：topLeft、topRight、bottomLeft 和 bottomRight。
     * 四叉树通常被用来划分一个二维空间，递归地将其细分为四个象限或区域。
     *
     * 我们希望在四叉树中存储 True/False 信息。四叉树用来表示 N * N 的布尔网格。
     * 对于每个结点, 它将被等分成四个孩子结点直到这个区域内的值都是相同的。
     * 每个节点都有另外两个布尔属性：isLeaf 和 val。当这个节点是一个叶子结点时 isLeaf 为真。
     * val 变量储存叶子结点所代表的区域的值。
     *
     * 你的任务是实现一个函数，该函数根据两个四叉树返回表示这两个四叉树的逻辑或的四叉树。
     *
     * 递归
     *
     * @param quadTree1
     * @param quadTree2
     * @return
     */
    public Node intersect(Node quadTree1, Node quadTree2) {
        // 如果两个都是叶子节点，直接运算即可
        if (quadTree1.isLeaf && quadTree2.isLeaf) {
            return new Node(quadTree1.val | quadTree2.val, true,
                    null, null, null, null);
        }
        // 如果其中一个是叶子节点，根据该叶子节点的值分为两种情况：
        // 1. 如果该叶子节点的值为 T，运算后还是为 T
        // 2. 如果该叶子节点的值为 F，运算后结果为另一个节点的值
        else if (quadTree1.isLeaf) {
            if (quadTree1.val) {
                return new Node(true, true,
                        null, null, null, null);
            } else {
                return new Node(true, false,
                        quadTree2.topLeft, quadTree2.topRight, quadTree2.bottomLeft, quadTree2.bottomRight);
            }
        }
        else if (quadTree2.isLeaf) {
            if (quadTree2.val) {
                return new Node(true, true,
                        null, null, null, null);
            } else {
                return new Node(true, false,
                        quadTree1.topLeft, quadTree1.topRight, quadTree1.bottomLeft, quadTree1.bottomRight);
            }
        }
        // 如果两个都不是叶子节点，需要对每个子节点进行运算后再合并
        else {
            Node tl = intersect(quadTree1.topLeft, quadTree2.topLeft);
            Node tr = intersect(quadTree1.topRight, quadTree2.topRight);
            Node bl = intersect(quadTree1.bottomLeft, quadTree2.bottomLeft);
            Node br = intersect(quadTree1.bottomRight, quadTree2.bottomRight);
            // 如果四个子节点的都是叶子节点且值相同，再合并为一个叶子节点
            if (tl.isLeaf && tr.isLeaf && bl.isLeaf && br.isLeaf && (tl.val == tr.val)
                && tr.val == bl.val && bl.val == br.val) {
                return new Node(tl.val, true,
                        null, null, null, null);
            } else {
                return new Node(true, false, tl, tr, bl, br);
            }
        }
    }

    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node() {}

        public Node(boolean _val,boolean _isLeaf,Node _topLeft,Node _topRight,Node _bottomLeft,Node _bottomRight) {
            val = _val;
            isLeaf = _isLeaf;
            topLeft = _topLeft;
            topRight = _topRight;
            bottomLeft = _bottomLeft;
            bottomRight = _bottomRight;
        }
    };
}
