package easy;

public class Question551 {
    public static void main(String[] args) {
        System.out.println(new Question551().checkRecord("PPALLL"));
    }

    /**
     * 给定一个字符串来代表一个学生的出勤记录，这个记录仅包含以下三个字符：
     * 'A' : Absent，缺勤
     * 'L' : Late，迟到
     * 'P' : Present，到场
     *
     * 如果一个学生的出勤记录中不超过一个'A'(缺勤)并且不超过两个连续的'L'(迟到),
     * 那么这个学生会被奖赏。
     *
     * 你需要根据这个学生的出勤记录判断他是否会被奖赏。
     *
     * @param s
     * @return
     */
    public boolean checkRecord(String s) {
        // 记录上一个及上上一个字符
        char last = 'N';
        char lLast = 'N';
        boolean hasA = false;
        for (char c : s.toCharArray()) {
            if (c == 'A') {
                if (hasA) {
                    return false;
                } else {
                    hasA = true;
                }
            } else if (c == 'L') {
                if (last == 'L' && lLast == 'L') {
                    return false;
                }
            }
            lLast = last;
            last = c;
        }

        return true;
    }
}
