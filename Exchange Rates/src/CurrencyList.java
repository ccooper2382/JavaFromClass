/**
 * this class represents a list of Currency objects parsed from a CSV file
 *
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CurrencyList{
    /**
     * holds the Currency Objects parsed from a CSV file
     */
    private final List<Currency> currencies;

    /**
     * constructs and initializes the CurrencyList object
     */
    public CurrencyList() {
        this.currencies = new ArrayList<Currency>();
    }

    /**
     * add a Currency object to the CurrencyList object
     * @param currency
     */
    public void add(Currency currency) {currencies.add(currency);}

    /**
     * returns the list of currency objects
     * @return
     */
    public List<Currency> getCurrencies() {return this.currencies;}

    @Override
    public String toString() {
        for (Currency currency: currencies) {
            System.out.println(currency);
        }

        return "";
    }
    @Override
    public int hashCode() {return Objects.hash(currencies); }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) return true;
        if (!(obj instanceof CurrencyList)) {
            return false;
        }
        var that = (CurrencyList) obj;
        return  Objects.equals(currencies, that.currencies);
    }

    }

