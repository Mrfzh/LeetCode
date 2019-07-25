package other;

import java.util.Arrays;
import java.util.Comparator;

public class Question179 {
    public static void main(String[] args) {
        int[] nums = {3,30,34,5,9};
        System.out.println(new Question179().largestNumber(nums));
    }

    /**
     * 给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。
     *
     * 关键是自定义排序
     *
     * @param nums
     * @return
     */
    public String largestNumber(int[] nums) {
        if (nums.length == 0) {
            return "0";
        }

        // 先将 int 数组转化为 String 数组
        String[] strings = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strings[i] = String.valueOf(nums[i]);
        }

        // 自定义排序
        Arrays.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String s1 = o1 + o2;
                String s2 = o2 + o1;
                return s2.compareTo(s1);
            }
        });

        // 特殊情况
        if (strings[0].equals("0")){
            return "0";
        }

        // 组装 String 数组
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            builder.append(strings[i]);
        }

        return builder.toString();
    }
}
