package skill;

public class Question204 {
    public static void main(String[] args) {
        System.out.println(new Question204().countPrimes(10));
        System.out.println(new Question204().countPrimes(12545));
        System.out.println(new Question204().countPrimes(125450));
    }

    /**
     * 统计所有小于非负整数 n 的质数的数量。
     *
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        int res = 0;
        if (n > 2) {
            res++;
        }
        for (int i = 3; i < n; i += 2) {
            if (isPrime(i)) {
                res++;
            }
        }

        return res;
    }

    /**
     * 判断是否是质数
     */
    private boolean isPrime(int n) {
        for (int i = 2; i <= n/2; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 上面的暴力法超出时间限制
     *
     * 优化：厄拉多塞筛法
     */
    public int countPrimes_better(int n) {
        boolean[] isNotPrime = new boolean[n];
        int res = 0;
        for (int i = 2; i < n; i++) {
            // 某数为质数
            if (!isNotPrime[i]) {
                res++;
                // 则该数的倍数不是质数
                for (int j = i*2; j < n; j += i) {
                    isNotPrime[j] = true;
                }
            }
        }

        return res;
    }
}
