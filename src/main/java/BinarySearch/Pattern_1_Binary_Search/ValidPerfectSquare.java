package BinarySearch.Pattern_1_Binary_Search;

public class ValidPerfectSquare {

    public static boolean isPerfectSquare(int num) {

        if (num < 2) {
            return true;
        }

        long left = 1;
        long right = num / 2;

        while (left <= right) {

            long mid = left + (right - left) / 2;

            long square = mid * mid;

            if (square == num) {
                return true;
            }

            if (square < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }

    public static void main(String[] args) {

        int num = 16;

        boolean result = isPerfectSquare(num);

        System.out.println("Perfect Square = " + result);
    }
}