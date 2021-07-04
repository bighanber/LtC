
// 错误的集合

import java.util.HashMap;
import java.util.Map;

//集合 s 包含从 1 到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个数字复制了成了集合里面的另外一个数字的值，导致集合 丢失了一个数字 并且 有一个数字重复 。
//
//给定一个数组 nums 代表了集合 S 发生错误后的结果。
//
//请你找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
//
//
//
//示例 1：
//
//输入：nums = [1,2,2,4]
//输出：[2,3]
//示例 2：
//
//输入：nums = [1,1]
//输出：[1,2]
//
//
//提示：
//
//2 <= nums.length <= 104
//1 <= nums[i] <= 104
public class L645 {

    public static void main(String[] args) {
        L645 l = new L645();
        for (int num : l.findErrorNums(new int[] {1, 2, 2, 4})) {
            System.out.println(num);
        }
    }

    public int[] findErrorNums(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] res = new int[2];
        int n = nums.length;
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (int i = 1; i <= n; i++) {
            int count = map.getOrDefault(i, 0);
            if (count == 2) {
                res[0] = i;
            } else if (count == 0) {
                res[1] = i;
            }
        }

        return res;
    }

    public int[] findErrorNums2(int[] nums) {
        int[] tmp = new int[10001];
        int repeat = 0, errorNum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (tmp[nums[i]] == 0) {
                tmp[nums[i]] = 1;
            } else {
                repeat = nums[i];
            }
        }
        for (int i = 1; i <= nums.length; i++) {
            if (tmp[i] == 0) {
                errorNum = i;
                break;
            }
        }
        return new int[] {repeat, errorNum};
    }
}
