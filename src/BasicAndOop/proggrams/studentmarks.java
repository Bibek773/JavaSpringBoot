package BasicAndOop.proggrams;
import java.util.Scanner;
class subjects{
    String name;
    int marks;
    Scanner input = new Scanner(System.in);
    void name(){
        System.out.println("Enter your name");
        name = input.nextLine();
        System.out.println("Enter your marks");
        marks = input.nextInt();
    }
    void display(){
        System.out.println("Name:" +name);
        System.out.println("Marks:"+ marks);
    }
}
public class studentmarks {
    public static void main(String[] args){
        subjects obj = new subjects();
        obj.name();
        obj.display();
    }
}
