import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Parser.updateData();
        Map<String, Double> exchangeRateMap = Parser.parse();
        Calculator calculator = new Calculator(exchangeRateMap);
        Scanner scanner = new Scanner(System.in);
        List<String> currencyCodes = new ArrayList<>();
        exchangeRateMap.forEach((i,j) -> currencyCodes.add(i));


        while(true) {
            System.out.println("Choose currency: ");
            currencyCodes.forEach(code -> System.out.println(currencyCodes.indexOf(code) + 1 + ". " + code));

            System.out.println(currencyCodes.size() + 1 + ". Exit");
            int option = scanner.nextInt();

            if(option<1 || option > currencyCodes.size() + 1)
                System.out.println("Not an option");
            else if (option == currencyCodes.size()+1) {
                System.exit(0);
            } else {
                System.out.println("Type quantity:");
                double quantity = scanner.nextDouble();
                System.out.println(quantity + " EUR = " + calculator.calculateEurRate(currencyCodes.get(option - 1), quantity)
                        + " " + currencyCodes.get(option - 1));
            }

        }

    }
}
