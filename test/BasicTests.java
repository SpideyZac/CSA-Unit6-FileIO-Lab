import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class BasicTests {

    private static final double EPSILON = 0.0001;

    @Test
    public void testCountBullishDays_basic() throws FileNotFoundException {
        Scanner sc = StockAnalyzer.openFile("test/data1");
        assertEquals(3, StockAnalyzer.countBullishDays(sc));
    }

    @Test
    public void testLongestBullRun_basic() throws FileNotFoundException {
        Scanner sc = StockAnalyzer.openFile("test/data1");
        assertEquals(2, StockAnalyzer.longestBullRun(sc));
    }

    @Test
    public void testMaxDrawdown_basic() throws FileNotFoundException {
        Scanner sc = StockAnalyzer.openFile("test/data1");
        assertEquals(0.013, StockAnalyzer.maxDrawdown(sc), 0.001);
    }

    @Test
    public void testTrading_basic() throws FileNotFoundException {
        Scanner sc = StockAnalyzer.openFile("test/data1");
        assertEquals(0.3, StockAnalyzer.simulateTrading(sc), EPSILON);
    }
}
