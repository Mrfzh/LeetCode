package easy;

import java.util.ArrayList;
import java.util.List;

public class Question682 {
    public static void main(String[] args) {
        String[] ops = {"5","-2","4","C","D","9","+","+"};
        System.out.println(new Question682().calPoints(ops));
    }

    /**
     * 你现在是棒球比赛记录员。
     * 给定一个字符串列表，每个字符串可以是以下四种类型之一：
     * 1.整数（一轮的得分）：直接表示您在本轮中获得的积分数。
     * 2. "+"（一轮的得分）：表示本轮获得的得分是前两轮有效 回合得分的总和。
     * 3. "D"（一轮的得分）：表示本轮获得的得分是前一轮有效 回合得分的两倍。
     * 4. "C"（一个操作，这不是一个回合的分数）：表示您获得的最后一个有效回合的分数是无效的，应该被移除。
     *
     * 每一轮的操作都是永久性的，可能会对前一轮和后一轮产生影响。
     * 你需要返回你在所有回合中得分的总和。
     *
     * 注意：
     * 输入列表的大小将介于1和1000之间。
     * 列表中的每个整数都将介于-30000和30000之间。
     *
     * @param ops
     * @return
     */
    public int calPoints(String[] ops) {
        // 用一个 List 记录每个有效回合的分数
        List<Integer> scoreList = new ArrayList<>();
        for (String op : ops) {
            switch (op) {
                case "+":
                    scoreList.add(scoreList.get(scoreList.size() - 1) +
                            scoreList.get(scoreList.size() - 2));
                    break;
                case "D":
                    scoreList.add(scoreList.get(scoreList.size() - 1) * 2);
                    break;
                case "C":
                    scoreList.remove(scoreList.size() - 1);
                    break;
                default:
                    scoreList.add(Integer.valueOf(op));
                    break;
            }
        }

        // 最后返回有效回合的分数总和
        int res = 0;
        for (int score : scoreList) {
            res += score;
        }

        return res;
    }
}
