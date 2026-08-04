package TwoPointer.Pattern_1_Sliding_Window;

import java.util.ArrayList;
import java.util.List;

public class FindAllAnagramsInAString {

    public static List<Integer> findAnagrams(String s, String p) {

        List<Integer> result = new ArrayList<>();

        int[] pCount = new int[26];
        int[] windowCount = new int[26];

        // p-oda characters count
        for (char ch : p.toCharArray()) {
            pCount[ch - 'a']++;
        }

        int k = p.length();

        // Sliding Window
        for (int right = 0; right < s.length(); right++) {

            // Add right character
            windowCount[s.charAt(right) - 'a']++;

            // Window size > k
            if (right >= k) {

                // Remove left character
                windowCount[s.charAt(right - k) - 'a']--;
            }

            // Window size == k
            if (right >= k - 1) {

                if (isSame(pCount, windowCount)) {
                    result.add(right - k + 1);
                }
            }
        }

        return result;
    }

    public static boolean isSame(int[] pCount, int[] windowCount) {

        for (int i = 0; i < 26; i++) {

            if (pCount[i] != windowCount[i]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        String s = "cbaebabacd";
        String p = "abc";

        List<Integer> result = findAnagrams(s, p);

        System.out.println("Anagram Starting Indexes = " + result);
    }
}