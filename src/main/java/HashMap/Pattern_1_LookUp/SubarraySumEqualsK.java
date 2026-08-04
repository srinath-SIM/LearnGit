package HashMap.Pattern_1_LookUp;

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

            int required = prefixSum - k;

            // Required prefix sum already exists?
            if (map.containsKey(required)) {
                count += map.get(required);
            }

            // Store prefixSum -> frequency
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

        System.out.println("Number of subarrays: " + result);
    }
}