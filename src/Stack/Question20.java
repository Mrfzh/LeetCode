package Stack;

import java.util.ArrayDeque;
import java.util.HashMap;

public class Question20 {
    public static void main(String[] args) {
        String s = "{[]}";
        System.out.println(isValid_Stack(s));
    }

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     * 有效字符串需满足：
     * 1. 左括号必须用相同类型的右括号闭合。
     * 2. 左括号必须以正确的顺序闭合。
     *
     * 利用了递归，但是这样效率很低
     *
     * @param s 给定的字符串
     * @return 是否有效
     */
    private static boolean isValid(String s) {

        if (s.equals("") || s.equals("()") || s.equals("[]") || s.equals("{}")) {
            return true;
        }

        int n = s.length();     //字符串长度
        int firstChar = s.charAt(0);
        int same = 1;   //首字母重复次数
        int endIndex = -1;  //记录与首字母匹配的元素下标

        if (firstChar == '(') {
            for (int i = 1; i < n; i++) {
                if (s.charAt(i) == '(') {
                    same++;
                }
                if (s.charAt(i) == ')') {
                    if (same == 1) {
                        endIndex = i;
                    } else {
                        same--;
                    }
                }
            }
        } else if (firstChar == '[') {
            for (int i = 1; i < n; i++) {
                if (s.charAt(i) == '[') {
                    same++;
                }
                if (s.charAt(i) == ']') {
                    if (same == 1) {
                        endIndex = i;
                    } else {
                        same--;
                    }
                }
            }
        } else if (firstChar == '{') {
            for (int i = 1; i < n; i++) {
                if (s.charAt(i) == '{') {
                    same++;
                }
                if (s.charAt(i) == '}') {
                    if (same == 1) {
                        endIndex = i;
                    } else {
                        same--;
                    }
                }
            }
        }

        if (endIndex != -1 && endIndex != n-1) {
            return isValid(s.substring(0, endIndex+1)) && isValid(s.substring(endIndex+1, n));
        }
        if (endIndex == n-1) {
            return isValid(s.substring(1, n-1));
        }

        return false;
    }

    /**
     * 利用栈
     * 思路：将开括号推入栈，对于闭括号，检查栈顶元素是否与之匹配，匹配则将栈顶元素推出，否则说明该字符串无效
     */
    private static boolean isValid_Stack(String s) {
        if (s.equals("") || s.equals("()") || s.equals("[]") || s.equals("{}")) {
            return true;
        }

        ArrayDeque<Character> stack = new ArrayDeque<>();
        HashMap<Character, Character> hashMap = new HashMap<>();    //存储括号对
        hashMap.put('(', ')');
        hashMap.put('[', ']');
        hashMap.put('{', '}');

        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if (curr == '(' || curr == '[' || curr == '{') {
                stack.push(curr);
            } else {
                try {
                    if (hashMap.get(stack.pop()) != curr) {
                        return false;
                    }
                } catch (Exception e) {
                    return false;
                }
            }
        }

        return stack.size() == 0;
    }
}
