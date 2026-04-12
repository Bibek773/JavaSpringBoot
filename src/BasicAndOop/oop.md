

## 1. Java Basics 

Java is:

* **Compiled + Interpreted** (compiled to bytecode → runs on JVM)
* **Object-Oriented**
* **Platform Independent** (WORA – Write Once Run Anywhere)

---

## 2. Variables and Types

### Syntax

```java
type variableName = value;
```

### Categories

#### Primitive Types

* Stored directly
* Fixed size
* Faster

Types:
`int, double, float, char, boolean, byte, short, long`

#### Reference Types

* Store address of object (heap)
* Examples: `String`, Arrays, Objects

---

### Key Insight

* Primitive → copy value
* Reference → copy address

---

## 3. Control Flow

### Conditional

```java
if (condition) { }
else { }

switch(expression) { case ... }
```

### Loops

```java
for(initialization; condition; update) { }

while(condition) { }

do { } while(condition);
```

---

## 4. Class and Object

### Class Syntax

```java
class ClassName {
    // fields
    // methods
}
```

### Object Creation

```java
ClassName obj = new ClassName();
```

---

* Class = blueprint
* Object = memory allocation + behavior instance

---

## 5. Fields and Methods

### Field

```java
type fieldName;
```

### Method

```java
returnType methodName(parameters) {
    // logic
}
```

---

### Important

* Fields → state
* Methods → behavior
* Methods operate on fields

---

## 6. Access Modifiers

### Syntax

```java
modifier type variableName;
modifier returnType methodName() { }
```

### Types

* `private` → only inside class
* `default` → same package
* `protected` → package + subclass
* `public` → everywhere

---

### Key Insight

* Access modifiers = **control visibility**
* Core for encapsulation

---

## 7. Static vs Instance

### Static

```java
static type variableName;
static returnType methodName() { }
```

* Belongs to class
* Loaded once (class loading time)
* Cannot access non-static directly

---

### Instance (Non-static)

```java
type variableName;
returnType methodName() { }
```

* Belongs to object
* Each object gets its own copy

---

### Critical Rule

* Static method → cannot use instance variables directly
* Instance method → can use static

---

## 8. Memory Model

### Stack

* Stores:

    * Method calls
    * Local variables
    * References

### Heap

* Stores:

    * Objects
    * Instance variables

---

### Important Insight

* Object lives in heap
* Reference lives in stack

---

## 9. Constructor

### Syntax

```java
ClassName(parameters) {
    // initialization
}
```

---

### Types

#### Default Constructor

* Provided by compiler (only if none exists)

#### No-Argument Constructor

```java
ClassName() { }
```

#### Parameterized Constructor

```java
ClassName(type param) { }
```

---

### Constructor Overloading

```java
ClassName() { }
ClassName(type a) { }
ClassName(type a, type b) { }
```

---

### Key Rules

* No return type
* Called automatically
* Can use `this()` for chaining

---

## 10. `this` Keyword

### Uses

```java
this.variable
this.method()
this()
```

---

### Purpose

* Refer current object
* Resolve ambiguity
* Call constructor

---

## 11. Encapsulation

### Concept

* Restrict direct access
* Expose controlled methods

---

### Syntax

```java
private type variable;

public type getVariable() { }
public void setVariable(type value) { }
```

---

### Key Insight

* Validation happens in setter
* Not just hiding data → controlling behavior

---

## 12. Inheritance

### Syntax

```java
class Child extends Parent { }
```

---

### Types

* Single
* Multilevel
* Hierarchical

---

### Key Insight

* “is-a” relationship
* Promotes reuse
* Avoid deep inheritance chains (fragile design)

---

## 13. `super` Keyword

### Syntax

```java
super.variable;
super.method();
super();
```

---

### Use Cases

* Access parent variable
* Call parent method
* Call parent constructor

---

### Important Rule

* `super()` must be first statement in constructor

---

## 14. Polymorphism

### Compile-Time (Overloading)

```java
method(type a)
method(type a, type b)
```

* Resolved at compile time

---

### Run-Time (Overriding)

```java
@Override
returnType methodName() { }
```

---

### Rules of Overriding

* Same method signature
* Cannot reduce access level
* Return type must be compatible

---

### Dynamic Dispatch

* Method call decided at runtime
* Based on actual object

---

## 15. Abstraction

### Abstract Class Syntax

```java
abstract class ClassName {
    abstract returnType methodName();
}
```

---

### Key Points

* Cannot instantiate
* Can have concrete + abstract methods

---

## 16. Interface

### Syntax

```java
interface InterfaceName {
    returnType methodName();
}
```

---

### Implementation

```java
class ClassName implements InterfaceName { }
```

---

### Java 8 Additions

* `default` methods
* `static` methods

---

### Key Insight

* Interface = contract
* Enables multiple inheritance

---

## 17. final Keyword

### Syntax

```java
final type variable;
final class ClassName { }
final returnType method() { }
```

---

### Meaning

* Variable → constant
* Method → cannot override
* Class → cannot inherit

---

## 18. Exception Handling

### Syntax

```java
try { }
catch(ExceptionType e) { }
finally { }
```

---

### Throwing

```java
throw new Exception();
```

### Declaring

```java
method() throws Exception { }
```

---

### Types

* Checked → must handle
* Unchecked → optional

---

### Key Insight

* Exceptions = control flow for errors
* Avoid overusing try-catch

---

## 19. Generics

### Syntax

```java
class ClassName<T> { }
```

---

### Usage

```java
T variable;
```

---

### Benefits

* Type safety
* No casting
* Reusability

---

### Key Insight

* Generics work only with reference types (not primitives)

---

## 20. Java 8 Features

---

### Lambda Expression

### Syntax

```java
(parameters) -> expression
```

---

### Functional Interface

* Interface with one abstract method

---

### Streams

### Syntax

```java
collection.stream()
```

Operations:

* `filter()`
* `map()`
* `reduce()`

---

### Key Insight

* Streams = functional-style data processing
* Do not store data, process it

---

### Optional

### Syntax

```java
Optional<Type> obj;
```

---

### Purpose

* Avoid null checks
* Represent optional value

---

