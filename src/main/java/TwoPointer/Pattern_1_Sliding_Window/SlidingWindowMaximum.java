package TwoPointer.Pattern_1_Sliding_Window;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class SlidingWindowMaximum {

    public static int[] maxSlidingWindow(int[] nums, int k) {

        int[] result = new int[nums.length - k + 1];

        Deque<Integer> deque = new ArrayDeque<>();

        int index = 0;

        for (int right = 0; right < nums.length; right++) {

            // Window-க்கு வெளியே போன index remove
            while (!deque.isEmpty()
                    && deque.peekFirst() <= right - k) {

                deque.pollFirst();
            }

            // Smaller elements remove
            while (!deque.isEmpty()
                    && nums[deque.peekLast()] <= nums[right]) {

                deque.pollLast();
            }

            // Current index add
            deque.offerLast(right);

            // Window size == k
            if (right >= k - 1) {

                // Front always maximum
                result[index] = nums[deque.peekFirst()];

                index++;
            }
        }

        return result;
    }

    public static void main(String[] args) {

        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;

        int[] result = maxSlidingWindow(nums, k);

        System.out.println(Arrays.toString(result));
    }
}