import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a series of stock purchases of a particular company
 *
 * This class provides a method to determine the total number of shares owned
 *
 * @author Caleb Cooper
 */

public class Stock {

    /**
     * The symbol of the stock
     */
    private String symbol;

    /**
     * The quantity owned of the stock stored in a collection each token represents a single purchase
     */
    private List<Integer> quantity;

    /**
     * The price of each purchase of the stock
     */
    private List<Double> price;

    /**
     * Constructs and initalizes a stock purchase with a given symbol a quantity collection and a price collection
     * @param symbol the stock symbol
     */
    public Stock (String symbol) {
        this.symbol = symbol;
        this.quantity = new ArrayList<>();
        this.price = new ArrayList<>();
    }

    /**
     * gets the symbol of this instance
     * @return the symbol of the instance
     */
    public String getSymbol() { return this.symbol; }

    /**
     * gets the quantities purchased of the stock
     * @return the quantities purchased
     */
    public List<Integer> getQuantity() { return this.quantity; }

    /**
     * gets the prices for each stock purchase
     * @return the price for each stock purchase
     */
    public List<Double> getPrice() { return this.price; }

    /**
     * adds the price of a stock purchase to the price collection
     * @param stockPrice price of the stock purchased per share
     */
    public void addPrice(Double stockPrice) {
        price.add(stockPrice);
    }

    /**
     * adds the quantity of a stock purchase to the quantity collection
     * @param stockQuantity quantity of the stock purchased
     */
    public void addQuantity(Integer stockQuantity) {
        quantity.add(stockQuantity);
    }

    /**
     * calculates the total number of shares owned
     * @return shareAmount total number of shares
     */
    public int totalShares() {
        var shareAmount = 0;

        for (int share : quantity) {
            shareAmount += share;
        }
        return shareAmount;
    }

}
