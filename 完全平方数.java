public class 完全平方数 {


    public static void main(String[] args) {
        System.out.println(cal(12));
        System.out.println(cal(13));
    }

    static int cal(int n) {
        if (n == 1) {
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        int i = 1;
        while (i * i <= n) {
            dp[i * i] = 1;
            i++;
        }
        //比如输入一个63， 它可能是1+62 或者 2+61 等等。 遍历一下就可以。遍历到/2  63/2 = 31 31 32即可
        for (int j = 3; j <= n; j++) {
            if (dp[j] == 0) {
                dp[j] = Integer.MAX_VALUE;
            }
            for (int k = 1; k <= j / 2; k++) {
                dp[j] = Math.min(dp[j], dp[k] + dp[j - k]);
            }
        }
        return dp[n];
    }
}


