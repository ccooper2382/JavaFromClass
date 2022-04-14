import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Trades {
    /**
     * Tax rate used to calculate taxes for stock sales
     */
    private static final double TAX_RATE = 0.15;
    /**
     * Prompts the user to select Buy Sell or Exit
     *
     * @return string
     */

    public static String userPrompt() {
        var scanner = new Scanner(System.in);
        System.out.println("Buy (B or b)");
        System.out.println("Sell (S or s)");
        System.out.println("Print (P or p)");
        System.out.println("Exit (E or e)");
        System.out.println(">");

        return scanner.next();
    }

    /**
     * Gets and validates user input for the stock symbol
     *
     * @param buyPrompt prompt user for stock symbol
     * @return user input scanner.nextLine
     */
    public static String getStockSymbol(String buyPrompt) {
        System.out.println(buyPrompt);
        var scanner = new Scanner(System.in);
        var userEntry = scanner.nextLine();

        while (userEntry.length() != 3) {
            System.out.println("Invalid, try again.");
            userEntry = scanner.nextLine();
        }
        return userEntry;
    }

    /**
     * Gets and validates user input for the quantity
     *
     * @param quantityPrompt prompt user for quantity
     * @return user input scanner.nextLine
     */
    public static int getQuantity(String quantityPrompt) {
        System.out.println(quantityPrompt);
        var scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid, try again.");
            scanner.nextLine();
            System.out.print(quantityPrompt);
        }
        return scanner.nextInt();
    }

    /**
     * Gets and validates user input for the buy price
     *
     * @param pricePrompt prompt user for price
     * @return user input scanner.nextLine
     */
    public static double getPrice(String pricePrompt) {
        System.out.println(pricePrompt);
        var scanner = new Scanner(System.in);
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid, try again.");
            scanner.nextLine();
            System.out.print(pricePrompt);
        }
        return scanner.nextDouble();
    }

    public static void main(String[] args) {
        var firstPromptValid = true;

        Map<String, ArrayList<Integer>> quantity = new HashMap<String, ArrayList<Integer>>();
        Map<String, ArrayList<Double>> price = new HashMap<String, ArrayList<Double>>();

        while (firstPromptValid) {
            var userOptionSelect = userPrompt();

            if (!userOptionSelect.equalsIgnoreCase("b") &&
                    !userOptionSelect.equalsIgnoreCase("s") &&
                    !userOptionSelect.equalsIgnoreCase("p") &&
                    !userOptionSelect.equalsIgnoreCase("e")) {

                System.out.println("Invalid Entry");
            } else if (userOptionSelect.equalsIgnoreCase("b")) {
                var stockSymbol = getStockSymbol("Enter sock symbol: ");
                if (quantity.containsKey(stockSymbol)) {
                    var stockQuantity = quantity.get(stockSymbol);

                    stockQuantity.add(getQuantity("Enter quantity: "));

                    var stockPrice = price.get(stockSymbol);
                    stockPrice.add(getPrice("Enter price: "));


                } else {
                    ArrayList<Integer> stockQuantity = new ArrayList<Integer>();


                    stockQuantity.add(getQuantity("Enter quantity: "));
                    quantity.put(stockSymbol, stockQuantity);

                    ArrayList<Double> stockPrice = new ArrayList<Double>();
                    stockPrice.add(getPrice("Enter price: "));
                    price.put(stockSymbol, stockPrice);

                }
            } else if (userOptionSelect.equalsIgnoreCase("s")) {
                var stockSymbol = getStockSymbol("Enter sock symbol: ");

                if (!quantity.containsKey(stockSymbol)) {
                    System.out.println("Stock has not been purchased");
                } else {
                    var stockQuantity = quantity.get(stockSymbol);
                    var stockPrice = price.get(stockSymbol);

                    var stockSellQuantity = 0;
                    stockSellQuantity = getQuantity("Enter quantity: ");
                    var stockTotal = 0;
                    for (int i = 0; i < stockQuantity.size(); i++) {
                        if (stockQuantity.get(i) <= 0) {
                            stockQuantity.remove(i);
                            stockPrice.remove(i);
                        } else {
                            stockTotal += stockQuantity.get(i);
                        }
                    }

                    if (stockTotal < stockSellQuantity) {
                        System.out.printf("You currently have %d shares of %s%n", stockTotal, stockSymbol);
                    } else {
                        var stockSellPrice = 0.0;
                        stockSellPrice = (getPrice("Enter Sell price"));
                        var totalSale = 0.0;
                        var keepGoing = true;
                        System.out.printf("Selling stock (%s)%n", stockSymbol);
                        while (keepGoing) {
                            if (stockSellQuantity < stockQuantity.get(0)) {
                                var sellTotal = stockSellQuantity * (stockSellPrice - stockPrice.get(0));
                                var difference = stockQuantity.get(0) - stockSellQuantity;
                                var profit = 0.0;
                                profit = stockSellPrice - stockPrice.get(0) ;
                                System.out.printf("Selling %d shares at %.2f (%.2f Profit) :   %.2f %n", stockSellQuantity,
                                        stockSellPrice, profit, sellTotal);

                                totalSale += sellTotal;
                                stockQuantity.set(0, difference);
                                keepGoing = false;


                            } else if (stockSellQuantity > stockQuantity.get(0)) {
                                stockSellQuantity = stockSellQuantity - stockQuantity.get(0);
                                var sellTotal = stockQuantity.get(0) * (stockSellPrice - stockPrice.get(0));
                                var profit = 0.0;
                                profit = stockSellPrice - stockPrice.get(0) ;
                                totalSale += sellTotal;
                                System.out.printf("Selling %d shares at %.2f (%.2f Profit) :   %.2f %n", stockQuantity.get(0), stockSellPrice, profit, sellTotal);
                                stockQuantity.remove(0);
                                stockPrice.remove(0);
                                keepGoing = true;
                            } else if (stockSellQuantity == 0) {
                                keepGoing = false;

                            }
                        }
                        quantity.put(stockSymbol, stockQuantity);
                        price.put(stockSymbol, stockPrice);
                        System.out.printf("Total sale amount   $  %.2f %n", totalSale);

                        var taxRate = 0.0;
                        taxRate = TAX_RATE * 100;
                        var totalTax = 0.0;
                        totalTax = totalSale * TAX_RATE;
                        var takeHome = 0.0;
                        takeHome = totalSale - totalTax;
                        System.out.printf("Tax (%.2f %%)   $  %.2f %n", taxRate, totalTax);
                        System.out.printf("Take Home  $  %.2f %n", takeHome);
                    }
                }
            } else if (userOptionSelect.equalsIgnoreCase("p")) {
                System.out.printf("%15s%15s%15s%n", "Symbol:", "Quantity:", "Price:");
                for (Map.Entry<String, ArrayList<Integer>> entry : quantity.entrySet()) {
                    var key = "";
                    key = entry.getKey();
                    var quantityDisplay = quantity.get(key);
                    var priceDisplay = price.get(key);

                    for (var i = 0; i < quantityDisplay.size(); i++) {
                        System.out.printf("%15s", key );
                        System.out.printf("%15d", quantityDisplay.get(i));
                        System.out.printf(" %15.2f %n", priceDisplay.get(i));
                    }

                }



            } else if (userOptionSelect.equalsIgnoreCase("e")) {
                firstPromptValid = false;
            } else {}

        }


    }

}





