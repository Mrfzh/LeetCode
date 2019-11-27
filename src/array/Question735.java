package array;

import java.util.Arrays;

public class Question735 {
    public static void main(String[] args) {
        int[] asteroids = {5, 10, -5};
        System.out.println(Arrays.toString(new Question735().asteroidCollision(asteroids)));
    }

    /**
     * 给定一个整数数组 asteroids，表示在同一行的行星。
     *
     * 对于数组中的每一个元素，其绝对值表示行星的大小，
     * 正负表示行星的移动方向（正表示向右移动，负表示向左移动）。
     * 每一颗行星以相同的速度移动。
     *
     * 找出碰撞后剩下的所有行星。
     * 碰撞规则：两个行星相互碰撞，较小的行星会爆炸。
     * 如果两颗行星大小相同，则两颗行星都会爆炸。
     * 两颗移动方向相同的行星，永远不会发生碰撞。
     *
     * 说明:
     * 数组 asteroids 的长度不超过 10000。
     * 每一颗行星的大小都是非零整数，范围是 [-1000, 1000] 。
     *
     * @param asteroids
     * @return
     */
    public int[] asteroidCollision(int[] asteroids) {
        int remainNum = asteroids.length; // 最终剩余的行星个数
        while (true) {
            boolean hasReduce = false;  // 这一轮过后行星个数是否有减少
            boolean[] hasBomb = new boolean[remainNum]; // 记录这一轮行星是否有爆炸
            // 开始遍历，计算每个行星是否会和下一个行星相撞
            int curr = 0;
            int next = 1;
            while (next < remainNum) {
                if ((asteroids[curr] < 0 && asteroids[next] < 0) ||
                        (asteroids[curr] > 0 && asteroids[next] > 0) ||
                        (asteroids[curr] < 0 && asteroids[next] > 0)) {
                    // 不会相撞
                    curr = next;
                    next = next + 1;
                } else {
                    hasReduce = true;
                    // 相撞，比较两者大小
                    int currSize = Math.abs(asteroids[curr]);
                    int nextSize = Math.abs(asteroids[next]);
                    if (currSize > nextSize) {  // 下一个行星爆炸
                        hasBomb[next] = true;
                        next++;
                    } else if (currSize < nextSize) {   // 当前行星爆炸
                        hasBomb[curr] = true;
                        curr = next;
                        next = next + 1;
                    } else {    // 两个都爆炸
                        hasBomb[curr] = true;
                        hasBomb[next] = true;
                        curr = next + 1;
                        next = next + 2;
                    }
                }
            }
            if (!hasReduce) {
                break;
            }
            // 更新数组
            int index = 0;
            for (int i = 0; i < remainNum; i++) {
                if (hasBomb[i]) {
                    continue;
                }
                asteroids[index] = asteroids[i];
                index++;
            }
            remainNum = index;
        }

        return Arrays.copyOfRange(asteroids, 0, remainNum);
    }
}
