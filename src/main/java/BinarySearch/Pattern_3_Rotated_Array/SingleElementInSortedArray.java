package BinarySearch.Pattern_3_Rotated_Array;

public class SingleElementInSortedArray {

    public static int singleNonDuplicate(int[] nums) {

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {

            int mid = left + (right - left) / 2;

            // Mid even index-ah irukkanum
            if (mid % 2 == 1) {
                mid--;
            }

            // Correct pair found
            if (nums[mid] == nums[mid + 1]) {

                // Single element right side-la irukkum
                left = mid + 2;

            } else {

                // Single element left side-la irukkum
                right = mid;
            }
        }

        return nums[left];
    }

    public static void main(String[] args) {

        int[] nums = {1, 1, 2, 3, 3, 4, 4};

        int result = singleNonDuplicate(nums);

        System.out.println("Single Element = " + result);
    }
}