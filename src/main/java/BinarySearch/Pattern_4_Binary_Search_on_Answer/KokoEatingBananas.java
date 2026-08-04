package BinarySearch.Pattern_4_Binary_Search_on_Answer;

public class KokoEatingBananas {

    public static int minEatingSpeed(int[] piles, int h) {

        int left = 1;
        int right = 0;

        // Maximum pile = maximum possible speed
        for (int pile : piles) {
            right = Math.max(right, pile);
        }

        int answer = right;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (canFinish(piles, h, mid)) {

                // Indha speed-la finish panna mudiyuthu
                answer = mid;

                // Innum smaller speed try pannalam
                right = mid - 1;

            } else {

                // Speed increase pannanum
                left = mid + 1;
            }
        }

        return answer;
    }

    public static boolean canFinish(int[] piles, int h, int speed) {

        int hours = 0;

        for (int pile : piles) {

            // Ceiling division
            hours += (pile + speed - 1) / speed;

            if (hours > h) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        int[] piles = {3, 6, 7, 11};
        int h = 8;

        int result = minEatingSpeed(piles, h);

        System.out.println("Minimum Eating Speed = " + result);
    }
}