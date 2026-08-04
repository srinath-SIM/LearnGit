package HashMap.Pattern_4_Grouping;

import java.util.*;

public class GroupAnagrams {

    public static List<List<String>> groupAnagrams(String[] strs) {

        HashMap<String, List<String>> map = new HashMap<>();

        for (String str : strs) {

            // 1. Convert string to character array
            char[] chars = str.toCharArray();

            // 2. Sort characters
            Arrays.sort(chars);

            // 3. Sorted characters become the common key
            String key = new String(chars);

            // 4. Create group if key doesn't exist
            map.putIfAbsent(key, new ArrayList<>());

            // 5. Add original string to the group
            map.get(key).add(str);
        }

        // 6. Return all groups
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {

        String[] strs = {
                "eat",
                "tea",
                "tan",
                "ate",
                "nat",
                "bat"
        };

        List<List<String>> result = groupAnagrams(strs);

        System.out.println(result);
    }
}