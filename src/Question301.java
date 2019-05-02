import java.util.*;

public class Question301 {
    public static void main(String[] args) {
        System.out.println(removeInvalidParentheses("()())()"));
        System.out.println(removeInvalidParentheses("(a)())()"));
        System.out.println(removeInvalidParentheses(")("));
    }

    /**
     * 删除最小数量的无效括号，使得输入的字符串有效，返回所有可能的结果。
     * 说明: 输入可能包含了除 ( 和 ) 以外的字符。
     *
     * 利用BFS思想
     * 1. 树的根为输入的字符串s，如果s不是有效字符串，则树的第一层由s删去一个'('或')'后的子串组成，继续判断第一层的子串是否有效，
     * 若无效则继续删去一个'('或')'，直到找到有效子串（此时删去的字符数最少）。
     * 2. 最终输出的结果集合中的元素必然是同一层中的有效字符
     *
     * @param s 输入的字符串
     * @return 所有可能的结果
     */
    private static List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        if (s.equals("()") || s.equals("")) {
            result.add(s);
            return result;
        }

        Deque<String> queue = new ArrayDeque<>();
        queue.offer(s);
        HashSet<String> set = new HashSet<>();  //用于存储裁剪后的元素，防止重复元素加入队列
        boolean isFound = false;    //判断是否找到了有效字符串

        while (!queue.isEmpty()) {  //队列不为空
            String curr = queue.poll();
            if (isValid(curr)) {
                result.add(curr);
                isFound = true;
            }
            if (isFound) {  //找到后不再进行裁剪
                continue;
            }
            //裁剪过程
            for (int i = 0; i < curr.length(); i++) {
                if (curr.charAt(i) == '(' || curr.charAt(i) == ')') {   //只对'('或')'进行裁剪
                    String str;
                    if (i == curr.length()-1) {
                        str = curr.substring(0, curr.length()-1);
                    } else {
                        str = curr.substring(0, i) + curr.substring(i+1);
                    }
                    if (set.add(str)) { //如果集合中还未有该字符串
                        queue.offer(str);
                    }
                }
            }
        }

        if (result.isEmpty()) {
            result.add("");
        }
        return result;
    }

    private static boolean isValid(String s) {
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            int curr = s.charAt(i);
            if (curr == '(') {
                left++;
            } else if (curr == ')') {
                if (left != 0) {
                    left--;
                } else {
                    return false;
                }
            }
        }
        return left == 0;
    }
}
