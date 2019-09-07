package recursion;

import java.util.HashMap;
import java.util.Stack;

public class Question394 {
    public static void main(String[] args) {
        System.out.println(new Question394().decodeString("100[leetcode]"));
        System.out.println(new Question394().decodeString("3[a2[c]]"));
        System.out.println(new Question394().decodeString("2[abc]3[cd]ef"));
    }

    /**
     * 给定一个经过编码的字符串，返回它解码后的字符串。
     * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
     *
     * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
     * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，
     * 例如不会出现像 3a 或 2[4] 的输入。
     *
     * 递归
     *
     * @param s
     * @return
     */
    public String decodeString(String s) {
        if (s.equals("")) {
            return "";
        }
        char[] chars = s.toCharArray();
        // 遍历一遍，找到与左括号匹配的右括号（key 为左括号索引，value 为相应的右括号索引）
        HashMap<Integer, Integer> bracketMap = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < chars.length; i++) {
            char curr = chars[i];
            if (curr == '[') {
                stack.push(i);
            } else if (curr == ']') {
                bracketMap.put(stack.pop(), i);
            }
        }

        return decode(0, chars.length-1, chars, bracketMap);
    }

    /**
     * 返回 chars[start, end] 解码后的字符串
     */
    private String decode(int start, int end, char[] chars,
                          HashMap<Integer, Integer> bracketMap) {
        StringBuilder builder = new StringBuilder();
        for (int i = start; i <= end; i++) {
            char curr = chars[i];
            if (Character.isDigit(curr)) {
                StringBuilder numBulider = new StringBuilder();
                numBulider.append(curr);
                // 连续几个数字的情况
                for (int j = i+1; j <= end; j++) {
                    if (Character.isDigit(chars[j])) {
                        numBulider.append(chars[j]);
                        i = j;
                    } else {
                        break;
                    }
                }
                int num = Integer.parseInt(numBulider.toString());
                int endBracketIndex = bracketMap.get(i+1);
                String repeatStr = decode(i+2, endBracketIndex-1, chars, bracketMap);
                for (int j = 0; j < num; j++) {
                    builder.append(repeatStr);
                }
                i = endBracketIndex;
            } else {
                builder.append(curr);
            }
        }

        return builder.toString();
    }
}
