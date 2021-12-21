/**
 * this class represents a number of circuits in serial
 * @author Caleb Cooper
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Serial extends Circuit  {
    /**
     * used to store a list of circuits in serial
     */
    private List<Resistor> circuitList;

    /**
     * constructs and initializes a serial circuit
     */
    public Serial () {
        super(0);
        this.circuitList = new ArrayList<>();
    }

    /**
     * adds a resistor to the circuit
     * @param resistor resistance of the resistor
     */
    public void addResistor(Resistor resistor) {
        circuitList.add(resistor);
    }

    /**
     * {@inheritDoc}
     * @return totalResistance
     */
    @Override
    public double getResistance() {
        var totalResistance = 0;
        for (Resistor resistance : circuitList) {
            totalResistance += resistance.getResistance();
        }
        return totalResistance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.circuitList);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Serial)) return false;
        var that = (Serial) obj;
        return that.circuitList.equals(this.circuitList);
    }
    @Override
    public String toString() {
        return String.format(
                " %.4f ",
                getResistance());
    }
}
