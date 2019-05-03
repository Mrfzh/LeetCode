import java.util.LinkedHashMap;

public class Question12 {
    public static void main(String[] args) {
        System.out.println(intToRoman(1994));
        System.out.println(intToRoman(58));
        System.out.println(intToRoman(140));
    }

    /**
     * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
     *
     * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，
     * 例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。
     * 同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
     * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
     * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     *
     * 题目要求：给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
     *
     * @param num 给定的整数
     * @return 对应的罗马数字
     */
    private static String intToRoman(int num) {
        //将数字和字母一一对应，使用LinkedHashMap保证元素的顺序
        //也可以使用两个数组分别存储数字和字符，这样比用LinkedHashMap效率要高一点
        LinkedHashMap<Integer, String> hashMap = new LinkedHashMap<>();
        hashMap.put(1000, "M");
        hashMap.put(900, "CM");
        hashMap.put(500, "D");
        hashMap.put(400, "CD");
        hashMap.put(100, "C");
        hashMap.put(90, "XC");
        hashMap.put(50, "L");
        hashMap.put(40, "XL");
        hashMap.put(10, "X");
        hashMap.put(9, "IX");
        hashMap.put(5, "V");
        hashMap.put(4, "IV");
        hashMap.put(1, "I");

        StringBuilder builder = new StringBuilder();
        for (Integer key : hashMap.keySet()) {
            if (num >= key) {
                for (int i = 0; i < num / key; i++) {
                    builder.append(hashMap.get(key));
                }
                num = num % key;
            }
        }

        return builder.toString();
    }
}
