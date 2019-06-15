package back;

import java.util.ArrayList;
import java.util.List;

public class Question93 {

    private List<String> result = new ArrayList<>();
    private String input;

    public static void main(String[] args) {
        System.out.println(new Question93().restoreIpAddresses("25525511135"));
        System.out.println(new Question93().restoreIpAddresses( "010010"));
    }

    /**
     * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
     *
     * 回溯
     *
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        if (s.length() <= 3 || s.length() > 12) {
            return new ArrayList<>();
        }

        input = s;
        back("", 0, 0);

        return result;
    }

    /**
     *
     * @param curr 当前的地址
     * @param currNum 当前的个数（一个完整的IP地址有4个数）
     * @param start 当前遍历到的输入字符串的位置
     */
    private void back(String curr, int currNum, int start) {
        if (currNum == 4 && start == input.length()) {
            result.add(curr.substring(0, curr.length()-1));
            return;
        }

        //如果现在剩余的字符数不足或大于组成剩下的IP地址所需的字符数，直接返回
        int remainLength = input.length() - start;
        if (remainLength < (4 - currNum) || remainLength > (4 - currNum)*3) {
            return;
        }

        if (input.charAt(start) == '0') {
            back(curr + "0.", currNum + 1, start + 1);
        } else {
            for (int i = 1; i < 4 && (start + i) <= input.length(); i++) {
                String next = input.substring(start, start+i);
                if (Integer.parseInt(next) < 256) {
                    back(curr + next + ".", currNum + 1, start + i);
                }
            }
        }
    }
}
