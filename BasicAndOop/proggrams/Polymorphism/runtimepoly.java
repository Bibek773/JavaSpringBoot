package BasicAndOop.proggrams.Polymorphism;



class Animal {
    void sound() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}

class Cat extends Animal {
    @Override
    void sound() {
        System.out.println("Cat meows");
    }
}

public class runtimepoly {
    public static void main(String[] args) {
        Animal a;

        a = new Dog();  // Upcasting
        a.sound();      // Calls Dog's sound()

        a = new Cat();
        a.sound();      // Calls Cat's sound()
    }
}