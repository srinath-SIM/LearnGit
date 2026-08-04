package HashMap.Pattern_3_HASHSET;

import java.util.HashSet;

public class LongestConsecutiveSequence {

    public static int longestConsecutive(int[] nums) {

        HashSet<Integer> set = new HashSet<>();

        // Add all numbers
        for (int num : nums) {
            set.add(num);
        }

        int longest = 0;

        // Check every number
        for (int num : set) {

            // Start of a sequence
            if (!set.contains(num - 1)) {

                int currentNum = num;
                int currentLength = 1;

                // Find consecutive numbers
                while (set.contains(currentNum + 1)) {

                    currentNum++;
                    currentLength++;
                }

                longest = Math.max(longest, currentLength);
            }
        }

        return longest;
    }

    public static void main(String[] args) {

        int[] nums = {100, 4, 200, 1, 3, 2};

        int result = longestConsecutive(nums);

        System.out.println(result);
    }
}