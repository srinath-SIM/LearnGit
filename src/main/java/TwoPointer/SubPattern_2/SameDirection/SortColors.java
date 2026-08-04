package TwoPointer.SubPattern_2.SameDirection;

import java.util.Arrays;

public class SortColors {

    public static void sortColors(int[] nums) {

        int low = 0;
        int mid = 0;
        int high = nums.length - 1;

        while (mid <= high) {

            if (nums[mid] == 0) {

                // Swap low and mid
                int temp = nums[low];
                nums[low] = nums[mid];
                nums[mid] = temp;

                low++;
                mid++;

            } else if (nums[mid] == 1) {

                // 1 belongs in the middle
                mid++;

            } else { // nums[mid] == 2

                // Swap mid and high
                int temp = nums[mid];
                nums[mid] = nums[high];
                nums[high] = temp;

                high--;

                // Don't increment mid
            }
        }
    }

    public static void main(String[] args) {

        int[] nums = {2, 0, 2, 1, 1, 0};

        System.out.println("Before:");
        System.out.println(Arrays.toString(nums));

        sortColors(nums);

        System.out.println("After:");
        System.out.println(Arrays.toString(nums));
    }
}