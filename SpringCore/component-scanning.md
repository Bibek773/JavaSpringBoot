

## What is Component Scanning?

**Component scanning** is the process Spring uses to automatically find classes that should be managed as Spring Beans.

Spring searches a package and its subpackages for classes marked with annotations such as:

```java
@Component
@Service
@Repository
@Controller
```

For example:

```java
@Service
public class StudentService {
}
```

Spring finds `StudentService` during component scanning and creates a Bean for it.

---

## Stereotype Annotations

These annotations tell Spring that a class should be managed as a Bean.

### `@Component`

Used for a general Spring component.

```java
@Component
public class EmailService {
}
```

### `@Service`

Usually used for business or service logic.

```java
@Service
public class StudentService {
}
```

### `@Repository`

Usually used for database-related classes.

```java
@Repository
public class StudentRepositoryImpl {
}
```

### `@Controller`

Usually used for handling web requests.

```java
@Controller
public class StudentController {
}
```

---

## How Spring Finds the Bean

When the Spring application starts:

```text
Spring starts
    ↓
Component scanning
    ↓
Finds @Component, @Service, @Repository, @Controller
    ↓
Creates the objects
    ↓
Registers them as Beans
```

So we don't need to manually write:

```java
StudentService service = new StudentService();
```

Spring creates and manages the object for us.

---

## Package Structure

Spring Boot normally starts component scanning from the package of the main application class.

Example:

```text
com.bibek.spring_core_demo
│
├── SpringCoreDemoApplication
├── controller
│   └── StudentController
├── service
│   └── StudentService
└── repository
    └── StudentRepositoryImpl
```

Because these packages are under:

```text
com.bibek.spring_core_demo
```

Spring can find the components inside them.

This is why **package placement matters**.

---

## Role of ApplicationContext

`ApplicationContext` is the Spring container that manages the Beans.

It keeps the Beans created by Spring and provides them when they are needed.

For example:

```java
ApplicationContext context =
        SpringApplication.run(SpringCoreDemoApplication.class, args);

StudentService service =
        context.getBean(StudentService.class);
```

Here, `getBean()` asks the `ApplicationContext` for the `StudentService` Bean.

---

## Simple Mental Model

```text
@Service
    ↓
Marks StudentService as a Spring component

Component Scanning
    ↓
Finds StudentService

ApplicationContext
    ↓
Stores and manages the Bean

Dependency Injection
    ↓
Provides the Bean where it is needed
```

### Key Point

> **Stereotype annotations mark classes, component scanning finds them, and ApplicationContext manages the resulting Beans.**
