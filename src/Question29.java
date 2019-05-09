public class Question29 {
    public static void main(String[] args) {
        System.out.println(divide(10, 3));
        System.out.println(divide(7, -3));
        System.out.println(divide(0, 10));
        System.out.println(divide(-2147483648 ,-2));
        System.out.println(divide(100, 3));
        System.out.println(divide(10, 3));
        System.out.println(divide(-10, -2));

        //INT_MAX = 2147483647, INT_MIN = -2147483648
    }

    /**
     * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
     * 返回被除数 dividend 除以除数 divisor 得到的商。
     *
     * 说明
     * 1. 被除数和除数均为 32 位有符号整数。
     * 2. 除数不为 0。
     * 3. 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
     *
     * @param dividend
     * @param divisor
     * @return
     */
    private static int divide(int dividend, int divisor) {
        int flag;  //0代表结果为正，1代表结果为负
        if (dividend == 0) {
            return 0;
        } else if ((dividend < 0 && divisor < 0) || (dividend > 0 && divisor > 0)) {
            flag = 0;
        } else {
            flag = 1;
        }

        //先处理溢出
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        } else if (dividend == Integer.MIN_VALUE && divisor == 1) {
            return Integer.MIN_VALUE;
        }

        //两个数都取负数
        if (dividend > 0) {
            dividend = -dividend;
        }
        if (divisor > 0) {
            divisor = -divisor;
        }

        if (dividend > divisor) {
            return 0;
        } else if (dividend == divisor) {
            return (flag == 0)? 1 : -1;
        } else {
            int result = 0;
            while (dividend < divisor) {    //两者相等时需另外计算

                //根据：左移n位 = 乘2^n，右移n位 = 除2^n
                //假设被除数为A，除数为B，即求A/B，（A，B都为负数）
                //A先除以2^n（n尽可能大），使得右移n为后的A <= B。然后A + 2^n，结果加上2^n。
                //重复此过程，直到A大于等于B。

                int i = 31;
                if (dividend == Integer.MIN_VALUE) {
                    while (dividend >> i > divisor) {
                        i--;
                    }
                } else {
                    while (((dividend >> i) + 1) > divisor) {   //由于负数右移的问题，这里要加上1
                        i--;
                    }
                }
                dividend += -divisor << i;
                result += 1 << i;
            }
            if (dividend == divisor) {  //最后两数相等的话再加上1
                result++;
            }
            return (flag == 0)? result : -result;
        }
    }
}
