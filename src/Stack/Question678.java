package Stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class Question678 {
    public static void main(String[] args) {
        System.out.println(checkValidString("()"));
        System.out.println(checkValidString("(*)"));
        System.out.println(checkValidString("(*))"));
        System.out.println(checkValidString(")(*)"));
        System.out.println(checkValidString("(**((()"));
        System.out.println(checkValidString("(())((())()()(*)(*()(())())())()()((()())((()))(*"));
    }

    /**
     * 给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则：
     * 1. 任何左括号 ( 必须有相应的右括号 )。
     * 2. 任何右括号 ) 必须有相应的左括号 ( 。
     * 3. 左括号 ( 必须在对应的右括号之前 )。
     * 4. * 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。
     * 5. 一个空字符串也被视为有效字符串。
     *
     * 利用栈的思想
     *
     * @param s 给定的字符串
     * @return 该字符是否有效
     */
    private static boolean checkValidString(String s) {
        if (s.equals("")) {
            return true;
        }

        int left = 0;   //左括号数量
        int star = 0;   //"*"字符数量
        ArrayDeque<Integer> stack = new ArrayDeque<>(); //记录左括号下标
        List<Integer> starIndexList = new ArrayList<>();    //记录 * 下标索引

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
                stack.push(i);
            } else if (s.charAt(i) == '*') {
                star++;
                starIndexList.add(i);
            } else {
                if (left != 0) {
                    left--;
                    stack.pop();
                } else {    //若没有左括号与之匹配，则看下有没有 *
                    if (star != 0) {
                        star--;
                        starIndexList.remove(0);
                    } else {
                        return false;
                    }
                }
            }
        }

        //最后看下有没有还未匹配的左括号
        if (left != 0) {
            if (left > star) {
                return false;
            } else {
                for (int i = left; i > 0; i--) {    //遍历剩下的左括号，查看是否有与之相匹配的*
                    int index = stack.pop();
                    boolean hasStar = false;
                    for (int j = 0; j < starIndexList.size(); j++) {    //查看是否存在*位于其后面
                        if (starIndexList.get(j) > index) {
                            starIndexList.remove(j);
                            hasStar = true;
                            break;
                        }
                    }
                    if (!hasStar) {
                        return false;
                    }
                }
                return true;
            }
        } else {
            return true;
        }
    }
}
