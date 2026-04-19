package BasicAndOop.proggrams.Encapsulation;
class Student {
    private int age;

    Student(int age) {
        this.age = age;
    }

    void compare(Student s) {
        // accessing private field of another object of same class
        System.out.println("Other student's age: " + s.age);
    }


}
public class AccessOtherObjField {
    public static void main(String[] args) {
        Student s1 = new Student(20);
        Student s2 = new Student(25);

        s1.compare(s2);
    }
}

