package double_pointer;

import java.util.Arrays;

public class Question443 {
    public static void main(String[] args) {
        char[] chars = {'a', 'a', 'b', 'c', 'c', 'c'};
        System.out.println(new Question443().compress(chars));
        System.out.println(Arrays.toString(chars));
    }

    /**
     * 给定一组字符，使用原地算法将其压缩。压缩后的长度必须始终小于或等于原数组长度。
     *
     * 在完成原地修改输入数组后，返回数组的新长度。
     *
     * 双指针
     *
     * @param chars
     * @return
     */
    public int compress(char[] chars) {
        // 判空
        if (chars.length == 0) {
            return 0;
        }

        // read 指针读取原数组的元素，write 指针指向当前压缩要写的位置
        // 每次读一段连续的字符，先读后写
        int read = 1;
        int write = 0;
        char lastChar = chars[0];   // 上一读取的字符
        int lastCharNum = 1;        // 上一读取字符的连续个数
        while (read <= chars.length) {
            // 加上 read == chars.length 是为了写入最后一个数据
            if (read == chars.length || chars[read] != lastChar) {
                // 当前字符不等于上一字符，将上一字符压缩写入
                chars[write] = lastChar;
                write++;
                if (lastCharNum > 1) {
                    // 注意：假如 lastCharNum 为 12，那么应该写入 '1','2'两位
                    // 所以这里先转换为 String，再转为 char
                    String str = String.valueOf(lastCharNum);
                    for (int i = 0; i < str.length(); i++) {
                        chars[write] = str.charAt(i);
                        write++;
                    }
                }
                // 更新相关变量
                if (read < chars.length) {
                    lastChar = chars[read];
                }
                lastCharNum = 1;
            } else {
                lastCharNum++;
            }
            read++;
        }

        return write;
    }
}
