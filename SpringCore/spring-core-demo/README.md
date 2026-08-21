# Spring Core — How the Project Runs

When the application starts:

```java
SpringApplication.run(SpringCoreDemoApplication.class, args);
```

Spring Boot starts the Spring container.

The container finds the classes marked with Spring annotations and creates their objects as **beans**.

## Main flow

```text
StudentController
       ↓
StudentService
       ↓
StudentRepository
       ↑
StudentRepositoryImpl
```

### `@Component`

```java
@Component
public class StudentController
```

Spring finds the controller and creates it as a bean.

`StudentController` needs `StudentService`, so Spring injects the `StudentService` bean through the constructor.

### `@Service`

```java
@Service
public class StudentService
```

Spring creates `StudentService` as a bean.

Its constructor needs `StudentRepository`, so Spring provides the repository bean.

### `@Repository`

```java
@Repository
public class StudentRepositoryImpl
        implements StudentRepository
```

Spring creates `StudentRepositoryImpl` as a bean.

`StudentService` depends on the `StudentRepository` interface, and Spring provides the `StudentRepositoryImpl` object because it implements that interface.

## `@Configuration` and `@Bean`

**explicitly tell Spring how to create an object:

```java
@Configuration
public class AppConfig {

    @Bean
    public ObjTest objTest() {
        return new ObjTest();
    }
}
```

`@Configuration` tells Spring that this class contains configuration.

`@Bean` tells Spring to manage the object returned by the method.

```text
AppConfig
   ↓
@Bean
   ↓
new ObjTest()
   ↓
Spring manages ObjTest
```

I can get the bean using:

```java
ApplicationContext context =
        new AnnotationConfigApplicationContext(AppConfig.class);

ObjTest objTest = context.getBean(ObjTest.class);
```

## Overall

Without Spring, I would manually create the dependency chain:

```java
StudentRepository repository =
        new StudentRepositoryImpl();

StudentService service =
        new StudentService(repository);

StudentController controller =
        new StudentController(service);
```

With Spring:

```text
Spring Container
      ↓
creates beans
      ↓
finds dependencies
      ↓
injects dependencies
      ↓
StudentController
      ↓
StudentService
      ↓
StudentRepositoryImpl
```

So Spring is handling the **object creation and dependency injection** for me.
