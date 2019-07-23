package other;

public class Question171 {
    public static void main(String[] args) {
        System.out.println(new Question171().titleToNumber("A"));
        System.out.println(new Question171().titleToNumber("AB"));
        System.out.println(new Question171().titleToNumber("ZY"));
    }

    /**
     * 给定一个Excel表格中的列名称，返回其相应的列序号。
     * 例如：
     *     A -> 1
     *     B -> 2
     *     C -> 3
     *     ...
     *     Z -> 26
     *     AA -> 27
     *     AB -> 28
     *     ...
     *
     * @param s
     * @return
     */
    public int titleToNumber(String s) {
        s = s.trim();
        if (s.equals("")) {
            return 0;
        }

        int[] a = new int[s.length()];  // a[x] 表示 26^x
        for (int i = 0; i < s.length(); i++) {
            a[i] = (int) Math.pow(26, i);
        }

        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            int curr = s.charAt(s.length() - i - 1) - 64;
            res += curr * a[i];
        }

        return res;
    }
}
