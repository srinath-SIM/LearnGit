package HashMap.Pattern_3_HASHSET;

import java.util.HashSet;

public class ContainsDuplicate {

    public static boolean containsDuplicate(int[] nums) {

        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {

            // Already exists
            if (set.contains(num)) {
                return true;
            }

            // Add new element
            set.add(num);
        }

        return false;
    }

    public static void main(String[] args) {

        int[] nums = {1, 2, 3, 1};

        boolean result = containsDuplicate(nums);

        System.out.println(result);
    }
}