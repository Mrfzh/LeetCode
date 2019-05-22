import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Question49 {
    public static void main(String[] args) {
        String [] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams2(strs));
    }

    /**
     * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
     *
     * 说明：
     * 1. 所有输入均为小写字母。
     * 2. 不考虑答案输出的顺序。
     *
     * @param strs
     * @return
     */
    private static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        int n = strs.length;

        if (n < 1) {
            return result;
        }
        if (n == 1) {
            List<String> list = new ArrayList<>();
            list.add(strs[0]);
            result.add(list);
            return result;
        }

        HashMap<Integer, List<Integer>> hashMap = new HashMap<>();
        int currIndex = 0;  //当前要在result数组存储的索引
        for (int i = 0; i < strs.length; i++) {
            String curr = strs[i];
            int l = curr.length();
            List<Integer> indexs = hashMap.get(l);  //长度为l的组合在result中的索引
            if (indexs != null) {
                boolean isFound = false;
                for (int j = 0; j < indexs.size(); j++) {
                    List<String> sList = result.get(indexs.get(j));
                    //判断是否为sList中的字母异位词
                    String s = sList.get(0);
                    if (isValid(s, curr)) {
                        isFound = true;
                        sList.add(curr);
                        break;
                    }
                }
                if (!isFound) { //如果没有找到合适的组合
                    List<String> list = new ArrayList<>();
                    list.add(curr);
                    result.add(list);

                    indexs.add(currIndex);
                    currIndex++;
                }
            } else {    //还没存储过该元素长度的字符串
                List<String> list = new ArrayList<>();
                list.add(curr);
                result.add(list);

                List<Integer> list1 = new ArrayList<>();
                list1.add(currIndex);
                currIndex++;
                hashMap.put(l, list1);
            }
        }

        return result;
    }

    private static boolean isValid(String s1, String s2) {
        if (s1.equals(s2)) {
            return true;
        }

        for (int i = 0; i < s1.length(); i++) {
            char curr = s1.charAt(i);
            int index;
            if ((index = s2.indexOf(curr)) != -1) {
                s2 = s2.substring(0, index) + s2.substring(index + 1);
            } else {
                return false;
            }
        }

        return true;
    }

    /**
     * 利用排序后的字符串作为同一组字母异位词的key
     */
    private static List<List<String>> groupAnagrams2(String[] strs) {
        int n = strs.length;
        if (n < 1) {
            return new ArrayList<>();
        }

        HashMap<String, List<String>> hashMap = new HashMap<>();
        //其中String存储同一字母序列，List<String>存储由key的字母组成的字符串

        for (int i = 0; i < n; i++) {
            char [] chars = strs[i].toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);
            List<String> list;
            if ((list = hashMap.get(key)) != null) {
                list.add(strs[i]);
            } else {
                List<String> newList = new ArrayList<>();
                newList.add(strs[i]);
                hashMap.put(key, newList);
            }
        }

        return new ArrayList<>(hashMap.values());
    }

    /*
     * 还有一种方法是分别用26个素数代表26个字母，利用素数相乘后的积作为同一组字母异位词的key
     * 不过这种方法如果遇到很长的字符串可能会发生溢出
     */
}
