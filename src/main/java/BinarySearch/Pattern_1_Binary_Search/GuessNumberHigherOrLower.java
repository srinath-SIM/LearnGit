package BinarySearch.Pattern_1_Binary_Search;

public class GuessNumberHigherOrLower {

    public static int guessNumber(int n) {

        int left = 1;
        int right = n;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            int result = guess(mid);

            if (result == 0) {

                // Correct answer
                return mid;

            } else if (result < 0) {

                // mid perusu
                right = mid - 1;

            } else {

                // mid sirusu
                left = mid + 1;
            }
        }

        return -1;
    }

    public static int guess(int num) {

        int pick = 6;

        if (num == pick) {
            return 0;
        }

        if (num > pick) {
            return -1;
        }

        return 1;
    }

    public static void main(String[] args) {

        int n = 10;

        int result = guessNumber(n);

        System.out.println("Number = " + result);
    }
}