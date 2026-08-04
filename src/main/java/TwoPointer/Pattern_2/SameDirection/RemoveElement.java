package TwoPointer.Pattern_2.SameDirection;

import java.util.Arrays;

public class RemoveElement {

    public static int removeElement(int[] nums, int val) {

        int slow = 0;

        for (int fast = 0; fast < nums.length; fast++) {

            // val illaadha element-a mattum keep pannuvom
            if (nums[fast] != val) {

                nums[slow] = nums[fast];

                slow++;
            }
        }

        return slow;
    }

    public static void main(String[] args) {

        int[] nums = {3, 2, 2, 3};
        int val = 3;

        System.out.println("Before:");
        System.out.println(Arrays.toString(nums));

        int k = removeElement(nums, val);

        System.out.println("After:");

        for (int i = 0; i < k; i++) {
            System.out.print(nums[i] + " ");
        }

        System.out.println();

        System.out.println("Remaining elements count: " + k);
    }
}