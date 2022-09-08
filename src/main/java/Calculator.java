import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Map;

public class Calculator {

    private final Map<String, Double> exchangeRateMap;
    private final DecimalFormat decimalFormat = new DecimalFormat("#.00");

    public DecimalFormat getDecimalFormat() {
        return decimalFormat;
    }

    public Calculator(Map<String, Double> exchangeRateMap) {
        this.exchangeRateMap = exchangeRateMap;
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
        decimalFormatSymbols.setDecimalSeparator('.');
        decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
    }

    public Double calculateEurRate(String currencyCode, Double quantity) {

        try {
            if(quantity>0)
                return Double.parseDouble(decimalFormat.format(exchangeRateMap.get(currencyCode) * quantity));
            else
                throw new IllegalArgumentException("Quantity must be greater than 0.");

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
