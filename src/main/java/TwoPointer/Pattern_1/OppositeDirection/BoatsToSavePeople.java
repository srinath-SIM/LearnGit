package TwoPointer.Pattern_1.OppositeDirection;

import java.util.Arrays;

public class BoatsToSavePeople {

    public static int numRescueBoats(int[] people, int limit) {

        Arrays.sort(people);

        int left = 0;
        int right = people.length - 1;
        int boats = 0;

        while (left <= right) {

            // Lightest + Heaviest fit together
            if (people[left] + people[right] <= limit) {
                left++;
            }

            // Heaviest person always goes
            right--;
            boats++;
        }

        return boats;
    }

    public static void main(String[] args) {

        int[] people = {3, 2, 2, 1};
        int limit = 3;

        System.out.println(numRescueBoats(people, limit));
    }
}