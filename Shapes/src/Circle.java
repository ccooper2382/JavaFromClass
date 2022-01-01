import java.util.Objects;

public class Circle extends Shape {

    private final double radius;

    public Circle(double radius) {
        super(Shape.DEFAULT_COLOR);
        this.radius = radius;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.radius);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof Circle)) return false;

        var that = (Circle) obj;

        return Double.valueOf(this.radius).equals(that.radius);
                //Double.compare(this.radius, that.radius) == 0;
    }

    @Override
    public double area() {
        return Math.PI * this.radius * this.radius;
    }
}
