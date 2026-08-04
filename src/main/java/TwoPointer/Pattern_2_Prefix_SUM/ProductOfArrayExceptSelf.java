package TwoPointer.Pattern_2_Prefix_SUM;

import java.util.Arrays;

public class ProductOfArrayExceptSelf {

    public static int[] productExceptSelf(int[] nums) {

        int n = nums.length;

        int[] result = new int[n];

        // Prefix product
        result[0] = 1;

        for (int i = 1; i < n; i++) {

            result[i] = result[i - 1] * nums[i - 1];
        }

        // Suffix product
        int suffix = 1;

        for (int i = n - 1; i >= 0; i--) {

            result[i] = result[i] * suffix;

            suffix = suffix * nums[i];
        }

        return result;
    }

    public static void main(String[] args) {

        int[] nums = {1, 2, 3, 4};

        int[] result = productExceptSelf(nums);

        System.out.println(Arrays.toString(result));
    }
}