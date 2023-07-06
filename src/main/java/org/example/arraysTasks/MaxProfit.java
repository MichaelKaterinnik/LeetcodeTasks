package org.example.arraysTasks;

public class MaxProfit {

    // ищется максимальная разница между двумя элементами, с учетом определенного элемента массива
    // как отправной точки

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int minPrice = prices[0]; // Минимальная цена на покупку
        int maxProfit = 0; // Максимальная прибыль

        for (int i = 1; i < prices.length; i++) {
            int currentPrice = prices[i];
            int currentProfit = currentPrice - minPrice;

            if (currentPrice < minPrice) {
                minPrice = currentPrice; // Обновляем минимальную цену на покупку
            } else if (currentProfit > maxProfit) {
                maxProfit = currentProfit; // Обновляем максимальную прибыль
            }
        }

        return maxProfit;
    }
}
