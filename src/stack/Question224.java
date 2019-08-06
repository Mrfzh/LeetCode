package stack;

import java.util.Stack;

public class Question224 {
    public static void main(String[] args) {
        System.out.println(new Question224().calculate("(1+(4+5+2)-3)+(6+8)"));
    }

    /**
     * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
     *
     * 字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格 。
     *
     * 栈
     *
     * @param s
     * @return
     */
    public int calculate(String s) {
        s = s.replaceAll(" ", "");
        if (s.equals("")) {
            return 0;
        }

        int res = 0;
        Stack<Integer> digitStack = new Stack<>();  // 存储数字
        Stack<Character> opStack = new Stack<>();   // 存储 +, - 和 (
        boolean lastCharIsDigit = false;    // 上一个字母是否为数字

        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (Character.isDigit(curr)) {
                // 如果当前字母为数字
                int num = curr - '0';
                if (lastCharIsDigit) {
                    // 如果之前也是数字，还要加上之前的数
                    digitStack.push(num + digitStack.pop() * 10);
                } else {
                    digitStack.push(num);
                }
                lastCharIsDigit = true;
            } else {
                if (curr == ')') {
                    // 遇到右括号时，计算直到上一左括号的结果
                    int sum = 0;
                    char currOp = opStack.pop();
                    while (currOp != '(') {
                        if (currOp == '+') {
                            sum += digitStack.pop();
                        } else {
                            sum -= digitStack.pop();
                        }
                        currOp = opStack.pop();
                    }
                    digitStack.push(sum + digitStack.pop());
                } else {
                    opStack.push(curr);
                }
                lastCharIsDigit = false;
            }
        }

        // 计算结果
        while (!opStack.isEmpty()) {
            char currOp = opStack.pop();
            if (currOp == '+') {
                res += digitStack.pop();
            } else {
                res -= digitStack.pop();
            }
        }

        return res + digitStack.pop();
    }
}
