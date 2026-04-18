package BasicAndOop.proggrams.classandobj;


class CopyConst {
    String brand;
    int year;

    // normal constructor
    CopyConst(String brand, int year) {
        this.brand = brand;
        this.year = year;
    }

    // copy constructor
    CopyConst(CopyConst c) {
        this.brand = c.brand;
        this.year = c.year;
    }

    void display() {
        System.out.println(brand + " " + year);
    }

    public static void main(String[] args) {
        CopyConst c1 = new CopyConst("Toyota", 2020);
        CopyConst c2 = new CopyConst(c1); // copy constructor

        c1.display();
        c2.display();
    }
}