package BinarySearch.Pattern_4_Binary_Search_on_Answer;

public class CapacityToShipPackages {

    public static int shipWithinDays(int[] weights, int days) {

        int left = 0;
        int right = 0;

        for (int weight : weights) {

            // Capacity minimum = largest package
            left = Math.max(left, weight);

            // Capacity maximum = total weight
            right += weight;
        }

        int answer = right;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (canShip(weights, days, mid)) {

                // Indha capacity-la ship panna mudiyuthu
                answer = mid;

                // Smaller capacity try pannalam
                right = mid - 1;

            } else {

                // Capacity increase pannanum
                left = mid + 1;
            }
        }

        return answer;
    }

    public static boolean canShip(int[] weights, int days, int capacity) {

        int currentWeight = 0;
        int usedDays = 1;

        for (int weight : weights) {

            if (currentWeight + weight > capacity) {

                // New day
                usedDays++;

                currentWeight = 0;
            }

            currentWeight += weight;
        }

        return usedDays <= days;
    }

    public static void main(String[] args) {

        int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int days = 5;

        int result = shipWithinDays(weights, days);

        System.out.println("Minimum Capacity = " + result);
    }
}