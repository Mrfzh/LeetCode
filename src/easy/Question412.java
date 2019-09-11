package easy;

import java.util.ArrayList;
import java.util.List;

public class Question412 {
    public static void main(String[] args) {
        System.out.println(new Question412().fizzBuzz(15));
    }

    /**
     * 写一个程序，输出从 1 到 n 数字的字符串表示。
     * 1. 如果 n 是3的倍数，输出“Fizz”；
     * 2. 如果 n 是5的倍数，输出“Buzz”；
     * 3. 如果 n 同时是3和5的倍数，输出 “FizzBuzz”。
     *
     * @param n
     * @return
     */
    public List<String> fizzBuzz(int n) {
        if (n <= 0) {
            return new ArrayList<>();
        }
        int lastFizz = 0;
        int lastBuzz = 0;
        int lastFizzBuzz = 0;
        List<String> res = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i - 15 == lastFizzBuzz) {
                res.add("FizzBuzz");
                lastFizzBuzz = i;
                lastFizz = i;
                lastBuzz = i;
            } else if (i - 5 == lastBuzz) {
                res.add("Buzz");
                lastBuzz = i;
            } else if (i - 3 == lastFizz) {
                res.add("Fizz");
                lastFizz = i;
            } else {
                res.add(String.valueOf(i));
            }
        }

        return res;
    }
}
