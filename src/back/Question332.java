package back;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Question332 {
    public static void main(String[] args) {
        List<List<String>> tickets = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        list1.add("JFK");
        list1.add("SFO");
        tickets.add(list1);
        List<String> list2 = new ArrayList<>();
        list2.add("JFK");
        list2.add("ATL");
        tickets.add(list2);
        List<String> list3 = new ArrayList<>();
        list3.add("SFO");
        list3.add("ATL");
        tickets.add(list3);
        List<String> list4 = new ArrayList<>();
        list4.add("ATL");
        list4.add("JFK");
        tickets.add(list4);
        List<String> list5 = new ArrayList<>();
        list5.add("ATL");
        list5.add("SFO");
        tickets.add(list5);
//        System.out.println(tickets);
        System.out.println(new Question332().findItinerary(tickets));
    }

    private List<String> res = new ArrayList<>();
    private boolean hasFound = false;

    /**
     * 给定一个机票的字符串二维数组 [from, to]，子数组中的两个成员分别表示
     * 飞机出发和降落的机场地点，对该行程进行重新规划排序。
     * 所有这些机票都属于一个从JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 出发。
     *
     * 说明:
     * 1. 如果存在多种有效的行程，你可以按字符自然排序返回最小的行程组合。
     * 例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前
     * 2. 假定所有机票至少存在一种合理的行程。
     *
     * 回溯
     *
     * @param tickets
     * @return
     */
    public List<String> findItinerary(List<List<String>> tickets) {
        int target = tickets.size() + 1;    // 目标路线所经过的机场数
        // key 代表起点，value 代表终点
        HashMap<String, List<String>> hashMap = new HashMap<>();
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            if (hashMap.containsKey(from)) {
                List<String> to = hashMap.get(from);
                to.add(ticket.get(1));
            } else {
                List<String> to = new ArrayList<>();
                to.add(ticket.get(1));
                hashMap.put(from, to);
            }
        }

        List<String> currRoad = new ArrayList<>();
        currRoad.add("JFK");
        find(currRoad, "JFK", target, hashMap);
        return res;
    }

    /**
     * 从 from 开始，查找一条长度为 target 的完整路线
     *
     * @return 是否找到路线
     */
    private void find(List<String> currRoad, String from, int target,
                         HashMap<String, List<String>> map) {
        if (hasFound) {
            return;
        }
        if (currRoad.size() == target) {
            res = currRoad;
            hasFound = true;
        }

        if (!map.containsKey(from)) {
            return;
        }
        List<String> toList = map.get(from);
        if (toList.isEmpty()) {
            return;
        }

        Collections.sort(toList);
        for(String to : toList) {
            // 新的路径
            List<String> newRoad = new ArrayList<>(currRoad);
            newRoad.add(to);
            // 从 Map 中删除该条路线
            HashMap<String, List<String>> newMap = new HashMap<>();
            for (String key : map.keySet()) {
                newMap.put(key, new ArrayList<>(map.get(key)));
            }
            List<String> list = newMap.get(from);
            list.remove(to);
            // 继续寻找
            find(newRoad, to, target, newMap);
        }
    }
}
