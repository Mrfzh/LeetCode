package other;

public class Question165 {
    public static void main(String[] args) {
        System.out.println(new Question165().compareVersion("0.1", "1.1"));
        System.out.println(new Question165().compareVersion("1.0.1", "1"));
        System.out.println(new Question165().compareVersion("7.5.2.4", "7.5.3"));
        System.out.println(new Question165().compareVersion("1.01", "1.001"));
        System.out.println(new Question165().compareVersion("1.0", "1.0.0"));
        System.out.println(new Question165().compareVersion("0.9.9.9.9.9.9.9.9.9.9.9.9", "1.0"));
    }

    /**
     * 比较两个版本号 version1 和 version2。
     * 如果 version1 > version2 返回 1，如果 version1 < version2 返回 -1， 除此之外返回 0。
     *
     * 你可以假设版本字符串非空，并且只包含数字和 . 字符。 . 字符不代表小数点，而是用于分隔数字序列。
     * 你可以假设版本号的每一级的默认修订版号为 0。例如，版本号 3.4 的第一级（大版本）
     * 和第二级（小版本）修订号分别为 3 和 4。其第三级和第四级修订号均为 0。
     *
     * @param version1
     * @param version2
     * @return
     */
    public int compareVersion(String version1, String version2) {
        String[] strs1 = version1.split("\\.");
        String[] strs2 = version2.split("\\.");
        int max = Math.max(strs1.length, strs2.length);
        int[] v1 = new int[max];
        int[] v2 = new int[max];

        for (int i = 0; i < strs1.length; i++) {
            v1[i] = Integer.parseInt(strs1[i]);
        }
        for (int i = 0; i < strs2.length; i++) {
            v2[i] = Integer.parseInt(strs2[i]);
        }

        for (int i = 0; i < max; i++) {
            if (v1[i] > v2[i]) {
                return 1;
            } else if (v1[i] < v2[i]) {
                return -1;
            }
        }
        return 0;
    }
}
