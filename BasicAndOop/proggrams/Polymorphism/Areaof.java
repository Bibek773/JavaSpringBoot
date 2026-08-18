package BasicAndOop.proggrams.Polymorphism;

import java.util.*;

class Shape{

    public void area(){
        System.out.println("Area of the shape");
    }
}
class circle extends Shape {
    public double a;
    circle(double radius){
        a = 3.14 *radius *radius;
    }

    @Override
    public void area(){

        System.out.println("the area of circle is "+ a);

    }
}
class triangle extends Shape{
    public double a;
    triangle(double base, double height){
        a= 0.5* base * height;

    }
    @Override
    public void area(){
        System.out.println("the area of triangle is "+ a);
    }
}
class rectangular extends Shape{
    public double a;

    rectangular(double length, double breadth){
        a= breadth*length;
    }
    @Override
    public void area(){
        System.out.println("The area of rectangular is"+ a);
    }
}
public class Areaof {
    public static void main(String[] args){

        System.out.println("Enter Base and height of Triangle");
        Scanner input = new Scanner(System.in);
        double b= input.nextDouble();
        double h= input.nextDouble();
        System.out.println("Enter the length and breadth of rectangular");
        double l = input.nextDouble();
        double br= input.nextDouble();
        System.out.println("Enter the radius of the circle");
        double r= input.nextDouble();
        List<Shape> lists = List.of(new triangle(b,h), new rectangular(l, br), new circle(r));
        //Shape ko object store
        for(Shape shapes : lists){
            shapes.area();
        }
//        for (DataType variable : collection) {
//            // use variable
//        }


    }
}
