package other;

import java.util.ArrayList;
import java.util.List;

public class Question649 {
    public static void main(String[] args) {
        System.out.println(new Question649().predictPartyVictory("RD"));
        System.out.println(new Question649().predictPartyVictory("RDD"));
        System.out.println(new Question649().predictPartyVictory("DRRDRDRDRDDRDRDR"));
    }

    /**
     * Dota2 的世界里有两个阵营：Radiant(天辉)和 Dire(夜魇)
     * Dota2 参议院由来自两派的参议员组成。现在参议院希望对一个 Dota2 游戏里的改变作出决定。
     *
     * 他们以一个基于轮为过程的投票进行。在每一轮中，每一位参议员都可以行使两项权利中的一项：
     * 1.禁止一名参议员的权利：参议员可以让另一位参议员在这一轮和之后的轮次丧失所有的权利。
     * 2. 宣布胜利：如果参议员发现有权利投票的参议员都是同一个阵营的，
     * 他可以宣布胜利并决定在游戏中的有关变化。
     *
     * 给定一个字符串代表每个参议员的阵营。字母 “R” 和 “D” 分别代表了 
     * Radiant（天辉）和 Dire（夜魇）。然后，如果有 n 个参议员，给定字符串的大小将是 n。
     *
     * 以轮为基础的过程从给定顺序的第一个参议员开始到最后一个参议员结束。
     * 这一过程将持续到投票结束。所有失去权利的参议员将在过程中被跳过。
     *
     * 假设每一位参议员都足够聪明，会为自己的政党做出最好的策略，
     * 你需要预测哪一方最终会宣布胜利并在 Dota2 游戏中决定改变。输出应该是 Radiant 或 Dire。
     *
     * 注意: 给定字符串的长度在 [1, 10,000] 之间.
     *
     * 这里用 list 和数组辅助记录，比较笨
     * 优化方法是可以使用双队列
     *
     * @param senate
     * @return
     */
    public String predictPartyVictory(String senate) {
        // R 和 D 集合分别存储 Radiant 和 Dire 阵营有权利的人的序号
        List<Integer> rList = new ArrayList<>();
        List<Integer> dList = new ArrayList<>();
        // 是否还有权利
        boolean[] isValid = new boolean[senate.length()];
        for (int i = 0; i < senate.length(); i++) {
            if (senate.charAt(i) == 'R') {
                rList.add(i);
            } else {
                dList.add(i);
            }
            isValid[i] = true;
        }
        // 按轮次投票
        while (true) {
            for (int i = 0; i < senate.length(); i++) {
                char curr = senate.charAt(i);
                if (curr == 'R') {
                    // 先判断该人是否还有权利
                    if (!isValid[i]) {
                        continue;
                    }
                    // Dire 阵营所有人失去权利，Radiant 胜利
                    if (dList.isEmpty()) {
                        return "Radiant";
                    }
                    // 让 Dire 阵营的最前序号（优先大于 i，没有就移除最前面）的人失去权利
                    if (dList.get(dList.size() - 1) < i) {
                        isValid[dList.get(0)] = false;
                        dList.remove(0);
                    } else {
                        // 找到 dList 中比 i 稍大的元素的索引
                        int index = helper(dList, i);
                        isValid[dList.get(index)] = false;
                        dList.remove(index);
                    }
                } else {
                    // 类似 R
                    if (!isValid[i]) {
                        continue;
                    }
                    if (rList.isEmpty()) {
                        return "Dire";
                    }
                    if (rList.get(rList.size() - 1) < i) {
                        isValid[rList.get(0)] = false;
                        rList.remove(0);
                    } else {
                        int index = helper(rList, i);
                        isValid[rList.get(index)] = false;
                        rList.remove(index);
                    }
                }
            }
        }
    }

    /**
     * 从 list 中找到比 i 稍大的元素的索引
     */
    private int helper(List<Integer> list, int i) {
        int start = 0;
        int end = list.size() - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (list.get(mid) <= i) {
                if (list.get(mid + 1) > i) {
                    return mid + 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if (mid - 1 < 0 || list.get(mid - 1) <= i) {
                    return mid;
                } else {
                    end = mid - 1;
                }
            }
        }

        return start;
    }
}
