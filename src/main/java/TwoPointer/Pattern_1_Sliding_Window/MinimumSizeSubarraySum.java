package TwoPointer.Pattern_1_Sliding_Window;

public class MinimumSizeSubarraySum {

    public static int minSubArrayLen(int target, int[] arr) {

        int left = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;

        for (int right = 0; right < arr.length; right++) {

            // Right element add
            sum = sum + arr[right];

            // Sum >= target aagura varaikum
            while (sum >= target) {

                int length = right - left + 1;

                minLength = Math.min(minLength, length);

                // Left element remove
                sum = sum - arr[left];
                left++;
            }
        }

        // No valid subarray
        if (minLength == Integer.MAX_VALUE) {
            return 0;
        }

        return minLength;
    }

    public static void main(String[] args) {

        int target = 7;
        int[] arr = {2, 3, 1, 2, 4, 3};

        int result = minSubArrayLen(target, arr);

        System.out.println("Minimum Length = " + result);
    }
}