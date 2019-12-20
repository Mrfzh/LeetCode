package skill;

public class Question781 {
    public static void main(String[] args) {
        int[] answers = {1, 1, 2};
        System.out.println(new Question781().numRabbits(answers));
    }

    /**
     * 森林中，每个兔子都有颜色。其中一些兔子（可能是全部）告诉你还有多少其他的兔子和自己有相同的颜色。
     * 我们将这些回答放在 answers 数组里。
     *
     * 返回森林中兔子的最少数量。
     *
     * 说明:
     * answers 的长度最大为1000。
     * answers[i] 是在 [0, 999] 范围内的整数。
     *
     * @param answers
     * @return
     */
    public int numRabbits(int[] answers) {
        if (answers.length == 0) {
            return 0;
        }
        // 记录每个数出现的次数, nums[a] 表示 a 出现的次数
        int max = Integer.MIN_VALUE;
        for (int answer : answers) {
            max = Math.max(max, answer);
        }
        int[] nums = new int[max + 1];
        for (int answer : answers) {
            nums[answer]++;
        }
        // 计算 res
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            int count = nums[i];
            int num = count == 0? 0 : (count - 1) / (i + 1) + 1;
            res += (i+1) * num;
        }

        return res;
    }
}
