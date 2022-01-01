
// In java we have two keywords for inheritance.
// We "extend" POJOs and Abstract classes
// But we "implement" interfaces.
// Shape is an abstract class.
// If we want to say Rectangle IS-A Shape
// We say Rectangle extends Shape.

import java.util.Objects;

// Because Shape has an abstract method "area" We MUST override area in Rectangle.
public class Rectangle extends Shape {

    // We also have an issue with constructors.
    private final double length;

    private final double width;

    // Would you agree that Rectangle IS-A Shape. Because we say Rectangle extends Shape.
    // For every rectangle object I create, it MUST create a Shape object.
    // This is called constructor chaining.
    // var rectangle = new Rectangle(5, 6), you actually create three objects!!!
    // 1st is the rectangle object. 2nd the shape object is create because rectangle IS-A shape,
    // 3rd is the instance of Object class.
    // rectangle(call shape(call object)))

    // Here the rectangle constructor MUST call the Shape constructor and it MUST be the first thing
    // it does.
    public Rectangle(double length, double width, String color) {
        super(color); // What is my super class ? Shape - so this calls my Super class constructor which takes a
        // color!
        this.length = length;
        this.width = width;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.length, this.width);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rectangle)) return false;
        var rectangle = (Rectangle) o;
        return Double.compare(rectangle.length, this.length) == 0 &&
               Double.compare(rectangle.width, this.width) == 0;
    }

    // We can also inherit documentation.
    /**
     * {@inheritDoc}
     */
    @Override
    public double area() {
        return this.length * this.width;
    }
}