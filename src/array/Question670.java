package array;

public class Question670 {
    public static void main(String[] args) {
        System.out.println(new Question670().maximumSwap(2736));
        System.out.println(new Question670().maximumSwap(9973));
    }

    /**
     * 给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。
     *
     * 给定数字的范围是 [0, 10^8]
     *
     * 将数字转换为字符数组来做，本身不难，注意细节即可
     *
     * @param num
     * @return
     */
    public int maximumSwap(int num) {
        if (num <= 10) {
            return num;
        }

        String s = String.valueOf(num);
        char[] chars = s.toCharArray();
        // 依次遍历
        for (int i = 0; i < chars.length - 1; i++) {
            // 在后面寻找比 chars[i] 大的最大值
            /* 注意：该值可能有多个，多个时取最后一个 */
            char max = chars[i];
            int maxIndex = -1;
            for (int j = i + 1; j < chars.length; j++) {
                if (chars[j] > max) {
                    max = chars[j];
                    maxIndex = j;
                } else if (chars[j] == max && max != chars[i]) {
                    maxIndex = j;
                }
            }
            // 交换 i 和 maxIndex 位置的元素
            if (maxIndex != -1) {
                char temp = chars[i];
                chars[i] = chars[maxIndex];
                chars[maxIndex] = temp;
                break;
            }
        }

        return Integer.parseInt(String.valueOf(chars));
    }
}
