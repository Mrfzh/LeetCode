public class Eleven {
    public static void main(String[] args) {
        int [] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea_doublePointer(height));
    }

    /**
     * 双指针法
     * 由两端向中间扫描
     */
    private static int maxArea_doublePointer(int[] height) {
        int max = 0;
        int l = 0;
        int r = height.length - 1;

        while (l < r) {
            max = Math.max(max, Math.min(height[l], height[r])*(r-l));
            //根据l与r之间的最大容纳水量求出新的最大容纳水量
            if (height[l] < height[r]) {
                //如果l的高度比r小，那么有l参与的最大容纳水量最大就是l与r，不需要再考虑l
                l++;
            } else {    //同上，不需要再考虑r
                r--;
            }
        }

        return max;
    }

    private static int maxArea(int[] height) {
        int n = height.length;  //数组长度
        int [] dp = new int[n+1];   //dp[i]表示前i个元素构成的容器可容纳最多的水

        dp[2] = Math.min(height[0], height[1]);     //如果只有两个元素
        int max = 0;    //存储和height[i-1]组成的容器中可容纳最多的水量
        for (int i = 3; i <= n; i++) {
            for (int j = 0; j < i; j++) {   //找出和height[i-1]组成的容器中可容纳最多的水
                int k = (i-1-j) * Math.min(height[i-1], height[j]);
                if (k > max) {
                    max = k;
                }
            }
            dp[i] = Math.max(dp[i-1], max);
            max = 0;
        }

        return dp[n];
    }
}
