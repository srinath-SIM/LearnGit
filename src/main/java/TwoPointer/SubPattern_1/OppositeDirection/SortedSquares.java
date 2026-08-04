package TwoPointer.SubPattern_1.OppositeDirection;

import java.util.Arrays;

public class SortedSquares {

    public static int[] sortedSquare(int[] nums)
    {
        int left = 0;
        int right = nums.length - 1;

        int[] result = new int[nums.length];
        int index = nums.length - 1;

        while(left <= right)
        {
            int leftSquare = nums[left] * nums[left];
            int rightSquare = nums[right] * nums[right];

            if(leftSquare > rightSquare)
            {
                result[index] = leftSquare;
                left++;
            }
            else {
                result[index] = rightSquare;
                right--;
            }

            index--;
        }

        return result;

    }

    public static void main(String args[])
    {
        int[] nums = {-4,1,0,10};

        int[] result = sortedSquare(nums);

        System.out.println(Arrays.toString(result));
    }

}
