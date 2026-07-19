package TwoPointer.Pattern_1.OppositeDirection;

import java.util.Arrays;

public class TwoSum {

    public static int[] twoSum(int[] nums, int target)
    {

        int left = 0;
        int right = nums.length - 1;

        while (left  < right)
        {
            int sum = nums[left] + nums[right];

            if(target == sum)
            {
                return new int[] {left,right};
            }

            if(target < sum)
            {
                right--;
            }
            else {
                left++;
            }
        }
        return new int[]{-1, -1};

    }


    public static void main(String[] args) {

        int[] nums = {2,7,11,15};
        int target = 18;
        int[] result = twoSum(nums,target);

        System.out.println(Arrays.toString(result));

    }
}