package TwoPointer.Pattern_1_Sliding_Window;

public class MaximumSumSubarray {

    public static int maxSumSubarray(int[] arr, int k) {

        int left = 0;
        int sum = 0;
        int maxSum = Integer.MIN_VALUE;

        for (int right = 0; right < arr.length; right++) {

            // Add right element
            sum += arr[right];

            // Window size = k
            if (right - left + 1 == k) {

                // Update maximum
                maxSum = Math.max(maxSum, sum);

                // Remove left element
                sum -= arr[left];
                left++;
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {

        int[] arr = {2, 1, 5, 1, 3, 2};
        int k = 3;

        System.out.println(maxSumSubarray(arr, k));
    }
}
