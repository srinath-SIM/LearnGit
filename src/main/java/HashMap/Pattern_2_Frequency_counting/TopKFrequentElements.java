package HashMap.Pattern_2_Frequency_counting;

import java.util.*;

public class TopKFrequentElements {

    public static int[] topKFrequent(int[] nums, int k) {

        // Step 1: Count frequency
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Step 2: Min Heap
        PriorityQueue<Integer> pq =
                new PriorityQueue<>(
                        (a, b) -> map.get(a) - map.get(b)
                );

        // Step 3: Keep only k elements
        for (int num : map.keySet()) {

            pq.offer(num);

            if (pq.size() > k) {
                pq.poll();
            }
        }

        // Step 4: Convert heap to array
        int[] result = new int[k];

        for (int i = k - 1; i >= 0; i--) {
            result[i] = pq.poll();
        }

        return result;
    }

    public static void main(String[] args) {

        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;

        int[] result = topKFrequent(nums, k);

        System.out.println(Arrays.toString(result));
    }
}