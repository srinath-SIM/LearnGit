package TwoPointer.Pattern_1_Sliding_Window;

import java.util.HashSet;

public class LongestSubstringWithoutRepeatingCharacters {

    public static int longestSubstring(String s) {

        int left = 0;
        int maxLength = 0;

        HashSet<Character> set = new HashSet<>();

        for (int right = 0; right < s.length(); right++) {

            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }

            set.add(s.charAt(right));

            int length = right - left + 1;

            maxLength = Math.max(maxLength, length);
        }

        return maxLength;
    }


    public static void main(String[] args) {

        String s = "abcabcbb";

        int result = longestSubstring(s);

        System.out.println("Longest Length = " + result);
    }
}