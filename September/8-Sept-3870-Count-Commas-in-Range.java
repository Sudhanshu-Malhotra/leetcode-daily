class Solution {
    public int countCommas(int n) {
        int count = 0;

        // Numbers from 1000 to n have 1 comma each
        if (n >= 1000) {
            count += n - 999;
        }
        return count;
    }
}
