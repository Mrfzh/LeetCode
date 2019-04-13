public class Seven {
    public static void main(String[] args) {
        System.out.println(reverse(1534236469));
    }

    /**
     *  给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     *  注意：假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。
     *  请根据这个假设，如果反转后整数溢出那么就返回 0。
     *
     * @param x 给定的整数
     * @return 返回反转后的结果
     */
    public static int reverse(int x) {
        //如果是个位数直接返回
        if (-9 <= x && x <= 9) {
            return x;
        }

        //先求出范围
        double min = Math.pow(-2, 31);
        double max = Math.pow(2, 31) - 1;

        boolean isPositive = true;  //标志位，是否是正数
        if (x < 0) {    //如果是负数就变为正数
            isPositive = false;
            x = -x;
        }
        //将该整数转换为字符串形式
        String s = x + "";
        //该整数的位数
        int n = s.length();

        //进行反转
        StringBuilder builder = new StringBuilder();
        for (int i = n-1; i >= 0; i--) {
            builder.append(s.charAt(i));
        }
        int result; //存储结果
        try {
            result = Integer.parseInt(builder.toString());
        } catch (NumberFormatException e) {     //整数超出边界时抛出异常
            return 0;
        }

        if (!isPositive) {  //如果是负数就取负
            result = -result;
        }
        //判断边界
        if (result < min || result > max) {
            return 0;
        }

        return result;
    }
}
