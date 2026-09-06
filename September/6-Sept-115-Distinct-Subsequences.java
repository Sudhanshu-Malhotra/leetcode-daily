class Solution {
    public int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();

        // dp[j] = number of ways to form t[0...j-1]
        // using the characters processed from s
        int[] dp = new int[m + 1];

        // Empty string can always be formed in exactly 1 way
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {

            // Go backwards so we don't overwrite
            // values needed for the current iteration
            for (int j = m; j >= 1; j--) {

                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    // Two choices:
                    // 1. Use s[i-1] to match t[j-1]
                    // 2. Skip s[i-1]
                    dp[j] = dp[j] + dp[j - 1];
                }
            }
        }

        return dp[m];    
    }
}
