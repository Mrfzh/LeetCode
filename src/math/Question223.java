package math;

public class Question223 {
    public static void main(String[] args) {
        System.out.println(new Question223().computeArea(
                -3, 0, 3, 4, 0, -1, 9, 2));
    }

    /**
     * 在二维平面上计算出两个由直线构成的矩形重叠后形成的总面积。
     *
     * 每个矩形由其左下顶点和右上顶点坐标表示，如图所示。
     *
     * 纯数学问题
     *
     * @param A
     * @param B
     * @param C
     * @param D
     * @param E
     * @param F
     * @param G
     * @param H
     * @return
     */
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        // 假设两矩形分别为 r1 和 r2
        // 先计算 r1 和 r2 的面积
        int areaR1 = (C - A) * (D - B);
        int areaR2 = (G - E) * (H - F);
        // 计算重叠部分的面积
        // 分别计算 x 和 y 的重叠长度
        // long 防止相减时溢出
        long temp = (long)Math.min(C, G) - (long)Math.max(A, E);
        int x = temp < 0 ? 0 : (int)temp;
        temp = (long)Math.min(D, H) - (long)Math.max(B, F);
        int y = temp < 0 ? 0 : (int)temp;
        // 重叠部分面积
        int areaR12 = x * y;

        return areaR1 + areaR2 - areaR12;
    }
}
