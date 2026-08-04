package TwoPointer.Pattern_1_Sliding_Window;

import java.util.HashMap;

public class LongestRepeatingCharacterReplacement {

    public static int characterReplacement(String s, int k) {

        int left = 0;
        int maxLength = 0;
        int maxFrequency = 0;

        HashMap<Character, Integer> map = new HashMap<>();

        for (int right = 0; right < s.length(); right++) {

            // Current character count increase
            char ch = s.charAt(right);

            map.put(ch, map.getOrDefault(ch, 0) + 1);

            // Maximum frequency in current window
            maxFrequency = Math.max(
                    maxFrequency,
                    map.get(ch)
            );

            // Characters that need replacement
            int replaceCount =
                    (right - left + 1) - maxFrequency;

            // More than k replacements needed
            while (replaceCount > k) {

                char leftChar = s.charAt(left);

                map.put(
                        leftChar,
                        map.get(leftChar) - 1
                );

                left++;

                // Recalculate replacement count
                replaceCount =
                        (right - left + 1) - maxFrequency;
            }

            // Update maximum length
            int length = right - left + 1;

            maxLength = Math.max(maxLength, length);
        }

        return maxLength;
    }


    public static void main(String[] args) {

        String s = "AABABBA";
        int k = 1;

        int result = characterReplacement(s, k);

        System.out.println("Longest Length = " + result);
    }
}