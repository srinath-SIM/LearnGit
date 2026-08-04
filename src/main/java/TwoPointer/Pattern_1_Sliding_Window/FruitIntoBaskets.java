package TwoPointer.Pattern_1_Sliding_Window;

import java.util.HashMap;

public class FruitIntoBaskets {

    public static int totalFruit(int[] fruits) {

        int left = 0;
        int maxLength = 0;

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int right = 0; right < fruits.length; right++) {

            // Right fruit add pannum
            map.put(
                    fruits[right],
                    map.getOrDefault(fruits[right], 0) + 1
            );

            // More than 2 types irundha
            while (map.size() > 2) {

                // Left fruit remove pannum
                map.put(
                        fruits[left],
                        map.get(fruits[left]) - 1
                );

                // Count 0 aana map-la remove
                if (map.get(fruits[left]) == 0) {
                    map.remove(fruits[left]);
                }

                left++;
            }

            // Maximum window length
            int length = right - left + 1;

            maxLength = Math.max(maxLength, length);
        }

        return maxLength;
    }

    public static void main(String[] args) {

        int[] fruits = {1, 2, 1, 2, 3};

        int result = totalFruit(fruits);

        System.out.println("Maximum Fruits = " + result);
    }

}