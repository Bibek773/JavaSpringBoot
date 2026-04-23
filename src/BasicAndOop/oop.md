# Java Core Syntax & Object-Oriented Programming

> A comprehensive reference covering OOP fundamentals, Generics, Exception Handling, and Java 8+ features — with syntax, usage, and practice questions.

---

## Table of Contents

1. [Classes & Objects](#1-classes--objects)
2. [Encapsulation — Getters & Setters](#2-encapsulation--getters--setters)
3. [Inheritance](#3-inheritance)
4. [Polymorphism](#4-polymorphism)
5. [Abstract Classes & Interfaces](#5-abstract-classes--interfaces)
6. [Generics](#6-generics)
7. [Exception Handling](#7-exception-handling)
8. [Java 8+ Features](#8-java-8-features)

---

## 1. Classes & Objects

### What is a Class?

A **class** is a blueprint that defines the structure (fields) and behaviour (methods) of objects.  
An **object** is a concrete instance of that class created at runtime.

### Syntax

```java
// Class definition
public class Car {
    // Fields (instance variables)
    String brand;
    int year;

    // Constructor
    public Car(String brand, int year) {
        this.brand = brand;
        this.year  = year;
    }

    // Method
    public void displayInfo() {
        System.out.println(brand + " (" + year + ")");
    }
}

// Creating objects
Car myCar = new Car("Toyota", 2022);
myCar.displayInfo();  // Toyota (2022)
```

### Key Concepts

| Concept | Description |
|---|---|
| `new` keyword | Allocates memory and calls the constructor |
| `this` keyword | Refers to the current object instance |
| Constructor overloading | Multiple constructors with different parameter lists |
| `static` members | Belong to the class, not any object instance |

### Static vs Instance Members

```java
public class Counter {
    static int count = 0;   // shared across ALL instances
    int id;                  // unique per instance

    public Counter() {
        count++;
        this.id = count;
    }
}

Counter a = new Counter(); // count=1, a.id=1
Counter b = new Counter(); // count=2, b.id=2
```

---

### Practice Questions — Classes & Objects

---

**Q1.** Create a `BankAccount` class with fields `accountNumber`, `owner`, and `balance`. Add a constructor and a `deposit(double amount)` method.

> 📄 [Solution](./proggrams/classandobj/BankAcc.java)

---

**Q2.** What is the difference between a **class variable** (`static`) and an **instance variable**? Give an example of each.

```java
class Student {
    String name;           // instance variable — unique per object
    static int count = 0;  // class variable — shared across all objects

    Student(String name) {
        this.name = name;
        count++;
    }
}
```

**Answer:** An instance variable belongs to an individual object. A static (class) variable belongs to the class itself and is shared among all instances.

---

**Q3.** What happens if you do **not** define any constructor in a class? Can you still instantiate it?

**Answer:** If no constructor is defined, Java automatically provides a default no-argument constructor, so the class can still be instantiated.

---

**Q4.** Write a class `Area` with an overloaded constructor — one accepting both dimensions, and one accepting a single value.

> 📄 [Solution](./proggrams/classandobj/AreaOfObject.java)

---

**Q5.** Explain what `this(...)` does when used inside a constructor.

```java
class Rectangle {
    double width, height;

    Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    Rectangle(double side) {
        this(side, side); // delegates to Rectangle(double, double)
    }
}
```

**Answer:** `this(...)` calls another constructor in the same class, enabling constructor chaining and reducing code duplication.

---

**Q6.** What is a **copy constructor**? Write one for the `Car` class above.

**Answer:** A copy constructor creates a new object by copying the fields of an existing object of the same class.

> 📄 [Example Program](./proggrams/classandobj/CopyConst.java)

---

**Q7.** Can a class in Java be instantiated without using `new`? Describe at least two alternative ways.

**Answer:** Yes — objects can be created without `new` using **reflection**, **cloning**, or **deserialization**, where object creation is handled internally by the JVM.

> 📄 [Reflection Method Example](./proggrams/classandobj/ObjUsingReflect.java)

---

## 2. Encapsulation — Getters & Setters

### What is Encapsulation?

Encapsulation is the practice of **hiding internal state** by making fields `private` and exposing controlled access through `public` getter and setter methods. It protects data integrity and enables validation.

### Syntax

```java
public class Person {
    // Private fields — hidden from outside
    private String name;
    private int age;

    // Getter — read-only access
    public String getName() {
        return name;
    }

    // Setter — write access with validation
    public void setAge(int age) {
        if (age < 0 || age > 150) {
            throw new IllegalArgumentException("Invalid age: " + age);
        }
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be blank");
        }
        this.name = name;
    }
}
```

### Java Records (Java 16+) — Concise Encapsulation

```java
// Immutable data carrier — getters auto-generated, no setters (immutable)
public record Point(double x, double y) {}

Point p = new Point(3.0, 4.0);
System.out.println(p.x()); // 3.0
```

### Access Modifiers Summary

| Modifier | Same Class | Same Package | Subclass | Everywhere |
|---|:---:|:---:|:---:|:---:|
| `private` | ✅ | ❌ | ❌ | ❌ |
| *(default)* | ✅ | ✅ | ❌ | ❌ |
| `protected` | ✅ | ✅ | ✅ | ❌ |
| `public` | ✅ | ✅ | ✅ | ✅ |

---

### Practice Questions — Encapsulation

---

**Q1.** Rewrite the `BankAccount` class from Section 1 so all fields are `private`. Add getters for all fields and a setter for `balance` that prevents negative values.

> 📄 [Solution](./proggrams/Encapsulation/BankAcc.java)

---

**Q2.** What is the difference between a **read-only** property (getter only) and a **read-write** property (getter + setter)?

**Answer:**
- **Read-only:** Has only a getter — the value can be accessed but not modified from outside.
- **Read-write:** Has both getter and setter — the value can be accessed and updated.

---

**Q3.** Why is it bad practice to expose mutable object references directly from a getter? How can you fix it?

**Answer:** Exposing a mutable object allows external code to change the internal state of the class directly, breaking encapsulation.

Fix it by returning a **defensive copy** or an **unmodifiable view**:

```java
// Option 1: Defensive copy
public ArrayList<String> getSubjects() {
    return new ArrayList<>(subjects);
}

// Option 2: Unmodifiable view
public List<String> getSubjects() {
    return Collections.unmodifiableList(subjects);
}
```

---

**Q4.** Can a `private` field be accessed by another object of the **same class**? Write code to demonstrate.

**Answer:** Yes — `private` members are accessible anywhere within the same class, including from other instances of that class.

> 📄 [Example](./proggrams/Encapsulation/AccessOtherObjField.java)

---

**Q5.** What advantage does encapsulation give when you need to change the internal representation of a field later?

**Answer:** Encapsulation lets you change the internal representation without affecting external code, because access is always mediated through getters/setters rather than direct field access.

---

## 3. Inheritance

### What is Inheritance?

Inheritance allows a **subclass** to acquire the fields and methods of a **superclass**, enabling code reuse and establishing an IS-A relationship.

### Syntax

```java
// Superclass (parent)
public class Animal {
    protected String name;

    public Animal(String name) {
        this.name = name;
    }

    public void eat() {
        System.out.println(name + " is eating.");
    }
}

// Subclass (child)
public class Dog extends Animal {
    private String breed;

    public Dog(String name, String breed) {
        super(name);          // call parent constructor
        this.breed = breed;
    }

    public void bark() {
        System.out.println(name + " says: Woof!");
    }

    @Override
    public void eat() {
        System.out.println(name + " (a dog) is eating kibble.");
    }
}

Dog d = new Dog("Rex", "Labrador");
d.eat();   // Rex (a dog) is eating kibble.
d.bark();  // Rex says: Woof!
```

### Key Rules

- Java supports **single inheritance** only (one parent class).
- Use `super.method()` to call an overridden parent method.
- `super(...)` **must** be the first statement in a subclass constructor.
- `final` classes cannot be subclassed; `final` methods cannot be overridden.

### Method Overriding vs Overloading

| Feature | Overriding | Overloading |
|---|---|---|
| Where | Subclass | Same class |
| Signature | Same | Different parameters |
| Annotation | `@Override` | None required |
| Resolved at | Runtime | Compile time |

---

### Practice Questions — Inheritance

---

**Q1.** Create a `Shape` superclass with a field `colour` and a method `area()`. Then create `Circle` and `Triangle` subclasses that override `area()`.

> 📄 [Solution](./proggrams/Inheritance/Inherit.java)

---

**Q2.** What does the `@Override` annotation do? Is it mandatory? Why is it recommended?

**Answer:** `@Override` signals to the compiler that a method is intended to override one from the superclass.

It is **not mandatory**, but strongly recommended — it catches mistakes like typos in method names or mismatched signatures that would silently create a new overloaded method instead of overriding.

---

**Q3.** Why does Java not support multiple class inheritance? What problem does this avoid?

**Answer:** Java avoids multiple inheritance to prevent the **Diamond Problem** — the ambiguity that arises when two parent classes define the same method and the compiler cannot determine which version to use.

---

**Q4.** If a subclass constructor does not call `super(...)`, what happens?

**Answer:** Java automatically inserts a call to the parent's **no-argument constructor**. If the parent has no no-arg constructor, a **compilation error** occurs.

---

**Q5.** Demonstrate how to use `super.method()` to extend (not completely replace) the parent's implementation.

> 📄 [Solution](./proggrams/Inheritance/SuperDemo.java)

---

**Q6.** What is **constructor chaining** and how does it work across a two-level inheritance hierarchy?

**Answer:** Constructor chaining ensures proper initialization by having each constructor invoke another. In a two-level hierarchy, the subclass constructor calls `super()`, which calls the grandparent's constructor — initializing from the top down before the child adds its own setup.

---

**Q7.** Can a subclass reduce the visibility of an overridden method (e.g., override a `public` method as `private`)? Why or why not?

**Answer:** No. Visibility can only be kept the same or increased in an override, never reduced. Reducing visibility would break polymorphism — a parent-type reference could no longer call the method it expects to be accessible.

---
## 4. Polymorphism

### What is Polymorphism?

Polymorphism means "many forms". It allows the **same interface** to be used for different underlying types. In Java, this is achieved through **method overriding** (runtime polymorphism) and **method overloading** (compile-time polymorphism).

### Runtime Polymorphism

```java
Animal a1 = new Dog("Rex", "Lab");
Animal a2 = new Cat("Whiskers");

a1.eat();  // Dog's eat() — resolved at runtime
a2.eat();  // Cat's eat() — resolved at runtime

// Polymorphic list
List<Animal> animals = List.of(new Dog("Rex", "Lab"), new Cat("Whiskers"));
for (Animal a : animals) {
    a.eat();  // correct method called for each type
}
```

### Upcasting & Downcasting

```java
Animal a = new Dog("Rex", "Lab");  // Upcasting (implicit, always safe)

// Downcasting (explicit, can throw ClassCastException)
if (a instanceof Dog d) {          // Pattern matching (Java 16+)
    d.bark();
}
```

---

### Practice Questions — Polymorphism

---

**Q1.** Define runtime polymorphism and compile-time polymorphism. Give a concrete code example of each.

> 📄 [Runtime Polymorphism](./proggrams/Polymorphism/runtimepoly.java)  
> 📄 [Compile-Time Polymorphism](./proggrams/Polymorphism/compiletimepoly.java)

---

**Q2.** Given a `List<Shape>` containing `Circle`, `Rectangle`, and `Triangle` objects, write a loop that prints the area of each shape without using `instanceof`.

> 📄 [Solution](./proggrams/Polymorphism/Areaof.java)

---

**Q3.** What is **dynamic dispatch**? Why is it important for polymorphism?

**Answer:** Dynamic dispatch is the mechanism by which the JVM determines which method implementation to execute at **runtime**, based on the actual object type rather than the declared reference type.

It is fundamental to polymorphism — it allows a single `Shape` reference to call the correct `area()` implementation whether the object is a `Circle`, `Triangle`, or `Rectangle`. Without dynamic dispatch, all calls would resolve to the base class method and polymorphism would not work.

---

**Q4.** What is the difference between **upcasting** and **downcasting**? When can downcasting throw an exception?

**Answer:**

**Upcasting** — converting a subclass object to a superclass reference. It is implicit and always safe.

```java
Shape s = new Circle();  // Circle IS-A Shape — always valid
```

**Downcasting** — converting a superclass reference back to a subclass type. It is explicit and potentially unsafe.

```java
Circle c = (Circle) s;  // must cast manually
```

Downcasting throws a **`ClassCastException`** at runtime if the actual object is not an instance of the target type. For example, casting a `Triangle` reference to `Circle` will fail. Use `instanceof` (or pattern matching in Java 16+) to guard against this:

```java
if (s instanceof Circle c) {
    c.draw();  // safe — no explicit cast needed
}
```

---

**Q5.** What output is produced by the code below, and why?

```java
class A {
    void greet() { System.out.println("Hello from A"); }
}
class B extends A {
    void greet() { System.out.println("Hello from B"); }
}

A obj = new B();
obj.greet();
```

**Output:**
```
Hello from B
```

**Answer:** Even though `obj` is declared as type `A`, the actual object in memory is a `B`. Java uses dynamic dispatch — method calls are resolved based on the **runtime type of the object**, not the declared reference type. So `B`'s `greet()` is called.
## 5. Abstract Classes & Interfaces

### Abstract Classes

An **abstract class** cannot be instantiated directly. It may have both **abstract methods** (no body — subclasses must implement) and **concrete methods** (with body).

```java
public abstract class Vehicle {
    protected int speed;

    // Abstract method — no body
    public abstract void fuelType();

    // Concrete method — shared implementation
    public void accelerate(int amount) {
        speed += amount;
        System.out.println("Speed: " + speed);
    }
}

public class ElectricCar extends Vehicle {
    @Override
    public void fuelType() {
        System.out.println("Electric");
    }
}
```

### Interfaces

An **interface** defines a contract of behaviour. All methods are implicitly `public abstract` unless marked `default` or `static`.

```java
public interface Drawable {
    void draw();                          // abstract

    default void describe() {             // default method (Java 8+)
        System.out.println("I am drawable.");
    }

    static void version() {              // static method (Java 8+)
        System.out.println("Drawable API v1.0");
    }
}

public interface Resizable {
    void resize(double factor);
}

// A class can implement MULTIPLE interfaces
public class Circle extends Shape implements Drawable, Resizable {
    @Override public void draw()              { System.out.println("Drawing circle"); }
    @Override public void resize(double f)    { radius *= f; }
}
```

### Abstract Class vs Interface

| Feature | Abstract Class | Interface |
|---|---|---|
| Instantiation | ❌ | ❌ |
| Fields | Any type | `public static final` only |
| Constructor | ✅ | ❌ |
| Multiple inheritance | ❌ (single) | ✅ (multiple) |
| Method bodies | ✅ | Only `default`/`static` |
| Use when | Shared state + behaviour | Pure contract / multiple roles |

### Practice Questions — Abstract Classes & Interfaces

1. Create an abstract class `Appliance` with abstract method `operate()` and concrete method `powerOn()`. Extend it with `WashingMachine` and `Microwave`.
2. When should you use an **abstract class** over an **interface**? Give a real-world scenario for each.
3. Can an abstract class have a constructor? What is its purpose if the class cannot be instantiated?
4. What happens if a concrete class does not implement all abstract methods from its parent?
5. Write an interface `Sortable` with a `compareTo(Object o)` method and a `default` method `isLessThan(Object o)` that uses it.
6. A class implements two interfaces that both define a `default` method with the same signature. How does Java resolve this conflict?
7. Explain the difference between `interface` and `@FunctionalInterface`. Why does the number of abstract methods matter?

---

## 6. Generics

### What are Generics?
Generics enable **type-safe, reusable code** by parameterising classes, interfaces, and methods with type variables. They eliminate casting and catch type errors at compile time.

### Generic Class

```java
public class Box<T> {
    private T value;

    public Box(T value)  { this.value = value; }
    public T getValue()  { return value; }
}

Box<Integer> intBox    = new Box<>(42);
Box<String>  strBox    = new Box<>("Hello");
```

### Generic Method

```java
public static <T extends Comparable<T>> T max(T a, T b) {
    return a.compareTo(b) >= 0 ? a : b;
}

System.out.println(max(10, 20));      // 20
System.out.println(max("cat","ant")); // cat
```

### Bounded Type Parameters & Wildcards

```java
// Upper bound — T must be Number or a subclass
public static <T extends Number> double sum(List<T> list) {
    return list.stream().mapToDouble(Number::doubleValue).sum();
}

// Wildcard — unknown type
public static void printList(List<?> list) {
    list.forEach(System.out::println);
}

// Upper-bounded wildcard
void process(List<? extends Shape> shapes) { ... }

// Lower-bounded wildcard
void addNumbers(List<? super Integer> list) { ... }
```

### Practice Questions — Generics

1. Create a generic `Pair<A, B>` class that holds two values of different types and provides getters for each.
2. What is **type erasure**? What are its implications at runtime?
3. Explain the difference between `List<?>`, `List<? extends Number>`, and `List<? super Integer>`.
4. Write a generic method `swap(T[] arr, int i, int j)` that swaps two elements in any array.
5. Why can you not create `new T[]` inside a generic class? What is the workaround?
6. What is the difference between a **raw type** (`List`) and a **parameterised type** (`List<String>`)? Why are raw types discouraged?
7. Can a generic class extend a non-generic class? Can it extend another generic class? Show syntax examples.

---

## 7. Exception Handling

### What is Exception Handling?
Exception handling provides a **structured way to respond to runtime errors** without crashing the program. Java uses `try-catch-finally` blocks and a hierarchy of throwable types.

### Exception Hierarchy

```
Throwable
 ├── Error           (JVM-level — do NOT catch: OutOfMemoryError, StackOverflowError)
 └── Exception
      ├── Checked    (must handle or declare: IOException, SQLException)
      └── RuntimeException (unchecked — optional: NullPointerException, IllegalArgumentException)
```

### Basic Syntax

```java
try {
    int result = 10 / 0;                  // throws ArithmeticException
} catch (ArithmeticException e) {
    System.out.println("Math error: " + e.getMessage());
} catch (Exception e) {                   // catch broader types last
    System.out.println("Unexpected: " + e);
} finally {
    System.out.println("Always runs");    // cleanup code
}
```

### Multi-catch & Try-with-Resources

```java
// Multi-catch (Java 7+)
try {
    ...
} catch (IOException | SQLException e) {
    System.out.println("IO or DB error: " + e);
}

// Try-with-resources — auto-closes AutoCloseable objects
try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
    String line = br.readLine();
} catch (IOException e) {
    e.printStackTrace();
}
```

### Custom Exceptions

```java
// Checked custom exception
public class InsufficientFundsException extends Exception {
    private double amount;

    public InsufficientFundsException(double amount) {
        super("Insufficient funds. Short by: " + amount);
        this.amount = amount;
    }

    public double getAmount() { return amount; }
}

// Usage
public void withdraw(double amount) throws InsufficientFundsException {
    if (amount > balance) {
        throw new InsufficientFundsException(amount - balance);
    }
    balance -= amount;
}
```

### Practice Questions — Exception Handling

1. What is the difference between **checked** and **unchecked** exceptions? Give two examples of each.
2. What is the role of the `finally` block? Can it be omitted? Does it run if a `return` statement is in the `try` block?
3. Rewrite the `withdraw()` method above to throw an **unchecked** `InsufficientFundsException`. When is this preferable?
4. What is **exception chaining**? Use `new RuntimeException("msg", cause)` in an example.
5. Why should you avoid catching `Exception` or `Throwable` broadly?
6. What happens if an exception is thrown inside a `finally` block that is already handling another exception?
7. Create a custom checked exception `InvalidAgeException` and use it in a `Person.setAge()` method. Write the calling code showing proper `try-catch`.

---

## 8. Java 8+ Features

### 8.1 Lambda Expressions

A **lambda** is an anonymous function — a concise way to implement a **functional interface**.

```java
// Old way
Runnable r = new Runnable() {
    public void run() { System.out.println("Running"); }
};

// Lambda
Runnable r = () -> System.out.println("Running");

// With parameters
Comparator<String> comp = (a, b) -> a.compareTo(b);

// Block body
Comparator<Integer> cmp = (a, b) -> {
    System.out.println("Comparing " + a + " and " + b);
    return Integer.compare(a, b);
};
```

### Common Functional Interfaces

| Interface | Signature | Use |
|---|---|---|
| `Predicate<T>` | `boolean test(T t)` | Filtering |
| `Function<T,R>` | `R apply(T t)` | Mapping / transforming |
| `Consumer<T>` | `void accept(T t)` | Performing side-effects |
| `Supplier<T>` | `T get()` | Providing values |
| `BiFunction<T,U,R>` | `R apply(T t, U u)` | Two-argument mapping |

### Method References

```java
List<String> names = List.of("Alice", "Bob", "Charlie");

names.forEach(System.out::println);         // instance method ref
names.stream().map(String::toUpperCase)...  // instance method on element
names.stream().sorted(String::compareTo)... // instance method as comparator

// Constructor reference
Supplier<ArrayList<String>> s = ArrayList::new;
```

---

### 8.2 Streams API

Streams provide **declarative, pipeline-based** processing of sequences of elements.

```java
List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

// filter → map → collect
List<Integer> result = numbers.stream()
    .filter(n -> n % 2 == 0)         // 2,4,6,8,10
    .map(n -> n * n)                 // 4,16,36,64,100
    .collect(Collectors.toList());

// reduce
int sum = numbers.stream()
    .reduce(0, Integer::sum);        // 55

// groupingBy
Map<Boolean, List<Integer>> grouped = numbers.stream()
    .collect(Collectors.partitioningBy(n -> n % 2 == 0));

// flatMap
List<List<Integer>> nested = List.of(List.of(1,2), List.of(3,4));
List<Integer> flat = nested.stream()
    .flatMap(Collection::stream)
    .collect(Collectors.toList());   // [1,2,3,4]
```

### Intermediate vs Terminal Operations

| Type | Examples | Returns |
|---|---|---|
| Intermediate (lazy) | `filter`, `map`, `sorted`, `distinct`, `limit`, `flatMap` | `Stream<T>` |
| Terminal (eager) | `collect`, `forEach`, `reduce`, `count`, `findFirst`, `anyMatch` | Value / side-effect |

---

### 8.3 Optional

`Optional<T>` is a container that **may or may not hold a value** — it replaces `null` and prevents `NullPointerException`.

```java
Optional<String> opt = Optional.of("Hello");
Optional<String> empty = Optional.empty();
Optional<String> nullable = Optional.ofNullable(null); // empty

// Checking & unwrapping
opt.isPresent();                          // true
opt.get();                                // "Hello" (throws if empty)
opt.orElse("default");                   // "Hello"
opt.orElseGet(() -> "computed default"); // lazy fallback
opt.orElseThrow(() -> new RuntimeException("No value"));

// Transforming
Optional<Integer> length = opt.map(String::length);        // Optional[5]
Optional<String> filtered = opt.filter(s -> s.length() > 3); // Optional[Hello]

// Chaining — avoid nested Optionals
Optional<String> city = findUser(id)
    .flatMap(User::getAddress)
    .map(Address::getCity);
```

---

### Practice Questions — Java 8+ Features

**Lambdas**
1. Rewrite this anonymous class as a lambda: `Comparator<String> c = new Comparator<>() { public int compare(String a, String b) { return a.length() - b.length(); } };`
2. What is a **functional interface**? Can it have more than one abstract method? What does `@FunctionalInterface` guarantee?
3. Write a `Predicate<Integer>` that returns `true` for even numbers and compose it with another `Predicate` for numbers greater than 10 using `and()`.

**Streams**

4. Given `List<String> words`, write a stream pipeline that returns a sorted list of distinct words longer than 4 characters, all in uppercase.
5. What is the difference between `map()` and `flatMap()`? Show an example where only `flatMap()` works correctly.
6. Explain **lazy evaluation** in streams. Why does the following code NOT print anything?
```java
Stream<Integer> s = Stream.of(1, 2, 3).filter(n -> { System.out.println(n); return n > 1; });
```
7. Use `Collectors.groupingBy` to group a `List<Person>` by their `department` field into a `Map<String, List<Person>>`.
8. What is the difference between `findFirst()` and `findAny()`? When does it matter?

**Optional**

9. Why is `optional.get()` considered bad practice? What should you use instead?
10. Rewrite this null-checking code using `Optional`:
```java
String city = null;
User user = findUser(id);
if (user != null) {
    Address addr = user.getAddress();
    if (addr != null) {
        city = addr.getCity();
    }
}
```
11. Can `Optional` be used as a **method parameter** type? What are the arguments against it?
12. Write a method that accepts an `Optional<String>` email, validates it contains `@`, and returns an `Optional<String>` with the domain part only.

---

## Quick Reference Card

```
OOP Pillars
  Encapsulation  → private fields + public getters/setters
  Inheritance    → extends (single), super(), @Override
  Polymorphism   → runtime dispatch, upcasting/downcasting
  Abstraction    → abstract class | interface

Generics
  <T>                    → type parameter
  <T extends Bound>      → upper bound
  <? extends T>          → read-only wildcard
  <? super T>            → write-friendly wildcard

Exceptions
  try / catch / finally / throw / throws
  checked  → must handle (IOException)
  unchecked → optional (RuntimeException)

Java 8+
  Lambda           (a, b) -> expr
  Method ref       Class::method
  Stream           .filter().map().collect()
  Optional         .orElse() .map() .flatMap()
```

