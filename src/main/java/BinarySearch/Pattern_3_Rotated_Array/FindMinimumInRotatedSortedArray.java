package BinarySearch.Pattern_3_Rotated_Array;

public class FindMinimumInRotatedSortedArray {

    public static int findMin(int[] nums) {

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {

            int mid = left + (right - left) / 2;

            // Mid right side value-kku greater
            // Minimum right side-la irukkum
            if (nums[mid] > nums[right]) {

                left = mid + 1;

            } else {

                // Minimum mid or left side-la irukkalam
                right = mid;
            }
        }

        return nums[left];
    }

    public static void main(String[] args) {

        int[] nums = {3, 4, 5, 1, 2};

        int result = findMin(nums);

        System.out.println("Minimum = " + result);
    }
}