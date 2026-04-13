package BasicAndOop.proggrams;

import java.util.Scanner;

class area {
    area(int l, int b){
        int aor = l * b;
        System.out.println("Area of Rectangle is " + aor);
    }

    area(float r){
        double aoc = 3.14 * r * r;
        System.out.println("Area of Circle is " + aoc);
    }
}

public class AreaOfObject {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        try {
            System.out.println("Select: \n 1 for Rectangle\n 2 for Circle");
            int a = input.nextInt();

            if(a == 1){
                System.out.println("Enter length and breadth");
                int l = input.nextInt();
                int b = input.nextInt();
                area obj = new area(l, b);
            }
            else if(a == 2){
                System.out.println("Enter radius");
                float r = input.nextFloat();   // FIXED (was nextInt)
                area obj = new area(r);
            }
            else {
                System.out.println("Invalid Input");
            }
        }
        catch (Exception e){
            System.out.println("Error: Invalid input type!");
        }
        finally {
            input.close(); // good practice
        }
    }
}