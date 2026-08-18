package BasicAndOop.proggrams.Polymorphism;
class parent {
    void area() {
        System.out.println("Area of shape");
    }
}

class child extends parent{
    void area() {
        System.out.println("Area of circle");
    }
}
public class compiletimepoly {
    public static void main(String[] args) {
       parent s = new child(); // parent reference, child object
        s.area(); // resolved at runtime → Circle's method
    }
}


