/** this class contains methods to prompt the user for input and return the result after validation
 *
 */
import java.util.Scanner;

public class Menu {

    /**
     * prompts the user to enter y or n if an export to file is desired
     * @param exportPrompt message for user
     * @return y or n
     */
    public static String getExport(String exportPrompt) {
        System.out.println(exportPrompt);
        var scanner = new Scanner(System.in);
        var userEntry = scanner.nextLine();

        while (!userEntry.equalsIgnoreCase("y") && !userEntry.equalsIgnoreCase("n")) {
            System.out.println("Invalid, try again.");
            System.out.println(exportPrompt);
            userEntry = scanner.nextLine();

        }
        return userEntry;
    }

    /**
     * a generic method to prompt a user for a string
     * @param prompt message for user
     * @return String
     */
    public static String getString(String prompt) {
        System.out.println(prompt);
        var scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * prompts the user to enter a double value
     * @param amountPrompt message for user
     * @return Double amount in pounds sterling
     */
    public static double getAmount(String amountPrompt) {
        System.out.println(amountPrompt);
        var scanner = new Scanner(System.in);
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid, try again.");
            scanner.nextLine();
            System.out.print(amountPrompt);
        }
        return scanner.nextDouble();
    }
}
