package TwoPointer.Pattern_1_Sliding_Window;

import java.util.HashMap;

public class MinimumWindowSubstring {

    public static String minWindow(String s, String t) {

        HashMap<Character, Integer> map = new HashMap<>();

        // t-oda characters count store pannum
        for (char ch : t.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        int left = 0;
        int required = t.length();
        int minLength = Integer.MAX_VALUE;
        int start = 0;

        for (int right = 0; right < s.length(); right++) {

            char ch = s.charAt(right);

            // t-ku required character na
            if (map.containsKey(ch)) {

                if (map.get(ch) > 0) {
                    required--;
                }

                map.put(ch, map.get(ch) - 1);
            }

            // All characters found
            while (required == 0) {

                int length = right - left + 1;

                // Minimum window update
                if (length < minLength) {
                    minLength = length;
                    start = left;
                }

                char leftChar = s.charAt(left);

                // Left character t-la irundha
                if (map.containsKey(leftChar)) {

                    map.put(
                            leftChar,
                            map.get(leftChar) + 1
                    );

                    // Required character missing
                    if (map.get(leftChar) > 0) {
                        required++;
                    }
                }

                left++;
            }
        }

        if (minLength == Integer.MAX_VALUE) {
            return "";
        }

        return s.substring(start, start + minLength);
    }

    public static void main(String[] args) {

        String s = "ADOBECODEBANC";
        String t = "ABC";

        String result = minWindow(s, t);

        System.out.println("Minimum Window = " + result);
    }

}