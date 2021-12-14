/**
 * Thermistors.  Calculates the temperature of a liquid flowing through a pipe from the resistance
 *
 * @author Caleb Cooper
 * @version Java Homework 2
 *
 * */


import java.util.Scanner;
import java.lang.Math;


public class Thermistor {

    /**
     * Used in nominator of the thermistor equation. R0
     */
    static final int RESISTANCE_ZERO = 10000;
    /**
     * Used in the nominator and denominator of the thermistor equation. T0
     */
    static final int TEMP_ZERO = 298;
    /**
     * Used in the nominator of the thermistor equation.
     */
    static final int BETA = 3960;
    /**
     * Used in the thermistor equation.
     */
    static final int KELVIN = 273;
    /**
     * used in the conversion of celsius to fahrenheit
     */
    static final int THIRTY_TWO = 32;
    /**
     * used in the conversion of celsius to fahrenheit and celsius to rankine
     */
    static final double ONE_POINT_EIGHT = 1.8;
    /**
     * used in the conversion of celsius to rankine and celsius to kelvin
     */
    static final double FULL_KELVIN = 273.15;


    public static void main(String[] args) {
        System.out.println("Enter Resistance: ");
        var scanner = new Scanner(System.in);

        var resistance = scanner.nextDouble();

        var temperatureTop = 0.0;
        temperatureTop = BETA * TEMP_ZERO;

        var temperatureLog = 0.0;
        temperatureLog = Math.log(resistance / RESISTANCE_ZERO);

        var temperatureBottom = TEMP_ZERO * temperatureLog + BETA;

        var temperature = 0.0;
        temperature = (temperatureTop / temperatureBottom) - KELVIN;
        System.out.printf("Celsius... %.2f %n", temperature);

        var fahrenheit = 0.0;
        fahrenheit = temperature * ONE_POINT_EIGHT + THIRTY_TWO;
        System.out.printf("fahrenheit... %.2f %n", fahrenheit);

        var rankine = 0.0;
        rankine = (temperature + FULL_KELVIN) * ONE_POINT_EIGHT;
        System.out.printf("Rankine... %.2f %n", rankine);

        var kelvin = 0.0;
        kelvin = temperature + FULL_KELVIN;
        System.out.printf("Kelvin... %.2f %n", kelvin);
    }
}
