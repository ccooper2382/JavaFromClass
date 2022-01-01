// Inheritance represents the IS-A relationship.
// Shape IS-A Object (Shape is the sub class and Object is the super class)
// Inheritance can be used in 4 ways -
// Object inheritance (toString, equals, hashCode)
// POJO inheritance (POJO - Plain-old Java Object) this is NOT ideal.
// Abstract Class - This is used ALL the time.
// Interfaces - This is used ALL the time as well.

// ALL of the classes we have create up to this point have been POJOs. Just a regular class.

// Abstract - Is a class which CANNOT be instantiated. An abstract is USED for inheritance. We create it so
// other classes can use it has a super class. Java assumes inheritance.
// Abstract class vs POJO
// Abstract class can contain abstract methods and POJO can only contain concrete methods.
// abstract method is a method which MUST be overridden in a sub class. A concrete method is just a plain
// method can may NOT be overridden.

import java.util.Objects;

// Let say we had a list of different shapes. Squares, Circles, triangles, etc...
// Each Shape has an area, we calculate the area differently for each shape. So the question is, how can I create
// a family of classes which all have the same area method but different implementations of that method.
// We can create an abstract super class with an abstract area method.
// We could then say Square IS-A Shape and Circle IS-A Shape, etc...
public abstract class Shape {

    public static final String DEFAULT_COLOR = "GRAY";
    private final String color;

    // Let's assume that each shape will have an area and a color.


    public Shape(String color) {
        this.color = Objects.requireNonNullElse(color, DEFAULT_COLOR);
    }

    // An abstract method in an abstract class (or interface), it is a method WITHOUT a body.
    // Why, because the body will be provided later in a sub class.

    /**
     * Calculates the area of this instance. In this case, the area is the quantity
     * that represents the extent of this instance in a plane.
     *
     * @return the area of this shape.
     */
    public abstract double area();

    // This method comes from class Object, how does Shape inherit from Object?
    // In Java it happens auto.
    // Shape IS-A Object.

    @Override
    public String toString() {
        return String.format(
                "The area is %.2f and the color is %s",
                this.area(),
                this.color);
    }
}