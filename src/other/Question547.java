package other;

public class Question547 {
    public static void main(String[] args) {
        int[][] M = {{1,1,0}, {1,1,1}, {0,1,1}};
        System.out.println(new Question547().findCircleNum(M));
    }

    /**
     * 班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。
     * 如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C 的朋友。
     * 所谓的朋友圈，是指所有朋友的集合。
     *
     * 给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。
     * 如果M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，否则为不知道。
     * 你必须输出所有学生中的已知的朋友圈总数。
     *
     * 注意：
     * N 在[1,200]的范围内。
     * 对于所有学生，有M[i][i] = 1。
     * 如果有M[i][j] = 1，则有M[j][i] = 1。
     *
     * 并查集
     *
     * @param M
     * @return
     */
    public int findCircleNum(int[][] M) {
        int N = M.length;     // 人数
        // 开始时，假设每个同学各自为一个集合
        int[] parent = new int[N];
        int[] rank = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = -1;
        }
        // 合并有朋友关系的集合
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                if (M[i][j] == 1) {
                    // 合并 i 和 j 所属集合
                    union(parent, rank, i, j);
                }
            }
        }
        // 最后判断集合个数，即查找 parent[i] == -1 的元素个数
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (parent[i] == -1) {
                count++;
            }
        }

        return count;
    }

    /**
     * 返回 parent[i] 元素所属的集合（代表元素的索引）
     */
    private int find(int[] parent, int i) {
        // 保存当前节点
        int temp = i;
        while (parent[i] != -1) {
            i = parent[i];
        }
        // 将从当前节点到根节点路径的所有节点的父节点置为根节点
        while (parent[temp] != -1) {
            int next = parent[temp];
            parent[temp] = i;
            temp = next;
        }
        // 返回根节点的索引
        return i;
    }

    /**
     * 合并 parent[x] 和 parent[y] 所属集合
     */
    private void union(int[] parent, int[] rank, int x, int y) {
        int xSet = find(parent, x);
        int ySet = find(parent, y);
        if (xSet == ySet) {
            return;
        }
        if (rank[xSet] > rank[ySet]) {
            parent[ySet] = xSet;
        } else if (rank[ySet] > rank[xSet]) {
            parent[xSet] = ySet;
        } else {
            parent[ySet] = xSet;
            rank[xSet]++;
        }
    }
}
