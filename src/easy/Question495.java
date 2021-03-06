package easy;

import java.util.Arrays;

public class Question495 {
    public static void main(String[] args) {
        int[] timeSeries = {1,2};
        System.out.println(new Question495().findPoisonedDuration(timeSeries, 2));
    }

    /**
     * 在《英雄联盟》的世界中，有一个叫 “提莫” 的英雄，
     * 他的攻击可以让敌方英雄艾希（编者注：寒冰射手）进入中毒状态。
     * 现在，给出提莫对艾希的攻击时间序列和提莫攻击的中毒持续时间，你需要输出艾希的中毒状态总时长。
     *
     * 你可以认为提莫在给定的时间点进行攻击，并立即使艾希处于中毒状态。
     *
     * @param timeSeries
     * @param duration
     * @return
     */
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        Arrays.sort(timeSeries);
        int res = 0;
        for (int i = 0; i < timeSeries.length; i++) {
            if (i == timeSeries.length-1) {
                res += duration;
                break;
            }
            int currTime = timeSeries[i];
            int nextTime = timeSeries[i+1];
            res += Math.min(duration, nextTime - currTime);
        }

        return res;
    }
}
