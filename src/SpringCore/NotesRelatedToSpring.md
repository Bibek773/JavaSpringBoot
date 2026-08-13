# Introduction to Spring Framework

Spring Framework is an open-source Java framework used to develop scalable, maintainable, and enterprise-level applications. It simplifies Java development by providing features such as Inversion of Control (IoC), Dependency Injection (DI), AOP, database integration, transaction management, web development, and testing. The Spring Container manages application objects called **Beans** and their dependencies. Spring reduces boilerplate code and promotes loose coupling, making applications easier to develop, test, and maintain. Spring Boot is built on top of Spring Framework and simplifies its configuration and application setup.

## IoC (Inversion of Control)

Inversion of Control is a principle in which the control of creating and managing objects is given to the Spring Framework instead of doing it manually in our code. Spring uses a container to create, manage, and provide objects when they are needed. This reduces tight coupling between classes and makes the application easier to maintain and test.

## Dependency Injection (DI)

Dependency Injection is a technique where an object gets the dependencies it needs from outside instead of creating them by itself. In Spring, the Spring container creates the required objects and injects them into the class. This reduces tight coupling between classes and makes the application easier to manage, modify, and test.

## Spring Beans

A Spring Bean is an object that is created, managed, and controlled by the Spring container. Instead of creating the object manually using `new`, we let Spring create and manage it for us. Beans are usually used to represent the different components of an application, such as services, repositories, and controllers.

## Bean Scope

Bean scope defines how and when a Spring Bean is created and how long it exists in the Spring container. It determines whether Spring creates a new object every time it is requested or reuses the same object.

Common bean scopes are:

* singleton – one instance is created for the entire Spring container.
* prototype – a new instance is created whenever the bean is requested.
* request – one instance is created for each HTTP request.
* session – one instance is created for each HTTP session.

## ApplicationContext

ApplicationContext is the main container in Spring that creates, manages, and provides beans to the application. It is responsible for dependency injection and also provides features like configuration, event handling, and internationalization. In simple terms, it acts as the central place where Spring manages all the objects used by the application.

## Constructor Injection

Constructor injection is a type of dependency injection where the required dependency is provided through the class constructor. Spring passes the required bean to the constructor when it creates the object. It is commonly preferred because the dependency is available when the object is created and can be made immutable.

```java
public class Car {
    private Engine engine;

    public Car(Engine engine) {
        this.engine = engine;
    }
}
```

## Component Scanning

Component scanning is a feature of Spring that automatically finds classes marked with annotations such as `@Component`, `@Service`, `@Repository`, and `@Controller` and registers them as Spring Beans. This saves us from manually defining each bean in the configuration. Spring Boot usually performs component scanning automatically from the package where the main application class is located.
