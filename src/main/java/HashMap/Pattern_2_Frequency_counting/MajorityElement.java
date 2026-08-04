package HashMap.Pattern_2_Frequency_counting;

import java.util.HashMap;

public class MajorityElement {

    public static int majorityElement(int[] nums) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {

            map.put(num, map.getOrDefault(num, 0) + 1);

            if (map.get(num) > nums.length / 2) {
                return num;
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        int[] nums = {2, 2, 1, 1, 1, 2, 2};

        System.out.println(majorityElement(nums));
    }
}