package BinarySearch.Pattern_1_Binary_Search;

public class BinarySearch {

    public static int search(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            // Target perusa irundha right side poganum
            if (nums[mid] < target) {
                left = mid + 1;
            }

            // Target sirusa irundha left side poganum
            else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        int[] nums = {1, 3, 5, 7, 9};
        int target = 7;

        int result = search(nums, target);

        System.out.println("Index = " + result);
    }
}