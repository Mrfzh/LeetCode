package bit;

public class Question405 {
    public static void main(String[] args) {
        System.out.println(new Question405().toHex_better(26));
        System.out.println(new Question405().toHex_better(-26));
    }

    /**
     * 给定一个整数，编写一个算法将这个数转换为十六进制数。对于负整数，我们通常使用 补码运算 方法。
     *
     * 注意:
     * 1. 十六进制中所有字母(a-f)都必须是小写。
     * 2. 十六进制字符串中不能包含多余的前导零。如果要转化的数为0，那么以单个字符'0'来表示；
     * 对于其他情况，十六进制字符串中的第一个字符将不会是0字符。
     *
     * 位运算 
     *
     * @param num
     * @return
     */
    public String toHex(int num) {
        char[] chars = {'0','1','2','3','4','5', '6','7','8','9','a','b','c','d','e','f'};
        StringBuilder res = new StringBuilder();
        if (num == 0) {
            return "0";
        } else if (num > 0) {
            while (num != 0) {
                int curr = 15 & num;
                res.insert(0, chars[curr]);
                num = num >> 4;
            }
            return res.toString();
        } else {
            for (int i = 0; i < 8; i++) {
                int curr = 15 & num;
                res.insert(0, chars[curr]);
                num = num >> 4;
            }
            return res.toString();
        }
    }

    /**
     * 优化：使用 >>>（无符号右移），无论是负数还是正数，右移后高位补 0，连同符号位移动
     */
    public String toHex_better(int num) {
        if (num == 0) {
            return "0";
        }

        char[] chars = {'0','1','2','3','4','5', '6','7','8','9','a','b','c','d','e','f'};
        StringBuilder res = new StringBuilder();
        while (num != 0) {
            int curr = 15 & num;    // 后四位的值
            res.insert(0, chars[curr]); // 将后四位的值转化为十六进制，并插入到开头
            num = num >>> 4; // 无符号右移四位
        }
        return res.toString();
    }
}
