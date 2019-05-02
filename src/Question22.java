import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Question22 {
    public static void main(String[] args) {
        System.out.println(generateParenthesis(4));
    }

    /**
     * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
     *
     * 递归实现
     *
     * @param n 括号的对数
     * @return 所有可能并且有效的括号组合
     */
    private static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        if (n < 1) {
            result.add("");
            return result;
        }
        if (n == 1) {
            result.add("()");
            return result;
        }

        List<String> last = generateParenthesis(n-1);
        HashSet<String> hashSet = new HashSet<>();
        StringBuilder builder;
        String current;
        String insert = "()";
        for (int i = 0; i < last.size(); i++) {
            current = last.get(i);
            for (int j = 0; j <= current.length(); j++) {   //成对地插入括号，HashSet去重
                builder = new StringBuilder(current);
                hashSet.add(builder.insert(j, insert).toString());
            }
        }

        return new ArrayList<>(hashSet);
    }
}
