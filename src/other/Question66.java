package other;

import java.util.Arrays;

public class Question66 {
    public static void main(String[] args) {
        int[] digits = {1, 2, 3};
        System.out.println(Arrays.toString(plusOne(digits)));
    }

    /**
     * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
     * 最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     *
     * @param digits
     * @return
     */
    private static int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i] += 1;
                break;
            } else {
                if (i == 0) {   //例如9，99，...，需要扩容
                    int[] result = new int[digits.length + 1];
                    result[0] = 1;
                    return result;
                } else {    //加1后该位为0，进位为1，继续循环
                    digits[i] = 0;
                }
            }
        }

        return digits;
    }
}
