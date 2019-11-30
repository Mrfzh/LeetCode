package easy;

public class Question744 {
    public static void main(String[] args) {
        char[] letters = {'c', 'f', 'j'};
        System.out.println(new Question744().nextGreatestLetter(letters, 'c'));
        System.out.println(new Question744().nextGreatestLetter(letters, 'd'));
        System.out.println(new Question744().nextGreatestLetter(letters, 'g'));
        System.out.println(new Question744().nextGreatestLetter(letters, 'j'));
        System.out.println(new Question744().nextGreatestLetter(letters, 'k'));
    }

    /**
     * 给定一个只包含小写字母的有序数组letters 和一个目标字母 target，
     * 寻找有序数组里面比目标字母大的最小字母。
     *
     * 数组里字母的顺序是循环的。举个例子，
     * 如果目标字母target = 'z' 并且有序数组为 letters = ['a', 'b']，则答案返回 'a'。
     *
     * 注:
     * letters长度范围在[2, 10000]区间内。
     * letters 仅由小写字母组成，最少包含两个不同的字母。
     * 目标字母target 是一个小写字母。
     *
     * @param letters
     * @param target
     * @return
     */
    public char nextGreatestLetter(char[] letters, char target) {
        char res = 'a';
        int min = Integer.MAX_VALUE;
        for (char letter : letters) {
            if (letter == target) { // 不能和目标相同
                continue;
            }
            int len;
            if (letter < target) {
                len = letter - target + 26;
            } else {
                len = letter - target;
            }
            if (len == 1) {
                return letter;  // 提前结束
            }
            if (len < min) {
                min = len;
                res = letter;
            }
        }

        return res;
    }
}
