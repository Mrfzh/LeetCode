package easy;

import java.util.ArrayList;
import java.util.List;

public class Question482 {
    public static void main(String[] args) {
        System.out.println(new Question482().licenseKeyFormatting("2-5g-3-J", 2));
    }

    /**
     * 给定一个密钥字符串S，只包含字母，数字以及 '-'（破折号）。
     * N 个 '-' 将字符串分成了 N+1 组。给定一个数字 K，重新格式化字符串，除了第一个分组以外，
     * 每个分组要包含 K 个字符，第一个分组至少要包含 1 个字符。
     * 两个分组之间用 '-'（破折号）隔开，并且将所有的小写字母转换为大写字母。
     *
     * 给定非空字符串 S 和数字 K，按照上面描述的规则进行格式化。
     *
     * @param S
     * @param K
     * @return
     */
    public String licenseKeyFormatting(String S, int K) {
        S = S.toUpperCase();
        List<Character> list = new ArrayList<>();
        // 先将不是破折号的字符存入 list
        for (char c : S.toCharArray()) {
            if (c != '-') {
                list.add(c);
            }
        }
        // 计算第一个组字符的个数
        int firstCount = list.size() % K;
        if (firstCount == 0 && list.size() != 0) {
            firstCount = K;
        }
        // 先加入第一组
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < firstCount; i++) {
            builder.append(list.get(i));
        }
        // 再加入之后的组
        int index = firstCount;
        while (index < list.size()){
            builder.append('-');
            for (int j = 0; j < K; j++) {
                builder.append(list.get(index));
                index++;
            }
        }

        return builder.toString();
    }
}
