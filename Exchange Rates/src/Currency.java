/**
 * this class represents the currency of a particular country
 * this class provides a method to parse the information from a CSV file into a Currency object
 */

import java.util.Objects;

public class Currency implements Comparable<Currency> {
    /**
     * Name of the country
     */
    private final String country;

    /**
     * name of the currency
     */
    private final String currency;

    /**
     * the currencies code A three-letter designation assigned by ISO standard 4217 denoting a given currency.
     */
    private final String currencyCode;

    /**
     * exchange rate per 1 Pound Sterling
     */
    private final Double perPound;

    /**
     * The start date of the exchange rate validity
     */
    private final String start;
    /**
     * the end date of the exchange rate validity
     */
    private final String end;

    /**
     * Constructs and initializes the Currency Object
     *
     * @param country Country name
     * @param currency Currency name
     * @param currencyCode Currency code
     * @param perPound exchange rate to 1 Pounds Sterling
     * @param start Beginning date of validity
     * @param end Ending date of validity
     */
    public Currency(String country, String currency, String currencyCode, Double perPound, String start, String end) {
        this.country = country;
        this.currency = currency;
        this.currencyCode = currencyCode;
        this.perPound = perPound;
        this.start = start;
        this.end = end;
    }

    /**
     * Parses a line from a CSV file taken from a URL into a Currency Object
     * @param line line of the CSV file to be parsed
     * @return Currency Object parsed from the CSV file
     */
    public static Currency parse(String line) {
        String[] currencyInfo = line.split(",");
        var country = "";
        var currency = "";
        var currencyCode = "";
        var perPound = 0.0;
        var start = "";
        var end = "";

        country = currencyInfo[0];
        currency = currencyInfo[1];
        currencyCode = currencyInfo[2];
        perPound = Double.parseDouble(currencyInfo[3]);
        start = currencyInfo[4];
        end = currencyInfo[5];

        return new Currency(country, currency, currencyCode, perPound, start, end);
    }

    /**
     * returns this objects country property
     * @return country
     */
    public String getCountryName() {
        return this.country;
    }
    /**
     * returns this objects currency property
     * @return currency
     */
    public String getCurrency() {
        return this.currency;
    }
    /**
     * returns this objects currencyCode property
     * @return currencyCode
     */
    public String getCurrencyCode() {
        return this.currencyCode;
    }
    /**
     * returns this objects perPound property representing the exchange rate per British Pound
     * @return perPound
     */
    public Double getPerPound() { return this.perPound; }

    /**
     * returns this objects start property representing the start date the data is valid
     * @return start
     */
    public String getStart() {
        return this.start;
    }

    /**
     * returns this objects end property representing the end date the data is valid
     * @return country
     */
    public String getEnd() {
        return this.end;
    }



    @Override
    public String toString() {
        return String.format(
                "%s, %s, %s, %s, %s, %s%n  ", this.country, this.currency, this.currencyCode, this.perPound, this.start, this.end);
    }
    @Override
    public int hashCode() {return Objects.hash(country, currency, currencyCode, perPound, start, end); }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) return true;
        if (!(obj instanceof Currency)) {
            return false;
        }
        var that = (Currency) obj;
        return  Objects.equals(country, that.country) &&
                Objects.equals(currency, that.currency) &&
                Objects.equals(currencyCode, that.currencyCode) &&
                perPound == that.perPound &&
                Objects.equals(start, that.start) &&
                Objects.equals(end, that.end);
    }
    @Override
    public int compareTo(Currency o) {
        return this.currencyCode.compareTo(o.currencyCode);
    }
}
