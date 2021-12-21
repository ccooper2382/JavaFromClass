/**
 * This class represents and electrical circuit.
 * This class provides a method to determine the resistance of the circuit
 * @author Caleb Cooper
 */

import java.util.Objects;

public abstract class Circuit {

    /**
     * the default resistance of a circuit used if the class is instantiated with a value of null
     */
    private static final double DEFAULT_RESISTANCE = 0.0;

    /**
     * the resistance of the circuit
     */
    protected final Double resistance;


    /**
     * initalizes and constructs a circuit
     * @param resistance
     */
    public Circuit(double resistance) {
        this.resistance = Objects.requireNonNullElse(resistance, DEFAULT_RESISTANCE);
    }

    /**
     * Determines the resistance of the circuit
     * @return double resistiance
     */
    public abstract double getResistance();

    @Override
    public int hashCode() {
        return Objects.hash(this.resistance);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Resistor)) return false;
        var that = (Circuit) obj;
        return that.resistance.equals(this.resistance);
    }
    @Override
    public String toString() {
        return String.format(
                "The resistance is %f ",
                this.resistance);
    }
}
