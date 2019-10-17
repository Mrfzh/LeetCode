package hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

public class Question554 {
    public static void main(String[] args) {

    }

    /**
     * 你的面前有一堵方形的、由多行砖块组成的砖墙。 
     * 这些砖块高度相同但是宽度不同。你现在要画一条自顶向下的、穿过最少砖块的垂线。
     *
     * 砖墙由行的列表表示，每一行都是一个代表从左至右每块砖的宽度的整数列表。
     * 如果你画的线只是从砖块的边缘经过，就不算穿过这块砖。
     *
     * 你需要找出怎样画才能使这条线穿过的砖块数量最少，并且返回穿过的砖块数量。
     * 你不能沿着墙的两个垂直边缘之一画线，这样显然是没有穿过一块砖的。
     *
     * 每一行砖块的宽度之和应该相等，并且不能超过 INT_MAX。
     *
     * hash
     *
     * @param wall
     * @return
     */
    public int leastBricks(List<List<Integer>> wall) {
        int len = 0;    // 墙的宽度（也就是每一行的砖块宽度之和）
        for (int i : wall.get(0)) {
            len += i;
        }

        // key 表示宽度和，value 表示该宽度和的个数
        HashMap<Integer, Integer> sumMax = new HashMap<>();
        // key 表示宽度和，value 表示为该宽度和的行索引
        HashMap<Integer, List<Integer>> indexMap = new HashMap<>();
        // next[a] 表示第 a 行当前下一遍历位置的索引
        int[] next = new int[wall.size()];
        // 存储宽度和，按从小到大的顺序存储
        TreeSet<Integer> treeSet = new TreeSet<>();

        // 每一行都先遍历第一个数
        for (int i = 0; i < wall.size(); i++) {
            List<Integer> list = wall.get(i);
            int currNum = list.get(0);
            update(sumMax, indexMap, next, treeSet, currNum, i, 1);
        }

        // 没有穿过的砖块的最大值
        int max = 0;
        while (!treeSet.isEmpty()) {
            // 当前划线的宽度
            int currLen = treeSet.first();
            treeSet.remove(currLen);
            if (currLen == len) {
                // 当前宽度已经等于墙的总宽度时，退出循环
                break;
            }
            // 当前没有穿过的砖块数
            max = Math.max(max, sumMax.get(currLen));
            sumMax.remove(currLen);
            // 当前宽度和为 currLen 的行索引
            List<Integer> indexList = indexMap.get(currLen);
            for (int index : indexList) {
                // 加上下一遍历位置后的新长度
                int newLen = currLen + wall.get(index).get(next[index]);
                // 更新相关值
                update(sumMax, indexMap, next, treeSet, newLen, index, next[index]+1);
            }
            indexMap.remove(currLen);
        }

        return wall.size() - max;
    }

    /**
     * 更新相应记录
     *
     * @param sumMax
     * @param indexMap
     * @param next
     * @param treeSet
     * @param currNum 当前新的宽度和
     * @param i 当前行索引
     * @param nextIndex 当前行的下一遍历位置
     */
    private void update(HashMap<Integer, Integer> sumMax, HashMap<Integer,
            List<Integer>> indexMap, int[] next, TreeSet<Integer> treeSet,
                        int currNum, int i, int nextIndex) {
        // 更新该宽度的个数
        if (sumMax.containsKey(currNum)) {
            sumMax.put(currNum, sumMax.get(currNum) + 1);
        } else {
            sumMax.put(currNum, 1);
        }
        // 更新该宽度的行索引
        if (indexMap.containsKey(currNum)) {
            List<Integer> indexList = indexMap.get(currNum);
            indexList.add(i);
            indexMap.put(currNum, indexList);
        } else {
            List<Integer> indexList = new ArrayList<>();
            indexList.add(i);
            indexMap.put(currNum, indexList);
        }
        // 更新该行的下一遍历位置索引
        next[i] = nextIndex;
        // 存储当前宽度和
        treeSet.add(currNum);
    }

    /**
     * 优化：其实只要利用 hash 统计每个宽度和出现的次数 max，最后返回 row - max 即可
     *
     * 反思：其实我的思路和这个是差不多的，但是自己弄得更复杂，还是太菜了o(╥﹏╥)o
     * 以后在打码之前还是要认真理清思路，不要把问题复杂化
     */
}
