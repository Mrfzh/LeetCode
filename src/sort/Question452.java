package sort;

import java.util.Arrays;

public class Question452 {
    public static void main(String[] args) {
        int[][] points = {{11702305,96123230},{37477084,64813411},{72660336,131786841},
                {5750846,38372575},{661313,34587170},{41616124,125970019},{39819582,40920127},
                {98898814,147132181},{10515434,96505798},{74344043,134657793}};
        System.out.println(new Question452().findMinArrowShots(points));
    }

    /**
     * 在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。
     * 由于它是水平的，所以y坐标并不重要，因此只要知道开始和结束的x坐标就足够了。
     * 开始坐标总是小于结束坐标。平面内最多存在10^4个气球。
     *
     * 一支弓箭可以沿着x轴从不同点完全垂直地射出。
     * 在坐标x处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend，
     * 且满足  xstart ≤ x ≤ xend，则该气球会被引爆。可以射出的弓箭的数量没有限制。
     * 弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。
     *
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        int count = 0;

        // 先排序，按照 end 的大小升序排列
        Arrays.sort(points, (o1, o2) -> o1[1] - o2[1]);

        // 每次射向第一个未引爆气球的 end
        // curr 指向当前第一个未引爆的气球
        int curr = 0;
        while (curr < points.length) {
            count++;
            int x = points[curr][1];    // 当前射入的 x 坐标
            // start <= x 的气球会被这支箭引爆
            for (int i = curr; i < points.length; i++) {
                if (points[i][0] <= x) {
                    // 当前气球被引爆，更新 curr 指针
                    curr++;
                } else {
                    // 当前气球未被引爆，退出循环，射入下一支箭
                    break;
                }
            }
        }

        return count;
    }
}
