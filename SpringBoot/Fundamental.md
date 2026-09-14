# Spring Framework vs Spring Boot

## Spring Framework

Spring Framework is the underlying framework providing features such as:

* IoC
* Dependency Injection
* Beans
* ApplicationContext
* AOP
* Transaction management
* Web development
* Data access


## Spring Boot

Spring Boot sits on top of Spring Framework and simplifies application setup.

| Spring Framework                      | Spring Boot                       |
| ------------------------------------- | --------------------------------- |
| Core framework                        | Built on Spring Framework         |
| More configuration may be required    | Convention and auto-configuration |
| Dependencies configured more manually | Starter dependencies              |
| Server configuration can be external  | Embedded server commonly used     |
| More setup                            | Faster project creation           |
| Flexible                              | Opinionated defaults              |

### Easy way to remember

> **Spring = framework**

> **Spring Boot = easier way to build Spring applications**

---

# What does Spring Boot actually provide?

Spring Boot provides:

### Auto-Configuration

Spring Boot automatically configures many things based on:

* Dependencies present in the project
* Application configuration
* Environment

The Web starter enables automatic configuration for web application development.

---

### Starter Dependencies

Instead of manually selecting many individual libraries, Spring Boot provides **starter dependencies**.

Example:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

This starter brings the dependencies commonly needed for web/REST development.

Common starters:

```text
spring-boot-starter-web
spring-boot-starter-data-jpa
spring-boot-starter-security
spring-boot-starter-test
```

---

### Embedded Server

Spring Boot applications can contain their own web server.

For example:

```text
Spring Boot Application
        +
Embedded Tomcat
        ↓
Run application
        ↓
Server starts
```


For example:

```text
mvn spring-boot:run
```

can start the application.

---

### Externalized Configuration

Spring Boot allows configuration outside Java code.

For example:

```properties
server.port=8081
```

Then the application can run on:

```text
http://localhost:8081
```

---

# Spring Initializr

**Spring Initializr** is a project-generation tool used to create a Spring Boot project with the required basic structure and dependencies.

Think of it as:

> **Project generator for Spring Boot**

Spring Initializr is available through its website and supported IDEs.

The typical choices are:

```text
Project
Language
Spring Boot version
Project Metadata
Packaging
Java version
Dependencies
```

---

# Creating a Spring Boot Project

Suppose the requirement is:

> Create a Spring Boot REST API using Java 21 and Maven.


### Step 1 — Open Spring Initializr

Open:

[Spring Initializr](https://start.spring.io/?utm_source=chatgpt.com)

---

### Step 2 — Select Project

Choose:

```text
Project: Maven
```


---

### Step 3 — Select Language

```text
Language: Java
```

---

### Step 4 — Select Spring Boot Version

Choose a suitable stable Spring Boot version.



---

### Step 5 — Project Metadata

Example:

```text
Group:
com.bibek

Artifact:
student-api

Name:
student-api

Package name:
com.bibek.studentapi
```

### What do these mean?

#### Group

Represents the organization or base namespace.

Example:

```text
com.bibek
```

#### Artifact

The name of the project/application.

Example:

```text
student-api
```

#### Name

Human-readable project name.

#### Package Name

The base Java package.

Example:

```text
com.bibek.studentapi
```

---

# Packaging



```text
Jar
```


### JAR

A JAR packages the application and its required classes/resources.

With an embedded server, the application can be run directly.

Example:

```bash
java -jar student-api.jar
```


# Java Version Compatibility

If the requirement says:

```text
Java 21
```

then select:

```text
Java: 21
```

But there is an important rule:

The Java version must be supported by the selected Spring Boot version.

There are several versions involved:

```text
Installed JDK
        ↓
Spring Boot supported Java versions
        ↓
Project Java version
```

Check the installed Java version with:

```bash
java -version
```

and Maven with:

```bash
mvn -version
```

The Java version used by Maven should match the project configuration.

---

# Maven vs Gradle

Both are **build automation and dependency management tools**.

They can:

* Download dependencies
* Compile Java code
* Run tests
* Package applications
* Execute build tasks

## Maven

Uses:

```text
pom.xml
```

Example:

```text
project
 ├── pom.xml
 └── src
```

## Gradle

Uses files such as:

```text
build.gradle
```

or:

```text
build.gradle.kts
```

### Basic comparison

| Maven                          | Gradle                              |
| ------------------------------ | ----------------------------------- |
| XML-based configuration        | Groovy/Kotlin-based configuration   |
| `pom.xml`                      | `build.gradle` / `build.gradle.kts` |
| Convention-based               | More programmable/flexible          |
| Very common in Java/Spring     | Very common in Java/Spring too      |
| Easier to understand initially | Can be more flexible                |

For this project, use:

```text
Maven
```

> **Maven manages dependencies and builds the project.**

---

# Dependencies

Dependencies are external libraries required by the application.

For a REST API, one important dependency is:

```text
Spring Web
```

In Spring Initializr, select:

```text
Spring Web
```

Initializr adds the required dependency to the project.

Maven resolves and downloads the required JAR files.

---

# What is pom.xml?

`pom.xml` means:

> **Project Object Model**

It is Maven's main project configuration file.

Example:

```xml
<project>

    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
    </parent>

    <groupId>com.bibek</groupId>
    <artifactId>student-api</artifactId>

    <dependencies>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

    </dependencies>

</project>
```

### `groupId`

Identifies the project/group.

```xml
<groupId>com.bibek</groupId>
```

### `artifactId`

Identifies the application/project.

```xml
<artifactId>student-api</artifactId>
```

### `dependencies`

Contains libraries required by the project.

```xml
<dependencies>
    ...
</dependencies>
```

### Dependency

Example:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

This tells Maven:

> "I need Spring Boot's Web starter."

Maven then resolves and downloads the required dependencies.

---

# Project Structure

A generated Spring Boot project will typically look similar to:

```text
student-api/
│
├── .mvn/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── bibek/
│   │   │           └── studentapi/
│   │   │               └── StudentApiApplication.java
│   │   │
│   │   └── resources/
│   │       └── application.properties
│   │
│   └── test/
│
├── pom.xml
├── mvnw
├── mvnw.cmd
└── .gitignore
```

Key files and directories:

```text
pom.xml
    ↓
Maven configuration + dependencies

src/main/java
    ↓
Java source code

src/main/resources
    ↓
Configuration/resources

application.properties
    ↓
Application configuration

StudentApiApplication.java
    ↓
Application entry point
```

---

# Main Application Class

A generated Spring Boot application contains a main class similar to:

```java
@SpringBootApplication
public class StudentApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentApiApplication.class, args);
    }
}
```

The important line is:

```java
SpringApplication.run(StudentApiApplication.class, args);
```

This starts the Spring Boot application.

```text
main()
   ↓
SpringApplication.run()
   ↓
Spring Boot starts
   ↓
Application context starts
   ↓
Embedded server starts if web dependency exists
```

---

# Embedded Server

A traditional Java web application may require:

```text
Application
     ↓
Deploy to
     ↓
External Tomcat
```

Spring Boot can package the server with the application:

```text
Spring Boot Application
        +
Embedded Tomcat
        ↓
Run application
```

Basic development does not require a separate Tomcat installation:

```bash
mvn spring-boot:run
```

or package and run:

```bash
mvn clean package
```

then:

```bash
java -jar target/student-api.jar
```

---

# Default Port

A Spring Boot web application commonly starts on:

```text
8080
```

So:

```text
http://localhost:8080
```

If you configure:

```properties
server.port=8081
```

then:

```text
http://localhost:8081
```

This configuration belongs in:

```text
src/main/resources/application.properties
```

---

# How to Verify the Application

After starting the application, check the console.

The console should show that the application started successfully and the embedded server is running.

Then test:

```text
http://localhost:8080
```

Without a controller or endpoint, the application may return a default error or 404 page.

**That does not necessarily mean Spring Boot failed.**

The important thing at this stage is:

```text
Application starts successfully
        ↓
Server starts
        ↓
Port is listening
```

With a REST controller, verify an actual endpoint.

---

# Complete Workflow

Application workflow:

```text
Requirement
    ↓
"Create Spring Boot REST API"
    ↓
Open Spring Initializr
    ↓
Choose Java
    ↓
Choose Maven
    ↓
Choose compatible Spring Boot version
    ↓
Select Java version
    ↓
Enter project metadata
    ↓
Add required dependencies
    ↓
Generate project
    ↓
Download ZIP
    ↓
Extract
    ↓
Open in IDE
    ↓
Maven loads dependencies
    ↓
Run main application
    ↓
Spring Boot starts
    ↓
Embedded server starts
    ↓
Verify application
```

---

# Practical Example

## Requirement

> Create a REST API using Java 21, Maven and Spring Boot.

### Configuration

```text
Project:
Maven

Language:
Java

Java:
21

Packaging:
Jar

Dependency:
Spring Web
```

Example metadata:

```text
Group:
com.bibek

Artifact:
student-api

Name:
student-api

Package:
com.bibek.studentapi
```

Generate the project.

---

# Run the Application

### Option 1 — IDE

Run:

```java
StudentApiApplication
```

---

### Option 2 — Maven

From the project directory:

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

If Maven is installed globally:

```bash
mvn spring-boot:run
```

---

### Option 3 — Build JAR

```bash
mvn clean package
```

Then:

```bash
java -jar target/student-api-0.0.1-SNAPSHOT.jar
```

---

# Quick Revision

### Spring Boot

> Tool/framework built on Spring Framework that simplifies configuration, dependency setup and application startup.

### Spring Initializr

> Project generator for creating a Spring Boot project.

### Maven

> Build and dependency management tool.

### `pom.xml`

> Maven project configuration file containing project information, dependencies and build configuration.

### Dependency

> External library required by the application.

### Embedded Server

> Server packaged with the application so the application can run without separately deploying it to an external server.

### JAR

> Common packaging format for Spring Boot applications.

### Core workflow

```text
Initializr
    ↓
Dependencies
    ↓
Project
    ↓
Maven
    ↓
Run
    ↓
Embedded Server
    ↓
Verify
```

---

# Phase 1 Practical Test

Create:

```text
Project: product-api
Build: Maven
Language: Java
Java: 21
Packaging: Jar
Dependency: Spring Web
```

Verification steps:

1. Generate the project.
2. Open it in the IDE.
3. Explain the important parts of `pom.xml`.
4. Explain what Maven is doing.
5. Run the application.
6. Identify the embedded server.
7. Identify the default port.
8. Change the port to `8081`.
9. Run it again.
10. Explain the entire startup flow.

