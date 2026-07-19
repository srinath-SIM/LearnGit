public class BestTimeToBuySellStock {

    public static int maxProfit(int[] prices) {

        int minPrice = prices[0];
        int maxPrice = 0;

        for(int i=1;i<prices.length;i++)
        {
            if(prices[i] < minPrice)
            {
                minPrice = prices[i];
            }

            int profit = prices[i] - minPrice;

            if(profit > maxPrice)
            {
                maxPrice = profit;
            }

        }
        return maxPrice;

    }

    public static void main(String[] args) {

        int[] prices = {7,1,5,3,6,4};
        System.out.println(maxProfit(prices));

    }
}