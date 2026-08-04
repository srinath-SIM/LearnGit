package BinarySearch.Pattern_4_Binary_Search_on_Answer;

public class MinimumDaysToMakeBouquets {

    public static int minDays(int[] bloomDay, int m, int k) {

        // m * k flowers venum
        if ((long) m * k > bloomDay.length) {
            return -1;
        }

        int left = Integer.MAX_VALUE;
        int right = Integer.MIN_VALUE;

        for (int day : bloomDay) {

            left = Math.min(left, day);
            right = Math.max(right, day);
        }

        int answer = -1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (canMakeBouquets(bloomDay, m, k, mid)) {

                // Indha day-la bouquets make panna mudiyuthu
                answer = mid;

                // Innum early day try pannalam
                right = mid - 1;

            } else {

                // More days wait pannanum
                left = mid + 1;
            }
        }

        return answer;
    }

    public static boolean canMakeBouquets(
            int[] bloomDay,
            int m,
            int k,
            int day) {

        int flowers = 0;
        int bouquets = 0;

        for (int bloom : bloomDay) {

            if (bloom <= day) {

                // Flower bloomed
                flowers++;

                if (flowers == k) {

                    // One bouquet ready
                    bouquets++;

                    flowers = 0;
                }

            } else {

                // Consecutive flowers break
                flowers = 0;
            }
        }

        return bouquets >= m;
    }

    public static void main(String[] args) {

        int[] bloomDay = {1, 10, 3, 10, 2};
        int m = 3;
        int k = 1;

        int result = minDays(bloomDay, m, k);

        System.out.println("Minimum Days = " + result);
    }
}