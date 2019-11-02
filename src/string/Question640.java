package string;

public class Question640 {
    public static void main(String[] args) {
        System.out.println(new Question640().solveEquation("x=x"));
        System.out.println(new Question640().solveEquation("2x=x"));
        System.out.println(new Question640().solveEquation("2x+3x-6x=x+2"));
        System.out.println(new Question640().solveEquation("x=x+2"));
    }

    /**
     * 求解一个给定的方程，将x以字符串"x=#value"的形式返回。
     * 该方程仅包含'+'，' - '操作，变量 x 和其对应系数。
     *
     * 如果方程没有解，请返回“No solution”。
     * 如果方程有无限解，则返回“Infinite solutions”。
     * 如果方程中只有一个解，要保证返回值 x 是一个整数。
     *
     * 处理字符串
     *
     * @param equation
     * @return
     */
    public String solveEquation(String equation) {
        String[] strings = equation.split("=");
        int[] r1 = getAB(strings[0]);
        int[] r2 = getAB(strings[1]);
        // 最终的 ax = b
        int a = r1[0] - r2[0];
        int b = -r1[1] + r2[1];
        if (a == 0 && b == 0) {
            return "Infinite solutions";
        } else if (a == 0) {
            return "No solution";
        } else {
            return "x=" + String.valueOf(b / a);
        }
    }

    /**
     * 根据给定的字符串，转换为 ax + b 的格式
     * 返回数组 res 中，res[0] = a, res[1] = b
     */
    private int[] getAB(String str) {
        int[] res = new int[2];
        int num;
        int obj;    // 0 代表 x 的系数，1 代表常数
        int start = 0;
        int end = 0;
        while (end < str.length()) {
            if (end == str.length()-1 || str.charAt(end + 1) == '+' ||
                    str.charAt(end + 1) == '-') {
                // start 到 end 间为一组
                if (str.charAt(end) == 'x'){
                    obj = 0;
                    // 注意 x 前面若没有系数，说明省略了系数，系数为 1，并且要根据符号看正负
                    String s = str.substring(start, end);
                    num = (s.equals("") || s.equals("+"))? 1 : s.equals("-")? -1 : Integer.parseInt(s);
                } else {
                    obj = 1;
                    num = Integer.parseInt(str.substring(start, end + 1));
                }
                // 更新 res
                if (obj == 0) {
                    res[0] += num;
                } else {
                    res[1] += num;
                }
                // 更新指针
                start = end + 1;
                end = end + 1;
            } else {
                end++;
            }
        }

        return res;
    }
}
