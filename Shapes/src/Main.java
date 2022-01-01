import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // I have a list of Shapes which could be ANY subclass of Shape.
        var shapes = new ArrayList<Shape>();

        // This is an example of polymorphic programming!
        shapes.add(new Rectangle(5.0, 6.0, "Red"));
        shapes.add(new Circle(5.0));
        shapes.add(new Rectangle(12.0, 3.0, "Green"));
        shapes.add(new Circle(10.0));

        var circle = new Circle(5.0);

        if (shapes.contains(circle)) {
            System.out.println("FOUND!");
        }

       // shapes.forEach(System.out::println);
    }
}