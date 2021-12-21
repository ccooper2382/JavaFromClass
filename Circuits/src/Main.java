public class Main {
    public static void main(String[] args) {

        var circuit1 = new Serial();
        circuit1.addResistor(new Resistor(42));
        circuit1.addResistor(new Resistor(23));
        circuit1.addResistor(new Resistor(19));

        var circuit3 = new Serial();
        circuit3.addResistor(new Resistor(23));
        circuit3.addResistor(new Resistor(82));

        var circuit4 = new Serial();
        circuit4.addResistor(new Resistor(75));


        var circuit2 = new Parallel();
        circuit2.add(circuit1);
        circuit2.add(circuit3);
        circuit2.add(circuit4);


        System.out.printf("The combined resistance is : %s", circuit2);
    }
}
