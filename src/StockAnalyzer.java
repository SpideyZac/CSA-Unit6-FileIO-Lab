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
    ============================================================================
    STOCK DATA FILE FORMAT
    ============================================================================
    
    The stock data file contains historical daily price data in the following format:
    
    DATE YYYY-MM-DD
    OPEN XX.XX
    HIGH XX.XX
    LOW XX.XX
    CLOSE XX.XX
    DATE YYYY-MM-DD
    OPEN XX.XX
    HIGH XX.XX
    LOW XX.XX
    CLOSE XX.XX
    ...
    
    Each day's data consists of exactly 5 lines:
      1. DATE line - The date in YYYY-MM-DD format
      2. OPEN line - The opening price for that trading day
      3. HIGH line - The highest price reached during that day
      4. LOW line - The lowest price reached during that day
      5. CLOSE line - The closing price at the end of that day
    
    This 5-line pattern repeats for each day in the dataset.
    
    Example from test/data1:
    DATE 2024-01-02
    OPEN 150.25
    HIGH 152.80
    LOW 149.50
    CLOSE 151.75
    DATE 2024-01-03
    OPEN 151.75
    HIGH 153.20
    LOW 151.00
    CLOSE 152.50
    
    IMPLEMENTATION TIP:
    Write helper methods to parse each day's data to avoid code duplication.
    Consider creating a helper method like:
      private static DayData readNextDay(Scanner sc)
    This will make your code cleaner and easier to debug.
    */

    /**
     * Opens a stock data file and returns a Scanner for reading it.
     * 
     * This method creates a Scanner object that can be used to read through
     * the stock data file line by line. The Scanner should be closed by the
     * caller when finished.
     * 
     * @param filePath The path to the stock data file (e.g., "test/data1")
     * @return A Scanner object ready to read from the file
     * @throws FileNotFoundException if the file doesn't exist or can't be read
     * 
     * Example usage:
     *   Scanner sc = openFile("test/data1");
     *   // ... use the scanner ...
     *   sc.close();
     */
    public static Scanner openFile(String filePath) throws FileNotFoundException {
        // TODO
        return null;
    }

    /**
     * Counts the total number of bullish days in the stock data.
     * 
     * A bullish day is defined as any day where:
     *   CLOSE price > OPEN price
     * 
     * This indicates the stock gained value during that trading day.
     * 
     * @param sc A Scanner positioned at the start of stock data
     * @return The count of bullish days in the dataset
     * 
     * Example:
     *   Day 1: OPEN=100, CLOSE=105 -> Bullish (gained $5)
     *   Day 2: OPEN=105, CLOSE=103 -> Not bullish (lost $2)
     *   Day 3: OPEN=103, CLOSE=108 -> Bullish (gained $5)
     *   Result: 2 bullish days
     * 
     * Note: The Scanner will be consumed by this method. If you need to
     * use the data again, you'll need to create a new Scanner.
     */
    public static int countBullishDays(Scanner sc) {
        // TODO
        return 0;
    }

    /**
     * Finds the longest consecutive sequence of bullish days.
     * 
     * A "bull run" is a streak of consecutive days where each day is bullish
     * (CLOSE > OPEN). This method finds the longest such streak in the data.
     * 
     * @param sc A Scanner positioned at the start of stock data
     * @return The length of the longest consecutive bullish streak
     * 
     * Example:
     *   Day 1: OPEN=100, CLOSE=105 -> Bullish
     *   Day 2: OPEN=105, CLOSE=110 -> Bullish
     *   Day 3: OPEN=110, CLOSE=115 -> Bullish
     *   Day 4: OPEN=115, CLOSE=112 -> Not bullish (streak breaks)
     *   Day 5: OPEN=112, CLOSE=114 -> Bullish
     *   Day 6: OPEN=114, CLOSE=116 -> Bullish
     *   Result: 3 (days 1-3 form the longest bull run)
     * 
     * Edge cases:
     *   - If no bullish days exist, return 0
     *   - A single bullish day counts as a run of length 1
     */
    public static int longestBullRun(Scanner sc) {
        // TODO
        return 0;
    }

    /**
     * Calculates the maximum drawdown experienced in the stock data.
     * 
     * Drawdown measures the largest peak-to-trough decline in the closing price.
     * For each day, we calculate: (peak - currentClose) / peak
     * where peak is the highest closing price seen up to (but not including) 
     * the current day.
     * 
     * The maximum drawdown is the largest such value encountered.
     * 
     * @param sc A Scanner positioned at the start of stock data
     * @return The maximum drawdown as a decimal (e.g., 0.15 means 15% drawdown)
     * 
     * Example:
     *   Day 1: CLOSE=100 -> No drawdown (first day, no prior peak)
     *   Day 2: CLOSE=120 -> peak=100, drawdown=(100-120)/100 = -0.20 (negative, ignored)
     *   Day 3: CLOSE=110 -> peak=120, drawdown=(120-110)/120 = 0.083 (8.3% down from peak)
     *   Day 4: CLOSE=90  -> peak=120, drawdown=(120-90)/120  = 0.25  (25% down from peak)
     *   Day 5: CLOSE=95  -> peak=120, drawdown=(120-95)/120  = 0.208 (20.8% down from peak)
     *   Result: 0.25 (the maximum drawdown was 25% on Day 4)
     * 
     * Important notes:
     *   - The first day cannot have a drawdown (no prior peak exists)
     *   - Only consider positive drawdowns (when current < peak)
     *   - The peak is always the highest close seen BEFORE the current day
     *   - Return 0.0 if no drawdown occurs (stock only goes up)
     */
    public static double maxDrawdown(Scanner sc) {
        // TODO
        return 0.0;
    }

    /**
     * Simulates a simple buy-on-bullish, sell-on-bearish trading strategy.
     * 
     * STARTING CONDITIONS:
     *   - Balance: $0
     *   - Shares owned: 0
     * 
     * TRADING RULES:
     *   - On a BULLISH day (CLOSE > OPEN): Buy 1 share at the CLOSE price
     *   - On a BEARISH day (CLOSE < OPEN): Sell 1 share at the CLOSE price
     *   - On a NEUTRAL day (CLOSE == OPEN): Do nothing
     *   - You can buy even with $0 balance (balance can go negative)
     *   - You CANNOT sell if you have 0 shares
     * 
     * END OF SIMULATION:
     *   - After processing all days, sell all remaining shares at the last CLOSE price
     *   - Return the final balance
     * 
     * @param sc A Scanner positioned at the start of stock data
     * @return The final account balance after selling all shares
     * 
     * Example walkthrough:
     *   Starting: balance=$0, shares=0
     *   
     *   Day 1: OPEN=100, CLOSE=105 (bullish)
     *     -> Buy 1 share at $105
     *     -> balance=-$105, shares=1
     *   
     *   Day 2: OPEN=105, CLOSE=103 (bearish)
     *     -> Sell 1 share at $103
     *     -> balance=-$105+$103=-$2, shares=0
     *   
     *   Day 3: OPEN=103, CLOSE=110 (bullish)
     *     -> Buy 1 share at $110
     *     -> balance=-$2-$110=-$112, shares=1
     *   
     *   Day 4: OPEN=110, CLOSE=115 (bullish, last day)
     *     -> Buy 1 share at $115
     *     -> balance=-$112-$115=-$227, shares=2
     *     -> Liquidate: sell 2 shares at $115 each = +$230
     *     -> Final balance: -$227+$230=$3
     *   
     *   Return: $3.0
     * 
     * Note: This is a simple strategy and will likely lose money on real data!
     * It's meant as a baseline to compare against your custom algorithm.
     */
    public static double simulateTrading(Scanner sc) {
        // TODO
        return 0.0;
    }

    /**
     * YOUR CUSTOM TRADING ALGORITHM - IMPLEMENT YOUR STRATEGY HERE!
     * 
     * This method is called once per trading day with the day's price data.
     * Your goal is to maximize your final balance through smart trading decisions.
     * 
     * TESTING PROCESS:
     *   - Your algorithm will be tested on 5 randomly generated stock datasets
     *   - Each dataset contains 365 trading days (1 year)
     *   - Your average final balance across the 5 tests becomes your leaderboard score
     *   - Higher scores are better!
     * 
     * METHOD PARAMETERS (all provided to you each day):
     * @param balance Current account balance (can be negative if you've been buying)
     * @param sharesOwned Number of shares you currently own (0 or more)
     * @param open The opening price for today
     * @param high The highest price reached today
     * @param low The lowest price reached today  
     * @param close The closing price for today
     * 
     * TRADING ACTIONS AVAILABLE:
     *   - StockTester.buyShare() - Buy 1 share
     *   - StockTester.sellShare() - Sell 1 share
     * 
     * RULES & CONSTRAINTS:
     *   - You CANNOT sell if sharesOwned == 0 (will throw IllegalStateException)
     *   - You CAN buy even with $0 balance (balance can go negative)
     *   - You can call buy/sell multiple times per day if you want
     *   - At the end of all 250 days, any remaining shares are automatically
     *     sold at the final closing price and added to your balance
     *   - You CANNOT change this method's signature
     * 
     * STRATEGY IDEAS TO CONSIDER:
     *   - Momentum: Buy when price is rising, sell when falling
     *   - Mean reversion: Buy dips, sell rallies
     *   - Trend following: Ride long-term trends
     *   - Stop-loss: Cut losses early to preserve capital
     *   - Take-profit: Lock in gains at target levels
     *   - Risk management: Don't hold too long or too much
     * 
     * TIPS FOR SUCCESS:
     *   - The simulated data has a slight upward drift (~0.1% per day)
     *   - Daily volatility is around 2%
     *   - Use static variables to track state across days (previous prices, etc.)
     *   - Test your strategy logic carefully - small bugs can cause big losses!
     *   - Consider when NOT to trade (sometimes doing nothing is the best move)
     * 
     * Good luck! May your algorithm prosper! 📈
     */
    public static void myTradingAlgorithm(double balance, int sharesOwned, double open, double high, double low, double close) {
        // TODO: Implement your winning strategy here!
    }
}
