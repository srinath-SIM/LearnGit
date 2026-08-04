package TwoPointer.SubPattern_1.OppositeDirection;

import java.util.Arrays;
import java.util.HashMap;

public class TwoSumUnsorted {


    public static int[] twoSumUnsorted(int[] nums, int target)
    {

        HashMap<Integer,Integer> map = new HashMap<>();

        for(int i=0; i<nums.length; i++)
        {
            int need = target - nums[i];

            if(map.containsKey(need))
            {
                return new int[]{map.get(need),i};
            }
            map.put(nums[i],i);
        }
        return new int[]{-1, -1};
    }


    public static void main(String[] args) {

        int[] nums = {3,2,4};
        int target = 6;
        int[] result = twoSumUnsorted(nums,target);

        System.out.println(Arrays.toString(result));
    }
}