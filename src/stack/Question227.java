package stack;

import java.util.Stack;

public class Question227 {
    public static void main(String[] args) {
        System.out.println(new Question227().calculate("3  + 2*    2"));
        System.out.println(new Question227().calculate(" 3/2 "));
        System.out.println(new Question227().calculate(" 3+5 / 2 "));
        System.out.println(new Question227().calculate("42"));
        System.out.println(new Question227().calculate("31  + 2*    2 *11*3 -2 *13 +1 -2 / 3"));
    }

    /**
     * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
     *
     * 字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。
     *
     * 利用栈
     *
     * @param s
     * @return
     */
    public int calculate(String s) {
        // 先将空格去掉
        s = s.replaceAll(" ", "");

        int res = 0;
        Stack<Integer> digitStack = new Stack<>();  // 存储数字
        Stack<Character> opStack = new Stack<>();     // 存储 +、-
        boolean isLastNum = false;  //上一个数是否为数字

        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (Character.isDigit(curr)) {
                // 如果是数字的话，入栈
                int num = curr - '0';
                // 如果上一个也是数字，还要和前面的合起来
                if (isLastNum) {
                    num += 10 * digitStack.pop();
                }
                digitStack.push(num);
                isLastNum = true;
            } else {
                // 如果是 + 或 -，入栈
                if (curr == '+' || curr == '-') {
                    opStack.push(curr);
                } else {
                    // 是 * 或 / 的话，先计算它和周围两个数的结果
                    int left = digitStack.pop();
                    // 右边的数可能连着好几个数字
                    int right = 0;
                    while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                        right = right * 10 + (s.charAt(i + 1) - '0');
                        i++;
                    }
                    if (curr == '*') {
                        digitStack.push(left * right);
                    } else {
                        digitStack.push(left / right);
                    }
                }
                isLastNum = false;
            }
        }

        // 遍历完后开始计算结果
        while (!opStack.isEmpty()) {
            char op = opStack.pop();
            if (op == '+') {
                res += digitStack.pop();
            } else {
                res -= digitStack.pop();
            }
        }

        return res + digitStack.pop();
    }
}
