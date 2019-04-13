package com.qfh.inteview;

public class Leetcode1 {
    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        int[] prices1 = {1,2,3,4,5};
        int[] prices2 = {7,6,4,3,1};

        int maxpProfit = getMaxProfit2(prices);
        System.out.println("max profit is " + maxpProfit);

        int maxpProfit2 = getMaxProfit2(prices1);
        System.out.println("max profit is " + maxpProfit2);

        int maxpProfit3 = getMaxProfit2(prices2);
        System.out.println("max profit is " + maxpProfit3);
    }


    /**
     Leetcode 121. 买卖股票的最佳时机
     给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
     如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
     注意你不能在买入股票前卖出股票。

     示例 1:
     输入: [7,1,5,3,6,4]
     输出: 5
     解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。

     示例 2:
     输入: [7,6,4,3,1]
     输出: 0
     解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
     */
    public static int getMaxProfit1(int[] prices){

        //思路： 定义两个变量
        //maxProfit ： 记录最大的利润
        //minNum ： 记录遍历过的最小价格
        int maxProfit = 0;
        int minNum = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            if(Integer.MAX_VALUE != minNum && prices[i] - minNum > maxProfit){
                maxProfit = prices[i] - minNum;
            }

            if(prices[i] < minNum){
                minNum = prices[i];
            }
        }
        return maxProfit;
    }


    /**
     Leetcode 122. 买卖股票的最佳时机 II
     这次改成股票可以买卖多次, 但是你必须要在出售股票之前把持有的股票卖掉。
     示例 1:
     输入: [7,1,5,3,6,4]
     输出: 7
     解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。

     示例 2:
     输入: [1,2,3,4,5]
     输出: 4
     解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。

     示例 3:
     输入: [7,6,4,3,1]
     输出: 0
     解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
     */
    public static int getMaxProfit2(int[] prices){
        int maxProfit = 0;

        for (int i = 0; i < prices.length; i++) {
            if(i != 0 && prices[i] > prices[i-1]){
                maxProfit += prices[i] - prices[i-1];
            }
        }
        
        return maxProfit;
    }

}
