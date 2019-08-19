package other;

public class Question299 {
    public static void main(String[] args) {
        System.out.println(new Question299().getHint("1807", "7810"));
        System.out.println(new Question299().getHint("1123", "0111"));
    }

    /**
     * 你正在和你的朋友玩 猜数字（Bulls and Cows）游戏：你写下一个数字让你的朋友猜。
     * 每次他猜测后，你给他一个提示，告诉他有多少位数字和确切位置都猜对了（称为“Bulls”, 公牛），
     * 有多少位数字猜对了但是位置不对（称为“Cows”, 奶牛）。
     * 你的朋友将会根据提示继续猜，直到猜出秘密数字。
     *
     * 请写出一个根据秘密数字和朋友的猜测数返回提示的函数，用 A 表示公牛，用 B 表示奶牛。
     * 请注意秘密数字和朋友的猜测数都可能含有重复数字。
     *
     * 你可以假设秘密数字和朋友的猜测数都只包含数字，并且它们的长度永远相等。
     *
     * @param secret
     * @param guess
     * @return
     */
    public String getHint(String secret, String guess) {
        int countA = 0;
        int countB = 0;
        // 记录各数字出现次数（不包括公牛数）
        int[] secretRecord = new int[10];
        int[] guessRecord = new int[10];
        for (int i = 0; i < secret.length(); i++) {
            int currSecret = secret.charAt(i) - '0';
            int currGuess = guess.charAt(i) - '0';
            if (currSecret == currGuess) {
                countA++;
            } else {
                secretRecord[currSecret]++;
                guessRecord[currGuess]++;
            }
        }
        // 统计 B
        for (int i = 0; i < 10; i++) {
            countB += Math.min(secretRecord[i], guessRecord[i]);
        }

        return countA + "A" + countB + "B";
    }
}
