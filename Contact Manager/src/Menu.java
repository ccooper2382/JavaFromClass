/**
 * this class contains methods to prompt the user for input and return the result after validation
 */

import java.util.Scanner;

public class Menu {

    /**
     * Prompts the user for a menu selection
     * @param exportPrompt the menu to be presented to the user
     * @return userEntry 1-3 or "l"
     */
    public static String getUserChoice(String exportPrompt) {
        System.out.println(exportPrompt);
        System.out.println("Enter menu number: ");
        var scanner = new Scanner(System.in);
        var userEntry = scanner.nextLine();

        while (!userEntry.equalsIgnoreCase("1") && !userEntry.equalsIgnoreCase("2") &&
                !userEntry.equalsIgnoreCase("3") && !userEntry.equalsIgnoreCase("l")) {
            System.out.println("Invalid, try again.");
            System.out.println(exportPrompt);
            System.out.println("Enter menu number: ");
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


}
