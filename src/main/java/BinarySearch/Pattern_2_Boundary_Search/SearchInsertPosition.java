package BinarySearch.Pattern_2_Boundary_Search;

public class SearchInsertPosition {

    public static int searchInsert(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // Left final-ah correct boundary / insert position
        return left;
    }

    public static void main(String[] args) {

        int[] nums = {1, 3, 5, 6};
        int target = 2;

        int result = searchInsert(nums, target);

        System.out.println("Insert Position = " + result);
    }
}