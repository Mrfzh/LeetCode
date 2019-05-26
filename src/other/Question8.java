package other;

import java.util.LinkedHashMap;

public class Question8 {
    public static void main(String[] args) {
        System.out.println(myAtoi("-91283472332"));

    }

    /**
     * 按照一定规则，将一个字符串转换为一个整数
     *
     * @param str 需要转换的字符串
     * @return 转换后的整数
     */
    private static int myAtoi(String str) {
        if (str == null) {
            return 0;
        }

        //先去除掉开头的空格
        String newStr = str.trim();

        //判断出不需要转换的字符(返回0)
        //1. 不是以+,-,或者数字开头的
        //2. 字符串为空
        //3. 以+,-开头，但后面没有数字
        if (newStr.equals("") || newStr.equals("+") || newStr.equals("-")) {
            return 0;
        }
        char c = newStr.charAt(0);  //去除空格后的首字符
        if (!(c == '+' || c == '-' || Character.isDigit(c))) {
            return 0;
        }

        int result;     //存储结果
        boolean isPosition = true;  //判断是否为正数
        boolean hasNum = false;     //判断是否有数字

        //找出连续的数字
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < newStr.length(); i++) {
            if (i == 0) {   //单独处理
                if (c == '-') {
                    isPosition = false;
                } else if (c == '+') {
                } else {
                    builder.append(c);
                }
            } else {
                if (Character.isDigit(newStr.charAt(i))) {
                    builder.append(newStr.charAt(i));
                    hasNum = true;
                } else {
                    break;
                }
            }
        }

        try {
            result = Integer.parseInt(builder.toString());
        } catch (NumberFormatException e) { //此时数值超出了范围
            if (!hasNum) {  //如果没有数字，则返回0，例如"+++"
                return 0;
            } else {
                if (isPosition) {
                    return Integer.MAX_VALUE;
                } else {
                    return Integer.MIN_VALUE;
                }
            }
        }

        if (!isPosition) {  //取负号
            result = -result;
        }

        return result;
    }
}
