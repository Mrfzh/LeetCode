package queue;

import java.util.LinkedList;

public class Question481 {
    public static void main(String[] args) {
        System.out.println(new Question481().magicalString(5));
    }

    /**
     * 神奇的字符串 S 只包含 '1' 和 '2'，并遵守以下规则：
     * 字符串 S 是神奇的，因为串联字符 '1' 和 '2' 的连续出现次数会生成字符串 S 本身。
     *
     * 字符串 S 的前几个元素如下：S = “1221121221221121122 ......”
     * 如果我们将 S 中连续的 1 和 2 进行分组，它将变成：
     * 1 22 11 2 1 22 1 22 11 2 11 22 ......
     * 并且每个组中 '1' 或 '2' 的出现次数分别是：
     * 1 2 2 1 1 2 1 2 2 1 2 2 ......
     * 你可以看到上面的出现次数就是 S 本身。
     *
     * 给定一个整数 N 作为输入，返回神奇字符串 S 中前 N 个数字中的 '1' 的数目。
     *
     * 队列
     *
     * @param n
     * @return
     */
    public int magicalString(int n) {
        if (n <= 0) {
            return 0;
        } else if (n <= 3) {
            return 1;
        }

        // 队列存储生成的字符串，队头元素即为下一个字符（1 或 2）的数量
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(2);
        int next = 1;   // 下一个字符
        int oneCount = 1;   // 1 的数目
        int index = 3;      // 下一个字符的索引
        while (index < n) {
            int nextCount = queue.remove(); // 下一字符的数量
            for (int i = 0; i < nextCount && index < n; i++) {
                if (next == 1) {
                    oneCount++;
                }
                queue.add(next);
                index++;
            }
            next = (next == 1)? 2 : 1;
        }

        return oneCount;
    }
}
