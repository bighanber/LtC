package dp;

// 连续子数组的最大和
//输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
//
//要求时间复杂度为O(n)。
//
//
//
//示例1:
//
//输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
//输出: 6
//解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
public class J42 {

    public static void main(String[] args) {
        J42 j = new J42();
        System.out.println(j.maxSubArray(new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    public int maxSubArray(int[] nums) {
        // dp[i]: 以nums[i]结尾的最大的连续子数组和
//        int n = nums.length;
//        int[] dp = new int[n];
//        dp[0] = nums[0];
//        int res = nums[0];
//        for (int i = 1; i < n; i++) {
//            dp[i] = Math.max(dp[ i - 1] + nums[i], nums[i]);
//            res = Math.max(res, dp[i]);
//        }
//
//        return res;

        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;

    }
}
