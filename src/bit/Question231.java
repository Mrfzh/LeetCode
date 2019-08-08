package bit;

public class Question231 {
    public static void main(String[] args) {
        System.out.println(new Question231().isPowerOfTwo(1));
        System.out.println(new Question231().isPowerOfTwo(16));
        System.out.println(new Question231().isPowerOfTwo(218));
    }

    /**
     * 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
     *
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & n - 1) == 0;
    }
}
