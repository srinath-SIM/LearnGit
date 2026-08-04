package TwoPointer.Pattern_2_Prefix_SUM;

import java.util.HashMap;

public class SubarraySumEqualsK {

    public static int subarraySum(int[] nums, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();

        // Prefix sum 0 occurs once
        map.put(0, 1);

        int prefixSum = 0;
        int count = 0;

        for (int num : nums) {

            prefixSum += num;

            // Check if required prefix exists
            if (map.containsKey(prefixSum - k)) {

                count += map.get(prefixSum - k);
            }

            // Store prefix sum
            map.put(
                    prefixSum,
                    map.getOrDefault(prefixSum, 0) + 1
            );
        }

        return count;
    }

    public static void main(String[] args) {

        int[] nums = {1, 1, 1};
        int k = 2;

        int result = subarraySum(nums, k);

        System.out.println("Count = " + result);
    }
}