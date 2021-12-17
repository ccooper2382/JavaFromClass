/**
 * this class represents a number of circuits in parallel
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Parallel extends Circuit {

    /**
     * used to store a list of circuits in parallel
     */
    private List<Circuit> circuitList;
    /**
     * used to calculate the resistance of a parallel circuit
     */
    private static final int ONE = 1;

    /**
     * constructs an initializes a parallel circuit
     */
    public Parallel () {
        super(0);
        this.circuitList = new ArrayList<>();
    }

    /**
     * adds a circuit to the parallel circuit
     * @param circuit object of the circuit to be added
     */
    public void add(Circuit circuit) {
        circuitList.add(circuit);
    }

    /**
     * calculates the resistance of a parallel circuit
     * @return Integer total resistance of the circuit
     */
    @Override
    public double getResistance() {
        var resistance = 0.0;
        for (Circuit circuit: circuitList) {
            resistance += ONE/circuit.getResistance();
        }
        return ONE/resistance;
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.circuitList);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Parallel)) return false;
        var that = (Parallel) obj;
        return that.circuitList.equals(this.circuitList);
    }

    @Override
    public String toString() {
        return String.format(
                "%.4f ",
                getResistance());
    }

}