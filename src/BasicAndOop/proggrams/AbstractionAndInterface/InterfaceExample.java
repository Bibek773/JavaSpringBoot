package BasicAndOop.proggrams.AbstractionAndInterface;
interface Sortable<B>{
    int compareTo(B o);
    default boolean isLessThan(B o){
        return compareTo(o)<0; // mathi int rakhnu ko karan, yesle integer return garxa...
    }
}
class Student implements Sortable<Student> {
    String name;
    int marks;

    Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }

    @Override
    public int compareTo(Student other) {
        return this.marks - other.marks; // compare by marks
    }

    @Override
    public String toString() {
        return name + " (" + marks + ")";
    }
}
public class InterfaceExample {
    public static void main(String[] args){
        Student s1 = new Student("Bibek", 85);
        Student s2 = new Student("Ghimire bhai", 72);
        Student s3 = new Student("BG", 90);

        // using default method
        System.out.println(s1.isLessThan(s2)); // false
        System.out.println(s2.isLessThan(s3)); // true

        // simple sorting using compareTo
        Student[] students = {s1, s2, s3};

        for (int i = 0; i < students.length; i++) {
            for (int j = i + 1; j < students.length; j++) {
                if (students[j].isLessThan(students[i])) {
                    Student temp = students[i];
                    students[i] = students[j];
                    students[j] = temp;
                }
            }
        }

        System.out.println("\nSorted Students (by marks):");
        for (Student s : students) {
            System.out.println(s);
        }
    }
}
