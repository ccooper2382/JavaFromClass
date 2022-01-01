/**
 * Haiku. Evaluates the number of syllables in a Haiku provided by the user to determine if it has the
 * appropriate number os syllables on each line.
 *
 * @author Caleb Cooper
 * @version Java Homework 3
 */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Haiku {
    /**
     * The appropriate number of syllables for lines one and three
     */
    static final int FIRST_LAST = 5;

    /**
     * The appropriate number of syllables for the second line
     */
    static final int SECOND = 7;

    public static boolean isVowel(char letter) {
        var vowel = false;
        var character = Character.toLowerCase(letter);

        if (Character.compare('a', letter) == 0
                || Character.compare('e', letter) == 0
                || Character.compare('i', letter) == 0
                || Character.compare('o', letter) == 0
                || Character.compare('u', letter) == 0
                || Character.compare('y', letter) == 0) {
            vowel = true;
        }
        return vowel;
    }

    /**
     *Counts the number of syllables in a word
     *
     * @param word  The word to be tested
     * @return count  The number of syllables in the word
     */
    public static int countSyllables(String word) {

        var count = 0;
        var isSyllable = true;
        char[] wordArray = word.toCharArray();

        for (int i = 0; i < wordArray.length; i++) {
            if (word.charAt(word.length() - 1) == 'e' && count != 0 && isSyllable) {
                count--;
            }

            if (isSyllable && isVowel(word.charAt(i))) {
                isSyllable = false;
                count++;
            } else if (!isVowel(word.charAt(i))) {
                isSyllable = true;
            }
        }

        return count;
    }

    /**
     * Tests if the first and last lines of the haiku are the appropriate 5 syllables
     * outputs appropriate message
     *
     * @param line the line to be tested
     * @param lineNumber first or last line
     */
    public static void isLineValid(int line, String lineNumber) {
        var syllables = 0;

        if (line < FIRST_LAST) {
            syllables = FIRST_LAST - line;
            System.out.println("Line".concat(" " + lineNumber).concat(" needs ") + syllables + " more syllables");
        } else if (line > FIRST_LAST) {
            syllables = line - FIRST_LAST;
            System.out.println("Line".concat(" " + lineNumber).concat(" needs ") + syllables + " fewer syllables");
        }
    }

    /**
     * Tests the second line for the appropriate 7 syllables
     * outputs appropriate message
     *
     * @param line line to be tested
     */
    public static void isLineValid(int line) {
        var syllables = 0;

        if (line < SECOND) {
            syllables = SECOND - line;
            System.out.println("Line two needs " + syllables + " more syllables");
        } else if (line > SECOND) {
            syllables = line - SECOND;
            System.out.println("Line two needs " + syllables + " fewer syllables");
        }
    }

    /**
     * Analyzes the specified line of the Haiku
     * Outputs to console the line with number of syllables in each word
     *
     * @param poem line of the haiku to be analyzed
     * @return numberSylables  the number of syllables in the line
     */
    public static int getAnalysis(String poem) {

        var lineSplit = poem.split(" ");
        var lineArray = new ArrayList<String>(Arrays.asList(lineSplit));
        var outputArray = new ArrayList<String>();
        var numberSylables = 0;

        for (var i = 0; i < lineArray.size(); i++) {

            numberSylables += countSyllables(lineArray.get(i));
            outputArray.add(lineArray.get(i).concat("("));
            outputArray.add(countSyllables(lineArray.get(i)) + ") ");

        }
        var lineTotal = String.valueOf(numberSylables);
        outputArray.add(" Line total = ".concat(lineTotal));

        var outputString = String.join(" ", outputArray);

        System.out.println(outputString);
        return numberSylables;
    }

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);

        System.out.println("Enter first line");
        String firstLine = "";
        firstLine = scanner.nextLine();

        System.out.println("Enter second line");
        var secondLine = "";
        secondLine = scanner.nextLine();

        System.out.println("Enter last line");
        var lastLine = "";
        lastLine = scanner.nextLine();

        var lineOne = getAnalysis(firstLine);
        var lineTwo = getAnalysis(secondLine);
        var lineThree = getAnalysis(lastLine);
        var total = lineOne + lineTwo + lineThree;
        System.out.print("Total syllables " + total);
        if (lineOne == FIRST_LAST && lineTwo == SECOND && lineThree == FIRST_LAST) {
            System.out.println(" Valid Haiku");
        } else {
            System.out.println(" Invalid Haiku");
        }
        isLineValid(lineOne, "one");
        isLineValid(lineTwo);
        isLineValid(lineThree, "three");

    }

}
