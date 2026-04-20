package BasicAndOop.proggrams.Inheritance;

class Shapes {
    protected String color;

    public void setCol(String col) {
        this.color = col;
    }

    public void area() {
        System.out.println("This shape is of " + this.color + " color.");

    }
}
class Circle extends Shapes{
     private double radius;
     public void setR(double R){
         this.radius = R;
     }
     @Override
    public void area(){
         double area= radius*radius*3.14;
         System.out.println("Area of circle is "+ area);
     }
}
class Triangle extends Shapes{
        private double Base, height;
        public void setSide(double B, double H){
            this.height= H;
            this.Base= B;

        }
    @Override
    public void area(){
        double area= 0.5 *(this.Base)*(this.height);
        System.out.println("Area of triangle is "+ area);
    }
}

public class Inherit {
    public static void main(String[] args){
        Circle c = new Circle();
        c.setCol("Red");
        c.setR(5);
        c.area();

        Triangle t = new Triangle();
        t.setCol("Blue");
        t.setSide(10, 5);
        t.area();
    }
}
