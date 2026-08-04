package TwoPointer.Pattern_2_Prefix_SUM;

public class RangeSumQueryImmutable {

    public static int rangeSum(int[] nums, int left, int right) {

        // Create Prefix Sum
        int[] prefix = new int[nums.length + 1];

        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        // Calculate range sum
        return prefix[right + 1] - prefix[left];
    }

    public static void main(String[] args) {

        int[] nums = {1, 2, 3, 4, 5};

        int left = 1;
        int right = 3;

        int result = rangeSum(nums, left, right);

        System.out.println("Range Sum = " + result);
    }
}