package HashMap.Pattern_1_LookUp;

import java.util.HashMap;

public class IsomorphicStrings {

    public static boolean isIsomorphic(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }

        HashMap<Character, Character> mapST = new HashMap<>();
        HashMap<Character, Character> mapTS = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {

            char sChar = s.charAt(i);
            char tChar = t.charAt(i);

            // s -> t mapping already exists
            if (mapST.containsKey(sChar)) {

                if (mapST.get(sChar) != tChar) {
                    return false;
                }
            }

            // t -> s mapping already exists
            if (mapTS.containsKey(tChar)) {

                if (mapTS.get(tChar) != sChar) {
                    return false;
                }
            }

            // Store both directions
            mapST.put(sChar, tChar);
            mapTS.put(tChar, sChar);
        }

        return true;
    }

    public static void main(String[] args) {

        String s = "egg";
        String t = "add";

        boolean result = isIsomorphic(s, t);

        System.out.println("Isomorphic: " + result);
    }
}