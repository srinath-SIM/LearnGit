package HashMap.Pattern_3_HASHSET;

import java.util.HashSet;

public class HappyNumber {

    public static boolean isHappy(int n) {

        HashSet<Integer> set = new HashSet<>();

        while (n != 1) {

            // Already seen → cycle
            if (set.contains(n)) {
                return false;
            }

            // Store current number
            set.add(n);

            // Calculate sum of squares
            n = sumOfSquares(n);
        }

        return true;
    }

    public static int sumOfSquares(int n) {

        int sum = 0;

        while (n > 0) {

            int digit = n % 10;

            sum += digit * digit;

            n = n / 10;
        }

        return sum;
    }

    public static void main(String[] args) {

        int n = 19;

        boolean result = isHappy(n);

        System.out.println(result);
    }
}