package skill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Question406 {
    public static void main(String[] args) {
        int[][] people = {{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
        int[][] newP = new Question406().reconstructQueue(people);
        for (int[] p : newP) {
            System.out.println(Arrays.toString(p));
        }
    }

    /**
     * 假设有打乱顺序的一群人站成一个队列。每个人由一个整数对(h, k)表示，
     * 其中 h 是这个人的身高，k 是排在这个人前面且身高大于或等于h的人数。
     * 编写一个算法来重建这个队列。
     *
     * @param people
     * @return
     */
    public int[][] reconstructQueue(int[][] people) {
        if (people.length == 0) {
            return new int[0][];
        }

        // 对 people 数组排序，按 h 降序排列，h 相等时按 k 升序排列
        Arrays.sort(people, (o1, o2) -> (o1[0] == o2[0])? o1[1] - o2[1] : o2[0] - o1[0]);

        // 重新排列 people 数组，按照 k 的值插入到相应位置
        List<int[]> list = new ArrayList<>();
        for (int[] p : people) {
            list.add(p[1], p);
        }

        return list.toArray(new int[list.size()][]);
    }
}
