package recursion;

public class Question427 {
    public static void main(String[] args) {

    }

    /**
     * 我们想要使用一棵四叉树来储存一个 N x N 的布尔值网络。
     * 网络中每一格的值只会是真或假。树的根结点代表整个网络。
     * 对于每个结点, 它将被分等成四个孩子结点直到这个区域内的值都是相同的.
     *
     * 你的任务是使用一个四叉树表示给定的网络。
     *
     * 递归
     *
     * @param grid
     * @return
     */
    public Node construct(int[][] grid) {
        if (grid.length == 0) {
            return null;
        }

        return constructImpl(grid, 0, 0, grid.length-1, grid.length-1);
    }

    /**
     * 构造 grid[x][y]（左上角）到 grid[x1][y1]（右下角）之间的节点
     */
    private Node constructImpl(int[][] grid, int x, int y, int x1, int y1) {
        // 只有一个节点时返回
        if (x == x1) {
            return new Node(grid[x][y] == 1, true, null, null, null, null);
        }

        int a = (x1-x)/2;
        int b = a+1;
        Node topLeft = constructImpl(grid, x, y, x + a, y + a);
        Node topRight = constructImpl(grid, x, y + b, x + a, y1);
        Node bottomLeft = constructImpl(grid, x + b, y, x1, y + a);
        Node bottomRight = constructImpl(grid, x + b, y + b, x1, y1);

        // 如果四个节点都是叶子节点，且值相同，合并它们
        if (topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf
            && (topLeft.val == topRight.val) && (topRight.val == bottomLeft.val)
                && (bottomLeft.val == bottomRight.val)) {
            return new Node(grid[x][y] == 1, true, null, null, null, null);
        } else {
            return new Node(true, false, topLeft, topRight, bottomLeft, bottomRight);
        }
    }

    class Node {
        public boolean val;     // 叶子节点时为元素的值（0/1），非叶子节点时任意
        public boolean isLeaf;  // 是否为叶子节点（全为 1/0）
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
