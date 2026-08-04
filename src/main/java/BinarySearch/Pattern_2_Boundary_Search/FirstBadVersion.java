package BinarySearch.Pattern_2_Boundary_Search;

public class FirstBadVersion {

    public static int firstBadVersion(int n) {

        int left = 1;
        int right = n;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (isBadVersion(mid)) {

                // Bad found
                // First bad left side-la irukkalam
                right = mid;

            } else {

                // Good version
                // First bad right side-la irukkum
                left = mid + 1;
            }
        }

        return left;
    }

    public static boolean isBadVersion(int version) {

        // Example:
        // Version 4 onwards bad

        return version >= 4;
    }

    public static void main(String[] args) {

        int n = 6;

        int result = firstBadVersion(n);

        System.out.println("First Bad Version = " + result);
    }
}