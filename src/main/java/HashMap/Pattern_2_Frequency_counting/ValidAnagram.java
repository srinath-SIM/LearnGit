package HashMap.Pattern_2_Frequency_counting;

import java.util.HashMap;
import java.util.Map;

public class ValidAnagram {

    public static boolean isAnagram(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }

        HashMap<Character, Integer> map = new HashMap<>();

        // Count characters in s
        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        // Remove characters using t
        for (char ch : t.toCharArray()) {

            if (!map.containsKey(ch)) {
                return false;
            }

            map.put(ch, map.get(ch) - 1);

            if (map.get(ch) == 0) {
                map.remove(ch);
            }
        }

        return map.isEmpty();
    }

    public static void main(String[] args) {

        String s = "anagram";
        String t = "nagaram";

        System.out.println(isAnagram(s, t));
    }
}