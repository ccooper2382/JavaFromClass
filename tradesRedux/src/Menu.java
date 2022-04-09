import java.util.LinkedList;
import java.util.Scanner;

public class Menu {

    /**
     * Tax rate used to calculate taxes for stock sales
     */
    private static final double TAX_RATE = 0.15;

    /**
     * Used to display tax rate as a whole number instead of a float
     */
    public static final int ONE_HUNDRED = 100;

    /**
     * Prompts the user to select Buy Sell Print or Exit
     *
     * @return string
     */
    public static String userPrompt() {
        var scanner = new Scanner(System.in);
        System.out.println("Buy (B or b)");
        System.out.println("Sell (S or s)");
        System.out.println("Print (P or p)");
        System.out.println("Exit (E or e)");
        System.out.print(">");

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

    /**
     * Launches the TradesRedux program
     */
    public static void Launch() {

        var firstPromptValid = true;
        LinkedList<Stock> stockBucket = new LinkedList<>();

        while (firstPromptValid) {
            var userOptionSelect = userPrompt();

            if (!userOptionSelect.equalsIgnoreCase("b") &&
                    !userOptionSelect.equalsIgnoreCase("s") &&
                    !userOptionSelect.equalsIgnoreCase("p") &&
                    !userOptionSelect.equalsIgnoreCase("e")) {

                System.out.println("Invalid Entry");
            } else if (userOptionSelect.equalsIgnoreCase("b")) {
                var stockSymbol = getStockSymbol("Enter sock symbol: ");
                var stockAvailable = false;
                for (Stock stock : stockBucket) {
                    if (stock.getSymbol().equals(stockSymbol)) {
                        stockAvailable = true;
                        stock.addQuantity(getQuantity("Enter quantity: "));
                        stock.addPrice(getPrice("Enter price: "));
                    }

                }
                if (!stockAvailable) {
                    var newStock = new Stock(stockSymbol);
                    newStock.addQuantity(getQuantity("Enter quantity: "));
                    newStock.addPrice(getPrice("Enter price: "));
                    stockBucket.add(newStock);
                }
            } else if (userOptionSelect.equalsIgnoreCase("s")) {
                var stockSymbol = getStockSymbol("Enter sock symbol: ");
                var stockAvailable = false;
                for (Stock stock : stockBucket) {
                    if (stock.getSymbol().equals(stockSymbol)) {
                        stockAvailable = true;
                        var stockSellQuantity = (getQuantity("Enter quantity: "));
                        if (stock.totalShares() < stockSellQuantity) {
                            System.out.printf("You currently have %d shares of %s%n", stock.totalShares(), stockSymbol);
                        } else {
                            var stockSellPrice = 0.0;
                            stockSellPrice = (getPrice("Enter Sell price"));
                            var totalSale = 0.0;
                            var keepGoing = true;
                            System.out.printf("Selling stock (%s)%n", stockSymbol);
                            while (keepGoing) {


                                if (stockSellQuantity < stock.getQuantity().get(0)) {
                                    var sellTotal = stockSellQuantity * (stockSellPrice - stock.getPrice().get(0));
                                    var difference = stock.getQuantity().get(0) - stockSellQuantity;
                                    var profit = 0.0;
                                    profit = stockSellPrice - stock.getPrice().get(0);
                                    System.out.printf("Selling %d shares at %.2f (%.2f Profit) :   %.2f %n", stockSellQuantity,
                                            stockSellPrice, profit, sellTotal);

                                    totalSale += sellTotal;
                                    stock.addQuantity(difference);
                                    keepGoing = false;


                                } else if (stockSellQuantity > stock.getQuantity().get(0)) {
                                    stockSellQuantity = stockSellQuantity - stock.getQuantity().get(0);
                                    var sellTotal = stock.getQuantity().get(0) * (stockSellPrice - stock.getPrice().get(0));
                                    var profit = 0.0;
                                    profit = stockSellPrice - stock.getPrice().get(0);
                                    totalSale += sellTotal;
                                    System.out.printf("Selling %d shares at %.2f (%.2f Profit) :   %.2f %n", stock.getQuantity().get(0), stockSellPrice, profit, sellTotal);
                                    stock.getQuantity().remove(0);
                                    stock.getPrice().remove(0);
                                    keepGoing = true;
                                } else if (stockSellQuantity == 0) {
                                    keepGoing = false;

                                }
                            }

                            System.out.printf("Total sale amount   $  %.2f %n", totalSale);

                            var taxRate = 0.0;
                            taxRate = TAX_RATE * ONE_HUNDRED;
                            var totalTax = 0.0;
                            totalTax = totalSale * TAX_RATE;
                            var takeHome = 0.0;
                            takeHome = totalSale - totalTax;
                            System.out.printf("Tax (%.2f %%)   $  %.2f %n", taxRate, totalTax);
                            System.out.printf("Take Home  $  %.2f %n", takeHome);
                        }

                    }
                }
                if (!stockAvailable) {
                    System.out.println("Stock has not been purchased");
                }

            } else if (userOptionSelect.equalsIgnoreCase("p")) {
                System.out.printf("%15s%15s%15s%n", "Symbol:", "Quantity:", "Price:");
                for (Stock stock : stockBucket) {
                    var quantityDisplay = stock.getQuantity();
                    var priceDisplay = stock.getPrice();

                    for (var i = 0; i < quantityDisplay.size(); i++) {
                        System.out.printf("%15s", stock.getSymbol());
                        System.out.printf("%15d", quantityDisplay.get(i));
                        System.out.printf(" %15.2f %n", priceDisplay.get(i));
                    }
                }
            } else if (userOptionSelect.equalsIgnoreCase("e")) {
                firstPromptValid = false;
            }
        }

    }
}

