import java.util.*;
import java.io.*;
import java.lang.reflect.Method;

import org.spideyzac.stocktester.StockTester;

public class StockAnalyzer {
    public static void main(String[] args) throws Exception {
        Method method = StockAnalyzer.class.getMethod("myTradingAlgorithm", double.class, int.class, double.class, double.class, double.class, double.class);
        StockTester.testTradingAlgorithm(method);
    }

    /*
    The stock data file format is as follows (an example can be seen in test/data1):

    DATE YYYY-MM-DD
    OPEN XX.XX
    HIGH XX.XX
    LOW XX.XX
    CLOSE XX.XX
    ...

    Each day's data starts with a "DATE" line followed by lines
    for "OPEN", "HIGH", "LOW", and "CLOSE" prices.
    This repeats for multiple days.

    TIP:
    Write helper methods to read and parse each day's data instead of
    copying the same code multiple times!
    */

    // Opens the stock data file and returns a Scanner object for reading it.
    // Precondition: The file exists and is readable.
    public static Scanner openFile(String filePath) throws FileNotFoundException {
        // TODO
        return null;
    }

    // Counts the number of bullish days across the stock data file.
    // A bullish day is defined as a day where the closing price is higher than the opening price.
    public static int countBullishDays(Scanner sc) {
        // TODO
        return 0;
    }

    // Counts the max number of consecutive bullish days.
    public static int longestBullRun(Scanner sc) {
        // TODO
        return 0;
    }

    // Calculates the maximum drawdown in the stock prices.
    // Drawdown is defined by the equation: (peak - currentClose) / peak
    // where peak is the highest closing price observed before the current close.
    // The first day's close cannot be a drawdown as there is no prior peak.
    public static double maxDrawdown(Scanner sc) {
        // TODO
        return 0.0;
    }

    // Simulates a simple trading strategy and returns the final account balance.
    // Assume a starting balance of $0.
    // Strategy:
    // Start with 0 shares
    // Buy 1 share at CLOSE price if the day was bullish
    // Sell 1 share at CLOSE price if the day was bearish (closing price < opening price)
    // You can't sell if you have 0 shares.
    // You can buy even if you have no money (your balance can go negative).
    // At the last close price, sell all remaining shares and return the final balance.
    // You can assume there are no transaction fees.
    // At the end of the simulation, add back to your balance the value of any shares you still own at the last closing price.
    public static double simulateTrading(Scanner sc) {
        // TODO
        return 0.0;
    }

    // This is a placeholder for your trading algorithm.
    // You cannot change the method signature.
    // If you have 0 shares, you cannot sell.
    // You can buy even if you have no money (your balance can go negative).
    // You can call StockTester.sellShare() and StockTester.buyShare() as many times as you want as long as you follow the rules above.
    // This method will be used to test your alogorithm on 5 different stock datasets (for a years worth of data) 
    // chosen at random, and your score (your final balance) will be uploaded to a leaderboard.
    public static void myTradingAlgorithm(double balance, int sharesOwned, double open, double high, double low, double close) {
        // TODO
    }
}