/**
 * This class represents a single resistor.
 * This class provides a method to determine the resistance of the resistor
 * @author Caleb Cooper
 */

import java.util.Objects;

public class Resistor extends Circuit {

    /**
     * constructs and initializes a resistor
     * @param resistance
     */
    public Resistor(double resistance) {
        super(resistance);
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public double getResistance() {
        return super.resistance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.resistance);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Resistor)) return false;
        var resistor = (Resistor) obj;
        return resistor.resistance.equals(this.resistance);
    }
}

