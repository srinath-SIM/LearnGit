package TwoPointer.Pattern_2_Prefix_SUM;

public class FindPivotIndex {

    public static int pivotIndex(int[] nums) {

        int totalSum = 0;

        // Find total sum
        for (int num : nums) {
            totalSum += num;
        }

        int leftSum = 0;

        for (int i = 0; i < nums.length; i++) {

            int rightSum = totalSum - leftSum - nums[i];

            if (leftSum == rightSum) {
                return i;
            }

            leftSum += nums[i];
        }

        return -1;
    }

    public static void main(String[] args) {

        int[] nums = {1, 7, 3, 6, 5, 6};

        int result = pivotIndex(nums);

        System.out.println("Pivot Index = " + result);
    }
}