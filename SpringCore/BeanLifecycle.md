

## What is Bean Lifecycle?

**Bean Lifecycle** is the sequence of steps a Spring bean goes through from **creation to destruction**.

Simple flow:

```text
Bean Creation
     ↓
Dependency Injection
     ↓
Initialization
     ↓
Bean Ready / In Use
     ↓
Destruction
```

---

# 1. Bean Creation

First, Spring creates the bean object.

Spring finds bean definitions from configuration, such as:

* `@Component`
* `@Service`
* `@Repository`
* `@Controller`
* `@Bean`

Example:

```java
@Service
public class StudentService {
}
```

Spring creates the `StudentService` object and manages it as a bean.

> For singleton beans, this normally happens when the application context starts.

---

# 2. Dependency Injection

After creating the bean, Spring checks whether the bean requires other beans to function.

For example:

```java
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
}
```

Here, `StudentService` requires `StudentRepository`.

Spring:

1. Checks the dependencies of `StudentService`.
2. Creates or obtains the required `StudentRepository` bean.
3. Injects it into `StudentService`.

This process is called **Dependency Injection (DI)**.

### Important

The dependency is injected **after the bean is created but before initialization**.

```text
Create StudentService
        ↓
Inject StudentRepository
        ↓
Initialize StudentService
```

---

# 3. Initialization

After dependency injection is completed, Spring performs the bean's initialization.

Initialization is the **final setup of the bean before it becomes ready to use**.

For example, we can use `@PostConstruct`:

```java
@Service
public class StudentService {

    @PostConstruct
    public void init() {
        System.out.println("StudentService initialized");
    }
}
```

Spring calls the `init()` method after dependencies have been injected.

Initialization can be used for tasks such as:

* Checking required data
* Loading initial configuration
* Preparing resources
* Initializing caches

> Initialization does not create the bean or inject dependencies. Those steps have already happened.

---

# 4. Bean Ready / In Use

After initialization is completed, the bean is **fully initialized and ready to use**.

The application can now use the bean normally.

For example:

```java
studentService.findAll();
```

The bean remains available according to its scope.

For a **singleton bean**, the same instance continues to be used throughout the application context's lifetime.

---

# 5. Destruction

When the Spring application context is closed, Spring starts destroying the beans it manages.

Before destruction, Spring can call a destruction method.

Example:

```java
@Service
public class StudentService {

    @PreDestroy
    public void cleanup() {
        System.out.println("StudentService is being destroyed");
    }
}
```

`@PreDestroy` runs before the bean is destroyed.

Destruction can be used for tasks such as:

* Closing resources
* Cleaning up
* Closing connections

---

# Complete Bean Lifecycle

```text
        Spring Container Starts
                 ↓
          Bean Creation
                 ↓
       Dependency Injection
                 ↓
           Initialization
                 ↓
        Bean Ready / In Use
                 ↓
       Spring Context Closes
                 ↓
            Destruction
                 ↓
          Bean Destroyed
```

---

# Simple Example

```java
@Component
public class StudentService {

    public StudentService() {
        System.out.println("1. Bean Created");
    }

    @PostConstruct
    public void init() {
        System.out.println("2. Bean Initialized");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("3. Bean Destroyed");
    }
}
```

The important conceptual order is:

```text
Creation
   ↓
Dependency Injection
   ↓
Initialization
   ↓
Ready to Use
   ↓
Destruction
```
