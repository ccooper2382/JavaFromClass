
import java.io.*;
import java.net.URL;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    private static final String CURRENCY_URL = "https://assets.publishing.service.gov.uk/government/uploads/system/uploads/attachment_data/file/974580/exrates-monthly-0421.csv";
    private final static String DELIMITER = ",";

    public static void main(String[] args) throws IOException {
        var currencyList = new CurrencyList();
        var url = new URL(CURRENCY_URL);

        try (var input = new Scanner(new BufferedReader(new InputStreamReader(url.openStream())))) {
            input.nextLine();
            while (input.hasNextLine()) {
                currencyList.add(Currency.parse(input.nextLine()));
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            System.out.println("File could not be loaded! Check URL!");
        }

        var currencies = currencyList.getCurrencies();
        Collections.sort(currencies);

        var keepGoing = true;

        while (keepGoing) {

            var userEntry = Menu.getAmount("Enter amount in British Pounds Sterling or -1 to quit: ");

            if (userEntry != -1) {
                var country = Menu.getString("Enter the country you would like to convert: ");
                var countryAvailable = false;

                for (Currency currency : currencies) {

                    if (currency.getCountryName().equalsIgnoreCase(country)) {
                        var convertedValue = currency.getCountryName().concat(" ").concat(currency.getCurrency());
                        var calculatedValue = currency.getPerPound() * userEntry;
                        var start = currency.getStart();
                        var end = currency.getEnd();

                        System.out.printf("%.2f British Pounds are worth %.2f %ss %n", userEntry,
                                calculatedValue, convertedValue);
                        System.out.printf("Conversion is valid between %s and %s %n", start, end);
                        countryAvailable = true;
                        var doExport = Menu.getExport("Would you like to export results to a file? (y/n)");
                        if (doExport.equalsIgnoreCase("y")) {
                            var fileName = Menu.getString("Enter file name: ");
                            fileName = fileName.concat(".csv");

                            try (var writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName)))) {
                                writer.println("Currency Code, Currency, Country, Converted Amount, Exchange Rate, Start Date, End Date");
                                for (Currency currency1 : currencies) {
                                    var calculatedValue1 = currency1.getPerPound() * userEntry;
                                    writer.printf("%s%s%s%s%s%s%.2f%s%.2f%s%s%s%s%n", currency1.getCurrencyCode(),
                                            DELIMITER, currency1.getCurrency(), DELIMITER,
                                            currency1.getCountryName(), DELIMITER, calculatedValue1, DELIMITER,
                                            currency1.getPerPound(), DELIMITER, currency1.getStart(), DELIMITER,
                                            currency1.getEnd());
                                }
                            } catch (IOException iOEx) {
                                System.out.println(iOEx.getMessage());
                            }
                        }
                    } else {

                    }
                }

                if (!countryAvailable) {
                    System.out.println("Country is not on file");
                }

            } else {
                keepGoing = false;
            }

        }
    }
}
