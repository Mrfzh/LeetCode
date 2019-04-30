public class EightFiveSix {
    public static void main(String[] args) {
        System.out.println(scoreOfParentheses2("()"));
        System.out.println(scoreOfParentheses2("(())"));
        System.out.println(scoreOfParentheses2("()()"));
        System.out.println(scoreOfParentheses2("(()(()))"));
        System.out.println(scoreOfParentheses2("(())()"));
        System.out.println(scoreOfParentheses2("(())()(())"));
    }

    /**
     * 给定一个平衡括号字符串 S，按下述规则计算该字符串的分数：
     * 1. () 得 1 分。
     * 2. AB 得 A + B 分，其中 A 和 B 是平衡括号字符串。
     * 3. (A) 得 2 * A 分，其中 A 是平衡括号字符串。
     *
     * 递归实现
     *
     * @param S
     * @return
     */
    private static int scoreOfParentheses(String S) {
        if (S.equals("")) {
            return 0;
        }
        if (S.equals("()")) {
            return 1;
        }

        //从第一个字符开始找出第一个有效子串
        int left = 1;
        int endIndex = -1; //第一个有效子串结尾字符的索引
        for (int i = 1; i < S.length(); i++) {
            if (S.charAt(i) == '(') {
                left++;
            } else {
                left--;
                if (left == 0) {
                    endIndex = i;
                    break;
                }
            }
        }

        if (endIndex == S.length()-1) {
            return 2 * scoreOfParentheses(S.substring(1, S.length()-1));
        } else {
            return scoreOfParentheses(S.substring(0, endIndex+1)) + scoreOfParentheses(S.substring(endIndex+1));
        }
    }

    /**
     * 非递归实现
     * 思路：只有当遇到")"且其前面是"("时，才进行分数累加。累加值为该"()"组合在整个字符串中的深度。
     * 例如："()"加2^0 = 1，"(())"加2^1 = 2，"((()))"加2^2 = 4，... 深度为depth时，加2^(depth-1)
     */
    private static int scoreOfParentheses2(String S) {
        if (S.equals("")) {
            return 0;
        }
        if (S.equals("()")) {
            return 1;
        }

        int depth = 0;     //当前深度
        int result = 0;     //当前总分

        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == '(') {
                depth++;
            } else {
                if (S.charAt(i-1) == '(') {
                    result += Math.pow(2, depth-1);
                }
                depth--;    //先计算了分数再减一
            }
        }

        return result;
    }
}
