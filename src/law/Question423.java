package law;

public class Question423 {
    public static void main(String[] args) {
        System.out.println(new Question423().originalDigits("fviefuro"));
    }

    /**
     * 给定一个非空字符串，其中包含字母顺序打乱的英文单词表示的数字0-9。按升序输出原始的数字。
     *
     * 注意:
     * 1. 输入只包含小写英文字母。
     * 2. 输入保证合法并可以转换为原始的数字，这意味着像 "abc" 或 "zerone" 的输入是不允许的。
     * 3. 输入字符串的长度小于 50,000。
     *
     * 找规律
     *
     * @param s
     * @return
     */
    public String originalDigits(String s) {
        // 记录字符串中各字母的数量
        int[] buckets = new int[26];
        for (char c : s.toCharArray()) {
            buckets[c - 'a']++;
        }

        // 记录各数字出现的次数
        int[] nums = new int[10];
        /**
         * 规律如下：
         * zero	    // 特有的 z
         * one 	    // 确定 zero, two, four 后，特有的 o
         * two		// 特有的 w
         * three	// 确定 eight 后，特有的 h
         * four	    // 特有的 u
         * five	    // 确定 four 后，特有的 f
         * six		// 特有的 x
         * seven	// 确定 five 后，特有的 v
         * eight	// 特有的 g
         * nine	    // 确定 five, six, eight 后特有的 i
         */
        nums[0] = buckets['z' - 'a'];
        nums[2] = buckets['w' - 'a'];
        nums[4] = buckets['u' - 'a'];
        nums[5] = buckets['f' - 'a'] - nums[4];
        nums[7] = buckets['v' - 'a'] - nums[5];
        nums[6] = buckets['x' - 'a'];
        nums[8] = buckets['g' - 'a'];
        nums[3] = buckets['h' - 'a'] - nums[8];
        nums[1] = buckets['o' - 'a'] - nums[0] - nums[2] - nums[4];
        nums[9] = buckets['i' - 'a'] - nums[5] - nums[6] - nums[8];

        // 根据各数字出现的数量构造字符串
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int num = nums[i];
            for (int j = 0; j < num; j++) {
                builder.append(i);
            }
        }

        return builder.toString();
    }
}
