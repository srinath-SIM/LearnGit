package HashMap.Pattern_3_HASHSET;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class IntersectionOfTwoArrays {

    public static int[] intersection(int[] nums1, int[] nums2) {

        HashSet<Integer> set = new HashSet<>();

        // Add nums1 elements
        for (int num : nums1) {
            set.add(num);
        }

        List<Integer> result = new ArrayList<>();

        // Check nums2
        for (int num : nums2) {

            if (set.contains(num)) {

                result.add(num);

                // Remove so duplicate answer won't come
                set.remove(num);
            }
        }

        // Convert List to int[]
        int[] answer = new int[result.size()];

        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }

    public static void main(String[] args) {

        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {2, 2};

        int[] result = intersection(nums1, nums2);

        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}