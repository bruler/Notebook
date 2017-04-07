public class Solution {
    public int reverse(int x) {
        int ret = 0;
        while (x != 0) {
            if (Math.abs(ret) > Integer.MAX_VALUE/10) {
                return 0;
            }
            ret = ret * 10 + x % 10;
            x = x/10;
        }
        return ret;
    }
}
