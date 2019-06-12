package dp;

public class Question91 {
    public static void main(String[] args) {
//        System.out.println(new dp.Question91().numDecodings("3026"));
//        System.out.println(new dp.Question91().numDecodings("12"));
        System.out.println(new Question91().numDecodings("2036204610253617"));
    }

    /**
     * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
     * 'A' -> 1
     * 'B' -> 2
     * ...
     * 'Z' -> 26
     *
     * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
     *
     * 动态规划
     *
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        //边界判断
        if (s.length() == 1) {
            if (s.equals("0")) {
                return 0;
            } else {
                return 1;
            }
        }else if (s.length() == 2) {
            if (s.contains("0")) {
                if (s.equals("10") || s.equals("20")) {
                    return 1;
                } else {
                    return 0;
                }
            } else {
                if (Integer.parseInt(s) > 26) {
                    return 1;
                } else {
                    return 2;
                }
            }
        }

        int [] dp = new int[s.length()+1];  //dp[a]表示字符s的倒数a个字符的解码方法数
        //初始条件
        dp[1] = (s.charAt(s.length()-1) == '0')? 0 : 1;
        String lastTwoChar = s.substring(s.length() - 2);
        if (lastTwoChar.contains("0")) {
            if (lastTwoChar.equals("10") || lastTwoChar.equals("20")) {
                dp[2] = 1;
            } else {
                dp[2] = 0;
            }
        } else {
            if (Integer.parseInt(lastTwoChar) > 26) {
                dp[2] = 1;
            } else {
                dp[2] = 2;
            }
        }

        //递推
        for (int i = 3; i < dp.length; i++) {
            char curr = s.charAt(s.length() - i);
            if (curr == '0') {
                char last = (s.length() - i - 1) >= 0 ? s.charAt(s.length() - i - 1) : 'a';
                if (last == '1' || last == '2') {
                    dp[i+1] = dp[i-1];
                    i++;
                } else {
                    return 0;
                }
            } else if (curr == '1'){
                dp[i] = dp[i-1] + dp[i-2];
            } else if (curr == '2') {
                int nextNum = s.charAt(s.length() - i + 1) - 48;
                dp[i] = (nextNum <= 6)? (dp[i-1] + dp[i-2]) : dp[i-1];
            } else {
                dp[i] = dp[i-1];
            }

//            System.out.println("dp[" + i + "] = " + dp[i]);
        }

        return dp[s.length()];
    }
}
