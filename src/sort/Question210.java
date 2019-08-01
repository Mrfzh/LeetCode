package sort;

import java.util.*;

public class Question210 {
    public static void main(String[] args) {
        int[][] prerequisites = {{1,0}, {2,0}, {3,1}, {3,2}};
        System.out.println(Arrays.toString(
                new Question210().findOrder(4, prerequisites)));
    }

    /**
     * 现在你总共有 n 门课需要选，记为 0 到 n-1。在选修某些课程之前需要一些先修课程。 
     * 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
     *
     * 给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。
     * 可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
     *
     * 拓扑排序
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0) {
            return new int[0];
        }
        int[] res = new int[numCourses];
        if (prerequisites.length == 0) {
            // 说明没有顺序要求
            for (int i = 0; i < numCourses; i++) {
                res[i] = i;
            }
            return res;
        }

        int[] inDegree = new int[numCourses];
        HashMap<Integer, List<Integer>> nextNodeMap = new HashMap<>();
        // 保存每个节点的入度和下一节点集合
        for (int[] pre : prerequisites) {
            inDegree[pre[0]]++;
            if (nextNodeMap.containsKey(pre[1])) {
                List<Integer> list = nextNodeMap.get(pre[1]);
                list.add(pre[0]);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(pre[0]);
                nextNodeMap.put(pre[1], list);
            }
        }

        LinkedList<Integer> queue = new LinkedList<>();
        // 将入度为 0 的节点入队
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        // 进行拓扑排序
        int index = 0;
        while (!queue.isEmpty()) {
            int curr = queue.remove();
            res[index++] = curr;
            // 查找下一节点，将下一节点的入度减一
            List<Integer> list = nextNodeMap.get(curr);
            if (list == null) {
                continue;
            }
            for (int i = 0; i < list.size(); i++) {
                inDegree[list.get(i)]--;
                // 入度变为 0，就加入队列
                if (inDegree[list.get(i)] == 0) {
                    queue.add(list.get(i));
                }
            }
        }

        return index == numCourses ? res : new int[0];
    }
}
