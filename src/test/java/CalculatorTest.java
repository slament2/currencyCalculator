import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


public class CalculatorTest {

    @Test
    void calculateEurRateTest(){
        Map<String, Double> currencyRateMap = Parser.parse();
        Calculator calculator = new Calculator(currencyRateMap);

        double result1 = calculator.calculateEurRate("USD", 3054.58);
        assertEquals(result1, Double.parseDouble(calculator.getDecimalFormat().format(currencyRateMap.get("USD") * 3054.58)));

        double result2 = calculator.calculateEurRate("CHF", 18672.29212);
        assertEquals(result2, Double.parseDouble(calculator.getDecimalFormat().format(currencyRateMap.get("CHF") * 18672.29212)));

        assertNull(calculator.calculateEurRate("TRY", -1.0));

        assertNull(calculator.calculateEurRate("JPY", 0.0));
    }

}
