package TwoPointer.SubPattern_2.SameDirection;

import java.util.Arrays;

public class RemoveDuplicates {
//    FAST → Search / Check pannum 🔍
//    SLOW → Save / Write pannum ✍️

    public static int removeDuplicates(int[] nums) {

        // Empty array
        if (nums.length == 0) {
            return 0;
        }

        int slow = 0;

        for (int fast = 1; fast < nums.length; fast++) {

            // New unique element found
            if (nums[fast] != nums[slow]) {

                slow++;

                nums[slow] = nums[fast];
            }
        }

        return slow + 1;
    }

    public static void main(String[] args) {

        int[] nums = {1, 1, 2, 2, 3};

        System.out.println("Before:");
        System.out.println(Arrays.toString(nums));

        int k = removeDuplicates(nums);

        System.out.println("After:");

        for (int i = 0; i < k; i++) {
            System.out.print(nums[i] + " ");
        }

        System.out.println();

        System.out.println("Unique elements count: " + k);
    }
}