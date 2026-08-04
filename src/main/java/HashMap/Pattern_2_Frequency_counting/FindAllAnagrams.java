package HashMap.Pattern_2_Frequency_counting;

import java.util.*;

public class FindAllAnagrams {

    public static List<Integer> findAnagrams(String s, String p) {

        List<Integer> result = new ArrayList<>();

        if (s.length() < p.length()) {
            return result;
        }

        HashMap<Character, Integer> pMap = new HashMap<>();
        HashMap<Character, Integer> windowMap = new HashMap<>();

        // Frequency of p
        for (char ch : p.toCharArray()) {
            pMap.put(ch, pMap.getOrDefault(ch, 0) + 1);
        }

        int windowSize = p.length();

        // Create first window
        for (int i = 0; i < windowSize; i++) {

            char ch = s.charAt(i);

            windowMap.put(
                    ch,
                    windowMap.getOrDefault(ch, 0) + 1
            );
        }

        // Check first window
        if (windowMap.equals(pMap)) {
            result.add(0);
        }

        // Sliding window
        for (int right = windowSize; right < s.length(); right++) {

            // Add new character
            char addChar = s.charAt(right);

            windowMap.put(
                    addChar,
                    windowMap.getOrDefault(addChar, 0) + 1
            );

            // Remove old character
            char removeChar = s.charAt(right - windowSize);

            windowMap.put(
                    removeChar,
                    windowMap.get(removeChar) - 1
            );

            if (windowMap.get(removeChar) == 0) {
                windowMap.remove(removeChar);
            }

            // Check anagram
            if (windowMap.equals(pMap)) {
                result.add(right - windowSize + 1);
            }
        }

        return result;
    }

    public static void main(String[] args) {

        String s = "cbaebabacd";
        String p = "abc";

        List<Integer> result = findAnagrams(s, p);

        System.out.println(result);
    }
}