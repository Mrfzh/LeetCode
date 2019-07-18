package stack;

import java.util.Stack;

public class Question150 {
    public static void main(String[] args) {
        String[] tokens = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        System.out.println(new Question150().evalRPN(tokens));
    }

    /**
     * 根据逆波兰表示法，求表达式的值。
     * 有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
     *
     * 说明：
     * 整数除法只保留整数部分。
     * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
     *
     * 栈
     *
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();    //用于存放数字

        for (int i = 0; i < tokens.length; i++) {
            if (isDigit(tokens[i])) {
                //将数字入栈
                stack.push(Integer.parseInt(tokens[i]));
            } else {
                //出栈两个数和它匹配
                int right = stack.pop();
                int left = stack.pop();
                int res = calculate(left, right, tokens[i]);
                //将计算结果入栈
                stack.push(res);
            }
        }

        return stack.pop();
    }

    /**
     * 判断该String是否为数字
     */
    private boolean isDigit(String str) {
        return !str.equals("+") && !str.equals("-") && !str.equals("*") && !str.equals("/");
    }

    /**
     * 返回运算结果
     *
     * @param left 左边运算数
     * @param right 右边运算数
     * @param operator 运算符
     * @return
     */
    private int calculate(int left, int right, String operator) {
        if (operator.equals("+")) {
            return left + right;
        } else if (operator.equals("-")) {
            return left - right;
        } else if (operator.equals("*")) {
            return left * right;
        } else {
            return left / right;
        }
    }
}
