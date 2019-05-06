import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Question17 {
    public static void main(String[] args) {
        System.out.println(letterCombinations("23"));
        System.out.println(letterCombinations("6"));
    }

    /**
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     * 2 abc, 3 def, ..., 8 tuv, 9 wxyz
     * @param digits
     * @return
     */
    private static List<String> letterCombinations(String digits) {
        if (digits.equals("")) {
            return new ArrayList<>();
        }

        HashMap<Integer, List<Character>> hashMap = new HashMap<>();    //存储数字和字母的映射关系
        hashMap.put(2, Arrays.asList('a', 'b', 'c'));
        hashMap.put(3, Arrays.asList('d', 'e', 'f'));
        hashMap.put(4, Arrays.asList('g', 'h', 'i'));
        hashMap.put(5, Arrays.asList('j', 'k', 'l'));
        hashMap.put(6, Arrays.asList('m', 'n', 'o'));
        hashMap.put(7, Arrays.asList('p', 'q', 'r', 's'));
        hashMap.put(8, Arrays.asList('t', 'u', 'v'));
        hashMap.put(9, Arrays.asList('w', 'x', 'y', 'z'));

        List<String> result = new ArrayList<>();
        List<String> currList;
        for (int i = 0; i < digits.length(); i++) {
            int currNum = (int) digits.charAt(i) - 48;
            List<Character> currCharList = hashMap.get(currNum);
            currList = new ArrayList<>();
            for (int j = 0; j < currCharList.size(); j++) {
                if (result.size() == 0) {
                    currList.add(String.valueOf(currCharList.get(j)));
                } else {
                    for (int k = 0; k < result.size(); k++) {
                        currList.add(result.get(k) + currCharList.get(j));
                    }
                }
            }
            result = currList;
        }

        return result;
    }
}
