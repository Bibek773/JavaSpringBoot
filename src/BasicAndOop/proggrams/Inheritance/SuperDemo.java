package BasicAndOop.proggrams.Inheritance;


class Shape {
    public void area() {
        System.out.println("Calculating shape area...");
    }
}

class Circ extends Shape {

    @Override
    public void area() {
        super.area();
        double r = 5;
        double a = 3.14 * r * r;

        System.out.println("Area of circle: " + a);
    }
}

public class SuperDemo {
    public static void main(String[] args) {
        Circ c = new Circ();
        c.area();
    }
}