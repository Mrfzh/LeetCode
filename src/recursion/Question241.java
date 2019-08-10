package recursion;

import java.util.ArrayList;
import java.util.List;

public class Question241 {
    public static void main(String[] args) {
        System.out.println(new Question241().diffWaysToCompute("2*3-4*5"));
    }

    /**
     * 给定一个含有数字和运算符的字符串，为表达式添加括号，改变其运算优先级以求出不同的结果。
     * 你需要给出所有可能的组合的结果。有效的运算符号包含 +, - 以及 * 。
     *
     * @param input
     * @return
     */
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new ArrayList<>();
        // 字符串中只有数字时返回
        if (!input.contains("+") && !input.contains("-") && !input.contains("*")) {
            res.add(Integer.parseInt(input));
            return res;
        }

        for (int i = 0; i < input.length(); i++) {
            char curr = input.charAt(i);
            if (curr == '+' || curr == '-' || curr == '*') {
                List<Integer> left = diffWaysToCompute(input.substring(0, i));
                List<Integer> right = diffWaysToCompute(input.substring(i+1));
                for (int j = 0; j < left.size(); j++) {
                    for (int k = 0; k < right.size(); k++) {
                        if (curr == '+') {
                            res.add(left.get(j) + right.get(k));
                        } else if (curr == '-') {
                            res.add(left.get(j) - right.get(k));
                        } else {
                            res.add(left.get(j) * right.get(k));
                        }
                    }
                }
            }
        }

        return res;
    }
}
