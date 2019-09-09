package skill;

import java.util.ArrayList;
import java.util.List;

public class Question401 {
    public static void main(String[] args) {
        System.out.println(new Question401().readBinaryWatch(1));
    }

    /**
     * 二进制手表顶部有 4 个 LED 代表小时（0-11），底部的 6 个 LED 代表分钟（0-59）。
     * 给定一个非负整数 n 代表当前 LED 亮着的数量，返回所有可能的时间。
     *
     * 注意事项:
     * 1. 输出的顺序没有要求。
     * 2. 小时不会以零开头，比如 “01:00” 是不允许的，应为 “1:00”。
     * 3. 分钟必须由两位数组成，可能会以零开头，比如 “10:2” 是无效的，应为 “10:02”。
     *
     * @param num
     * @return
     */
    public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();
        if (num >= 9) {
            return res;
        }
        if (num == 0) {
            res.add("0:00");
            return res;
        }

        // 遍历所有时间，寻找符合条件的
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 60; j++) {
                // 计算小时数和分钟数所含有的 1，该值即为两者所亮灯的总和
                int count = count(i) + count(j);
                if (count == num) {
                    // 说明该时间满足条件
                    if (j < 10) {
                        res.add(String.valueOf(i) + ":0" + String.valueOf(j));
                    } else {
                        res.add(String.valueOf(i) + ":" + String.valueOf(j));
                    }
                }
            }
        }

        return res;
    }

    /**
     * 计算 num 的二进制中所含有的 1 的数量
     */
    private int count(int num) {
        int count = 0;
        while (num != 0) {
            count++;
            num = num & (num-1);
        }
        return count;
    }
    
}
