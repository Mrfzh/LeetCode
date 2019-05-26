package other;

public class Question6 {
    public static void main(String[] args) {
        System.out.println(convert_violence("LEETCODEISHIRING", 4));
    }

    /**
     * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
     *
     * @param s 给定字符串
     * @param numRows 给定行数
     * @return 从左往右逐行读取，产生出的一个新的字符串
     */
    private static String convert_violence(String s, int numRows) {
        //判断行数
        if (numRows <= 1) {
            return s;
        }

        //创建一个二维数组a[i][j],其中i = numRows

        //先求出j
        int n = s.length();     //字符串长度
        int i1 = numRows + numRows - 2;     //一组字符的字符数
        int i2 = numRows - 1;         //一组字符所占列数
        int i3 = n % i1;               //最后剩余字符数
        int j = (n / i1) * i2 + (i3 / numRows) + (i3 % numRows);

        char[][] a = new char[numRows][j];
        System.out.println(a[0][0]);
        int sc = 0;     //记录当前存到的字符位标

        //先将字符串按Z字形存好数组
        for (int l = 0; l < j; l++) {   //列
            for (int m = 0; m < numRows; m++) {     //行
                if (l % i2 == 0) {  //该列为Z的一横（倒过来就是一竖），整列都存字符
                    if (sc < s.length()) {
                        a[m][l] = s.charAt(sc);
                        sc++;
                    }
                } else {
                    if (m == numRows-1-(l % i2)) {  //判断该行是否需要存字符
                        if (sc < s.length()) {
                            a[m][l] = s.charAt(sc);
                            sc++;
                        }
                    }
                }
            }
        }

        //再根据数组拼接出新字符
        StringBuilder builder = new StringBuilder();
        for (int m = 0; m < numRows; m++) {
            for (int l = 0; l < j; l++) {
                if (a[m][l] != '\u0000') {  //判断是否有存字符
                    builder.append(a[m][l]);
                }
            }
        }

        return builder.toString();

    }
}
