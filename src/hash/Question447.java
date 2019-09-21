package hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Question447 {
    public static void main(String[] args) {

    }

    /**
     * 给定平面上 n 对不同的点，“回旋镖” 是由点表示的元组 (i, j, k) ，
     * 其中 i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。
     *
     * 找到所有回旋镖的数量。
     *
     * hash
     *
     * @param points
     * @return
     */
    public int numberOfBoomerangs(int[][] points) {
        // 用索引0,1,2，...表示各点
        // 遍历数组，计算各点距离，存入 HashMap 集合 map 中
        // map[a] 表示以 a 为起点
        // map[a] 的 key 表示距离，value 表示和 a 的距离为 key 的点的个数
        List<HashMap<Double, Integer>> map = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            HashMap<Double, Integer> hashMap = new HashMap<>();
            map.add(hashMap);
        }
        for (int i = 0; i < points.length; i++) {
            for (int j = i+1; j < points.length; j++) {
                // 计算两点（i 和 j）之间的距离
                double dis = distance(points, i, j);
                // 保存两点的距离
                saveDistance(map, i, dis);
                saveDistance(map, j, dis);
            }
        }

        // 计算以每个点作为 i 时，可等到的回旋镖总和
        int sum = 0;
        for (int i = 0; i < points.length; i++) {
            HashMap<Double, Integer> curr = map.get(i);
            // 计算具有相同距离的点所能组成的 j,k
            for (Map.Entry<Double, Integer> node : curr.entrySet()) {
                int num = node.getValue();
                if (num == 2) {
                    sum += 2;
                } else if (num > 2) {
                    sum += num * (num - 1);
                }
            }
        }

        return sum;
    }

    /**
     * 计算 points[i] 和 points[j] 之间的距离的平方
     */
    private double distance(int[][] points, int i, int j) {
        int x1 = points[i][0];
        int y1 = points[i][1];
        int x2 = points[j][0];
        int y2 = points[j][1];
        return Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2);
    }

    /**
     * 将距离 dis 保存到 map[start] 中
     */
    private void saveDistance(List<HashMap<Double, Integer>> map, int start, double dis) {
        HashMap<Double, Integer> curr = map.get(start);
        if (curr.containsKey(dis)) {
            curr.put(dis, curr.get(dis) + 1);
        } else {
            curr.put(dis, 1);
        }
    }
}
