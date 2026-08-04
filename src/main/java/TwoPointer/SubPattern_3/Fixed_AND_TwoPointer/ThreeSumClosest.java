package TwoPointer.SubPattern_3.Fixed_AND_TwoPointer;

import java.util.Arrays;

public class ThreeSumClosest {

//    3Sum Closest = 1 fixed + 2 pointers + closest
    public static int threeSumClosest(int[] nums, int target) {

        Arrays.sort(nums);

        int closestSum =
                nums[0] + nums[1] + nums[2];

        for (int i = 0; i < nums.length - 2; i++) {

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {

                int sum =
                        nums[i] + nums[left] + nums[right];

                // Check whether current sum is closer
                if (Math.abs(sum - target)
                        < Math.abs(closestSum - target)) {

                    closestSum = sum;
                }

                if (sum == target) {

                    return sum;

                } else if (sum < target) {

                    left++;

                } else {

                    right--;
                }
            }
        }

        return closestSum;
    }

    public static void main(String[] args) {

        int[] nums = {-1, 2, 1, -4};

        int target = 1;

        int answer =
                threeSumClosest(nums, target);

        System.out.println("Closest Sum: " + answer);
    }
}