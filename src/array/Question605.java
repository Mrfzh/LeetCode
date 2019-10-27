package array;

public class Question605 {
    public static void main(String[] args) {
        int[] flowerbed = {1,0,0,0,1};
        System.out.println(new Question605().canPlaceFlowers(flowerbed, 2));
    }

    /**
     * 假设你有一个很长的花坛，一部分地块种植了花，另一部分却没有。
     * 可是，花卉不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
     *
     * 给定一个花坛（表示为一个数组包含0和1，其中0表示没种植花，1表示种植了花），和一个数 n 。
     * 能否在不打破种植规则的情况下种入 n 朵花？能则返回True，不能则返回False。
     *
     * 注意:
     * 数组内已种好的花不会违反种植规则。
     * 输入的数组长度范围为 [1, 20000]。
     * n 是非负整数，且不会超过输入数组的大小。
     *
     * @param flowerbed
     * @param n
     * @return
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        // 先找第一个 1 的位置
        int firstOneIndex = -1;
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 1) {
                firstOneIndex = i;
                break;
            }
        }
        if (firstOneIndex == -1) {
            // 当前花圃全为 0
            return noOne(flowerbed.length) >= n;
        }
        // 找到最后一个 1 的位置
        int lastOneIndex = -1;
        for (int i = flowerbed.length-1; i >= 0; i--) {
            if (flowerbed[i] == 1) {
                lastOneIndex = i;
                break;
            }
        }
        // 先处理两端
        n -= oneOne(firstOneIndex) + oneOne(flowerbed.length - lastOneIndex - 1);
        if (n <= 0) {
            return true;
        }
        // 再处理中间
        int lastIndex = firstOneIndex;  // 上一个 1 的索引
        for (int i = firstOneIndex+1; i <= lastOneIndex; i++) {
            if (flowerbed[i] == 1) {
                n -= twoOne(i - lastIndex - 1);
                lastIndex = i;
                if (n <= 0) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 两端没有 1 的情况下，n 个 0 可种植的最多颗数
     */
    private int noOne(int n) {
        return (n+1) / 2;
    }

    /**
     * 一端有 1 的情况下，n 个 0 可种植的最多颗数
     */
    private int oneOne(int n) {
        return n / 2;
    }

    /**
     * 两端有 1 的情况下，n 个 0 可种植的最多颗数
     */
    private int twoOne(int n) {
        return (n+1) / 2 - 1;
    }
}
