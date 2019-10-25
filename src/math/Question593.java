package math;

public class Question593 {
    public static void main(String[] args) {
        int[] p1 = {0,0};
        int[] p2 = {1,1};
        int[] p3 = {1,0};
        int[] p4 = {0,1};
        System.out.println(new Question593().validSquare(p1, p2, p3, p4));
    }

    /**
     * 给定二维空间中四点的坐标，返回四点是否可以构造一个正方形。
     * 一个点的坐标（x，y）由一个有两个整数的整数数组表示。
     *
     * 注意:
     * 所有输入整数都在 [-10000，10000] 范围内。
     * 一个有效的正方形有四个等长的正长和四个等角（90度角）。
     * 输入点没有顺序。
     *
     * @param p1
     * @param p2
     * @param p3
     * @param p4
     * @return
     */
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        // 如果是正方形需要满足以下两个条件
        // （1）四条边都相等
        // （2）对于 p1p2, p1p3, p1p4 这三条边，有其中两条边的平方之和等于另一条边的平方
        long p12 = cal(p1, p2);
        long p13 = cal(p1, p3);
        long p14 = cal(p1, p4);
        long p42 = cal(p4, p2);
        long p43 = cal(p4, p3);
        long p32 = cal(p3, p2);
        if (p12 == p13 && p13 != p14 && (p12 + p13) == p14) {
            return p42 == p43 && p42 == p12;
        } else if (p12 == p14 && p13 != p14 && (p12 + p14) == p13) {
            return p43 == p32 && p43 == p12;
        } else if (p13 == p14 && p12 != p13 && (p14 + p13) == p12) {
            return p42 == p32 && p42 == p13;
        }

        return false;
    }

    /**
     * 求 ab 边距离的平方
     * 依据两点间距离公式：|ab|^2 = (x1 - x2)^2 + (y1 - y2)^2，其中 a(x1, y1), b(x2, y2)
     */
    private long cal(int[] a, int[] b) {
        long x1 = a[0];
        long y1 = a[1];
        long x2 = b[0];
        long y2 = b[1];

        return (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2);
    }
}
