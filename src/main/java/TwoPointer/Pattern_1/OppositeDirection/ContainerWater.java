package TwoPointer.Pattern_1.OppositeDirection;

import java.util.Arrays;

public class ContainerWater {


    public static int[] maxArea(int[] height)
    {

        int left = 0;
        int right = height.length - 1;
        int max = 0;

        while (left < right)
        {
            int width = right - left;
            int min = Math.min(height[left],height[right]);
            int area = width * min;
            max = Math.max(max,area);

            if(height[left] < height[right])
            {
                left++;
            }
            else
            {
                right--;
            }
        }
        return new int[]{max};
    }


    public static void main(String[] args)
    {
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(Arrays.toString(maxArea(height)));
    }
}
