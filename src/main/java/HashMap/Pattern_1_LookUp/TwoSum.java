package HashMap.Pattern_1_LookUp;

import java.util.HashMap;

public class TwoSum {

    public static int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            int required = target - nums[i];

            // Required number already present?
            if (map.containsKey(required)) {
                return new int[]{
                        map.get(required),
                        i
                };
            }

            // Number -> Index
            map.put(nums[i], i);
        }

        return new int[]{-1, -1};
    }

    public static void main(String[] args) {

        int[] nums = {2, 7, 11, 15};
        int target = 9;

        int[] result = twoSum(nums, target);

        System.out.println("Index 1: " + result[0]);
        System.out.println("Index 2: " + result[1]);
    }
}