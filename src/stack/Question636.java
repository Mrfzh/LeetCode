package stack;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Question636 {
    public static void main(String[] args) {
        List<String> logs = Arrays.asList("0:start:0","0:start:2","0:end:5","0:start:6","0:end:6","0:end:7");
        System.out.println(Arrays.toString(new Question636().exclusiveTime(1, logs)));
    }

    /**
     * 给出一个非抢占单线程CPU的 n 个函数运行日志，找到函数的独占时间。
     * 每个函数都有一个唯一的 Id，从 0 到 n-1，函数可能会递归调用或者被其他函数调用。
     * 日志是具有以下格式的字符串：function_id：start_or_end：timestamp。
     * 例如："0:start:0" 表示函数 0 从 0 时刻开始运行。"0:end:0" 表示函数 0 在 0 时刻结束。
     *
     * 函数的独占时间定义是在该方法中花费的时间，调用其他函数花费的时间不算该函数的独占时间。
     * 你需要根据函数的 Id 有序地返回每个函数的独占时间。
     *
     * 栈
     *
     * @param n
     * @param logs
     * @return
     */
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];

        Stack<Integer> stack = new Stack<>();   // 位于栈顶的是当前运行的函数
        int lastTime = 0;
        String lastOp = "";
        for (String log : logs) {
            String[] content = log.split(":");
            int id = Integer.parseInt(content[0]);
            String op = content[1];
            int time = Integer.parseInt(content[2]);
            // 增加当前运行函数的时间
            if (!stack.isEmpty()) {
                int curr = stack.peek();
                // 关键是要根据操作来判断增加的时间
                if (lastOp.equals("start") && op.equals("end")) {
                    res[curr] += time - lastTime + 1;
                } else if (lastOp.equals("end") && op.equals("start")) {
                    res[curr] += time - lastTime - 1;
                } else {
                    res[curr] += time - lastTime;
                }
            }
            // 更新栈顶
            if (op.equals("start")) {
                stack.add(id);
            } else {
                stack.pop();
            }
            // 更新记录
            lastTime = time;
            lastOp = op;
        }

        return res;
    }
}
