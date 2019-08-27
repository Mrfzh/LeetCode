package double_pointer;

public class Question345 {
    public static void main(String[] args) {
        System.out.println(new Question345().reverseVowels("hello"));
        System.out.println(new Question345().reverseVowels("leetcode"));
    }

    /**
     * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
     *
     * 双指针法
     *
     * @param s
     * @return
     */
    public String reverseVowels(String s) {
        char[] chars = s.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        while (left < right) {
            while (!isValid(chars[left]) && left < right) {
                left++;
            }
            while (!isValid(chars[right]) && left < right) {
                right--;
            }

            if (left < right) {
                // 交换两元音字母
                char temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;
            }
            left++;
            right--;
        }

        return String.valueOf(chars);
    }

    /**
     * 判断字符是否为元音字母
     */
    private boolean isValid(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
            || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }
}
