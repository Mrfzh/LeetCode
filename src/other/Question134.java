package other;

public class Question134 {
    public static void main(String[] args) {
        int[] gas = {3, 1 , 1};
        int[] cost = {1, 2 , 2};
        System.out.println(new Question134().canCompleteCircuit_better(gas, cost));
    }

    /**
     * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
     * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。
     * 你从其中的一个加油站出发，开始时油箱为空。
     * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
     * 说明:
     * 1. 如果题目有解，该答案即为唯一答案。
     * 2. 输入数组均为非空数组，且长度相同。
     * 3. 输入数组中的元素均为非负数。
     *
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;     //加油站个数
        for (int i = 0; i < n; i++) {
            if (gas[i] >= cost[i]) {
                //从第i个加油站出发
                int[] dp = new int[n];    //dp[a]代表到达地a个加油站的油量
                dp[i] = gas[i];
                int j = (i + 1) % n;
                while (j != i) {
                    dp[j] = gas[j] + dp[(j+n-1)%n] - cost[(j+n-1)%n];
                    if (dp[j] < cost[j]) {
                        break;
                    }
                    j = (j + 1) % n;
                }
                //如果可以环行一圈
                if (j == i) {
                    return i;
                }
            }
        }

        return -1;
    }

    /**
     * 优化，这里用到一个数学定理：
     * 如果一个数组的总和非负，那么一定可以找到其中找到一个点开始，累加和一直都是非负的
     */
    public int canCompleteCircuit_better(int[] gas, int[] cost) {
        int n = gas.length;
        int totalTank = 0;      //油箱中的油量
        int currTank = 0;       //从某个加油站开始的油量
        int startStation = 0;
        for (int i = 0; i < n; i++) {
            totalTank += gas[i] - cost[i];
            currTank += gas[i] - cost[i];
            //当前累加和为负，说明从startStation到i的点都不可能是起始点
            if (currTank < 0) {
                startStation = i + 1;
                currTank = 0;
            }
        }

        return totalTank >= 0 ? startStation : -1;
    }
}
