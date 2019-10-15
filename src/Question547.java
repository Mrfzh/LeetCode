import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Question547 {
    public static void main(String[] args) {
        int[][] M = {{1,1,0}, {1,1,1}, {0,1,1}};
        System.out.println(new Question547().findCircleNum(M));
    }

    /**
     * 班上有 N 名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。
     * 如果已知 A 是 B 的朋友，B 是 C 的朋友，那么我们可以认为 A 也是 C 的朋友。
     * 所谓的朋友圈，是指所有朋友的集合。
     *
     * 给定一个 N * N 的矩阵 M，表示班级中学生之间的朋友关系。
     * 如果M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，否则为不知道。
     * 你必须输出所有学生中的已知的朋友圈总数。
     *
     * 注意：
     * N 在[1,200]的范围内。
     * 对于所有学生，有M[i][i] = 1。
     * 如果有M[i][j] = 1，则有M[j][i] = 1。
     *
     * @param M
     * @return
     */
    public int findCircleNum(int[][] M) {
        int num = M.length;     // 人数
        int listNum = num;  // 集合（朋友圈）个数
        // key 表示同学，value 表示该同学所属的朋友圈（集合）
        HashMap<Integer, List<Integer>> hashMap = new HashMap<>();
        // 一开始假设每个人都不认识，有 num 个集合（即 num 个朋友圈）
        for (int i = 0; i < num; i++) {
            List<Integer> list = new ArrayList<>();
            list.add(i);
            hashMap.put(i, list);
        }

        // 依次遍历朋友关系
        boolean isFinished = false;
        for (int i = 0; i < num && !isFinished; i++) {
            for (int j = i+1; j < num; j++) {
                if (M[i][j] == 1) {
                    // 将 i 和 j 所属的朋友圈合并，朋友圈个数减一
                    List<Integer> iList = hashMap.get(i);
                    List<Integer> jList = hashMap.get(j);
                    // 如果 i 和 j 已经是一个朋友圈了，就不用管它
                    if (iList == jList) {
                        break;
                    }
                    iList.addAll(jList);
                    hashMap.put(i, iList);
                    hashMap.put(j, iList);
                    listNum--;
                    if (listNum == 1) {
                        // 提取结束循环
                        isFinished = true;
                        break;
                    }
                }
            }
        }

        return listNum;
    }
}
