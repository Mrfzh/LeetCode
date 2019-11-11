package find_union;

import java.util.Arrays;

public class Question684 {
    public static void main(String[] args) {
        int[][] edges = {{1,2}, {2,3}, {3,4}, {1,4}, {1,5}};
        System.out.println(Arrays.toString(new Question684().findRedundantConnection(edges)));
    }

    /**
     * 在本问题中, 树指的是一个连通且无环的无向图。
     *
     * 输入一个图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。
     * 附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。
     *
     * 输入以边组成的二维数组。每一个边的元素是一对[u, v]，满足u < v，表示连接顶点u和v的无向图的边。
     *
     * 返回一条可以删去的边，使得结果图是一个有着N个节点的树。
     * 如果有多个答案，则返回二维数组中最后出现的边。答案边 [u, v] 应满足相同的格式 u < v。
     *
     * 注意:
     * 输入的二维数组大小在 3 到 1000。
     * 二维数组中的整数在1到N之间，其中N是输入数组的大小。
     *
     * 并查集
     *
     * @param edges
     * @return
     */
    public int[] findRedundantConnection(int[][] edges) {
        // 使用并查集
        int N = edges.length;
        int[] parent = new int[N];
        int[] rank = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = -1;
        }
        // 遍历边
        int[] res = new int[2];
        for (int i = 0; i < N; i++) {
            // 找到两端点所属集合
            int x = find(parent, edges[i][0]-1);
            int y = find(parent, edges[i][1]-1);
            // 如果属于同一集合，说明该边是多余的，返回
            if (x == y) {
                res[0] = edges[i][0];
                res[1] = edges[i][1];
                break;
            } else {    // 否则合并两集合
                union(parent, rank, x, y);
            }
        }

        return res;
    }

    /**
     * 合并第 x 和第 y 个元素的所属集合
     */
    private void union(int[] parent, int[] rank, int x, int y) {
        if (rank[x] > rank[y]) {
            parent[y] = x;
        } else if (rank[y] > rank[x]) {
            parent[x] = y;
        } else {
            parent[y] = x;
            rank[x]++;
        }
    }

    /**
     * 查找第 i 个元素所属的集合
     */
    private int find(int[] parent, int i) {
        int temp = i;
        while (parent[i] != -1) {
            i = parent[i];
        }
        while (parent[temp] != -1) {
            int next = parent[temp];
            parent[temp] = i;
            temp = next;
        }

        return i;
    }
}
