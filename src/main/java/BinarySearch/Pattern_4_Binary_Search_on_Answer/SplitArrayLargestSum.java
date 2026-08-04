package BinarySearch.Pattern_4_Binary_Search_on_Answer;

public class SplitArrayLargestSum {

    public static int splitArray(int[] nums, int k) {

        int left = 0;
        int right = 0;

        for (int num : nums) {

            // Minimum possible answer
            left = Math.max(left, num);

            // Maximum possible answer
            right += num;
        }

        int answer = right;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (canSplit(nums, k, mid)) {

                // mid limit-la split panna mudiyuthu
                answer = mid;

                // Innum smaller largest sum try pannalam
                right = mid - 1;

            } else {

                // Bigger limit venum
                left = mid + 1;
            }
        }

        return answer;
    }

    public static boolean canSplit(
            int[] nums,
            int k,
            int maxSum) {

        int subarrays = 1;
        int currentSum = 0;

        for (int num : nums) {

            if (currentSum + num > maxSum) {

                // New subarray start
                subarrays++;

                currentSum = 0;
            }

            currentSum += num;
        }

        return subarrays <= k;
    }

    public static void main(String[] args) {

        int[] nums = {7, 2, 5, 10, 8};
        int k = 2;

        int result = splitArray(nums, k);

        System.out.println("Minimum Largest Sum = " + result);
    }
}