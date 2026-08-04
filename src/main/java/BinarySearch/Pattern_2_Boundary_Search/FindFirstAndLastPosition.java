package BinarySearch.Pattern_2_Boundary_Search;

import java.util.Arrays;

public class FindFirstAndLastPosition {

    public static int[] searchRange(int[] nums, int target) {

        int first = findFirst(nums, target);
        int last = findLast(nums, target);

        return new int[]{first, last};
    }

    // Find First Occurrence
    public static int findFirst(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;

        int answer = -1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {

                // Target found
                answer = mid;

                // Innum left side-la target irukka?
                right = mid - 1;

            } else if (nums[mid] < target) {

                left = mid + 1;

            } else {

                right = mid - 1;
            }
        }

        return answer;
    }

    // Find Last Occurrence
    public static int findLast(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;

        int answer = -1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {

                // Target found
                answer = mid;

                // Innum right side-la target irukka?
                left = mid + 1;

            } else if (nums[mid] < target) {

                left = mid + 1;

            } else {

                right = mid - 1;
            }
        }

        return answer;
    }

    public static void main(String[] args) {

        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;

        int[] result = searchRange(nums, target);

        System.out.println(Arrays.toString(result));
    }
}