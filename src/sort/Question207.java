package sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Question207 {
    public static void main(String[] args) {
        int[][] prerequisites = {{1, 0}, {2, 1}};
        System.out.println(new Question207().canFinish(3, prerequisites));
    }

    /**
     * 现在你总共有 n 门课需要选，记为 0 到 n-1。在选修某些课程之前需要一些先修课程。 
     * 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
     *
     * 给定课程总量以及它们的先决条件，判断是否可能完成所有课程的学习？
     *
     * 拓扑排序
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites.length == 0) {
            return true;
        }

        // 各节点的入度
        int[] inDegree = new int[numCourses];
        // 各节点的后序节点
        HashMap<Integer, List<Integer>> adjNodeMap = new HashMap<>();
        // 保存各节点的入度和后序节点
        for (int[] pre : prerequisites) {
            inDegree[pre[0]]++;
            if (adjNodeMap.containsKey(pre[1])) {
                List<Integer> list = adjNodeMap.get(pre[1]);
                list.add(pre[0]);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(pre[0]);
                adjNodeMap.put(pre[1], list);
            }
        }

        LinkedList<Integer> queue = new LinkedList<>();
        // 将入度为 0 的节点保存在队列
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        // 保存拓扑排序的结果
        List<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            int curr = queue.remove();
            res.add(curr);
            // 删除该节点，将后序节点的入度减一
            List<Integer> list = adjNodeMap.get(curr);
            if (list == null) {
                continue;
            }
            for (int i = 0; i < list.size(); i++) {
                inDegree[list.get(i)]--;
                // 如果后序节点的入度变为 0，加入队列
                if (inDegree[list.get(i)] == 0) {
                    queue.add(list.get(i));
                }
            }
        }

        return res.size() == numCourses;
    }
}
