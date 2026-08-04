package TwoPointer.SubPattern_2.SameDirection;

import java.util.Arrays;

public class MoveZeros {

    public static void moveZeroes(int[] nums) {

        int slow = 0;

        for (int fast = 0; fast < nums.length; fast++) {

//            Fast → non-zero find
//            Slow → correct position-la save.
            // Non-zero element found
            // if you want front use == get like this [0, 0, 1, 3, 12]
            if (nums[fast] != 0) {

                // Swap
                int temp = nums[slow];
                nums[slow] = nums[fast];
                nums[fast] = temp;

                slow++;
            }
        }
    }

    public static void main(String[] args) {

        int[] nums = {0, 1, 0, 3, 12};

        System.out.println("Before:");
        System.out.println(Arrays.toString(nums));

        moveZeroes(nums);

        System.out.println("After:");
        System.out.println(Arrays.toString(nums));
    }
}
