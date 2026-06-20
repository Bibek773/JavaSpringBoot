# Annotations


## `@SpringBootApplication`

**Annotation:**  
`@SpringBootApplication`

**Used in:**  
Main class of a Spring Boot application.

**Purpose:**  
Used to mark the starting point of a Spring Boot project.

It combines three important annotations:

- `@Configuration` → marks the class as a configuration class
- `@EnableAutoConfiguration` → enables Spring Boot auto-configuration
- `@ComponentScan` → scans components like `@Controller`, `@Service`, `@Repository`, `@Component`

**Example:**

```java
package com.project.StudentManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentManagementApplication.class, args);
    }
}
```

**Common mistake:**  

Placing the main class in the wrong package.

Example wrong structure:

```text
com.project.StudentManagement.config
    StudentManagementApplication.java
com.project.StudentManagement.controller
    UserController.java
```

If `@SpringBootApplication` is inside `config`, Spring may not scan `controller`, `service`, or `repository` packages properly.

Better structure:

```text
com.project.StudentManagement
    StudentManagementApplication.java

com.project.StudentManagement.controller
com.project.StudentManagement.service
com.project.StudentManagement.repository
com.project.StudentManagement.model
```

---

## `@Configuration`

**Annotation:**  
`@Configuration`

**Used in:**  
Class level.

**Purpose:**  
Used to tell Spring that this class contains bean definitions or configuration code.

A class marked with `@Configuration` can contain methods marked with `@Bean`.

Spring will read this class and create/manage the objects returned by `@Bean` methods.

**Example:**

```java
package com.project.StudentManagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

**Common mistake:**  

Forgetting to add `@Configuration` on a config class.

Wrong:

```java
public class SpringConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

Here, Spring may not detect this class as a configuration class, so the `PasswordEncoder` bean may not be created.

Correct:

```java
@Configuration
public class SpringConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

**Simple meaning:**  
`@Configuration` means:

```text
This class gives configuration/settings/beans to Spring.
```

---

## `@Bean`

**Annotation:**  
`@Bean`

**Used in:**  
Method level, usually inside a `@Configuration` class.

**Purpose:**  
Used to manually create an object and give it to the Spring container.

Spring will manage that object as a bean, so it can be injected using `@Autowired` or constructor injection.

**Example:**

```java
package com.project.StudentManagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

Now Spring can inject `PasswordEncoder` wherever needed:

```java
@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
```

**Common mistake:**

Creating a method but forgetting `@Bean`.

Wrong:

```java
@Configuration
public class SpringConfig {

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

Here, Spring will not manage `PasswordEncoder`.

Correct:

```java
@Configuration
public class SpringConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

**Simple meaning:**

```text
@Bean means:
Create this object and let Spring manage it.
```
## `@Entity`

**Annotation:**  
`@Entity`

**Used in:**  
Class level.

**Purpose:**  
Used to tell Spring/JPA that this class represents a database table.

When a class is marked with `@Entity`, JPA maps that class to a table in the database.

Each object of that class represents one row in the table.

**Example:**

```java
package com.project.StudentManagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    // getters and setters
}
```

In database, this may create a table like:

```text
users
--------------------------------
id | username | password
```

**Common mistake:**

Forgetting to add `@Id`.

Wrong:

```java
@Entity
public class Users {

    private Long id;
    private String username;
    private String password;
}
```

Here, JPA will give an error because every entity must have a primary key.

Correct:

```java
@Entity
public class Users {

    @Id
    private Long id;

    private String username;
    private String password;
}
```

**Simple meaning:**

```text
@Entity means:
This Java class is connected to a database table.
```
## `@Table`

**Annotation:**  
`@Table`

**Used in:**  
Class level, usually with `@Entity`.

**Purpose:**  
Used to specify the database table name for an entity class.

If you do not use `@Table`, JPA will automatically use the class name as the table name.

**Example:**

```java
package com.project.StudentManagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    // getters and setters
}
```

Here, the `Users` class is connected to the `users` table in the database.

```text
users
--------------------------------
id | username | password
```

**Common mistake:**

Using `@Table` without `@Entity`.

Wrong:

```java
@Table(name = "users")
public class Users {

    private Long id;
    private String username;
    private String password;
}
```

Here, JPA will not treat this class as a database entity.

Correct:

```java
@Entity
@Table(name = "users")
public class Users {

    @Id
    private Long id;

    private String username;
    private String password;
}
```

**Simple meaning:**

```text
@Table means:
Use this specific database table for this entity class.
```
## `@Id`

**Annotation:**  
`@Id`

**Used in:**  
Field level.

**Purpose:**  
Used to mark a field as the primary key of an entity/table.

Every `@Entity` class must have one primary key field marked with `@Id`.

**Example:**

```java
package com.project.StudentManagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    // getters and setters
}
```

Here, `id` is the primary key of the `Users` entity.

```text
users
--------------------------------
id | username | password
```

**Common mistake:**

Writing `@ID` instead of `@Id`.

Wrong:

```java
@Entity
public class Users {

    @ID
    private Long id;

    private String username;
}
```

Java annotations are case-sensitive, so `@ID` is wrong.

Correct:

```java
@Entity
public class Users {

    @Id
    private Long id;

    private String username;
}
```

**Simple meaning:**

```text
@Id means:
This field uniquely identifies each row in the table.
```
## `@GeneratedValue`

**Annotation:**  
`@GeneratedValue`

**Used in:**  
Field level, usually with `@Id`.

**Purpose:**  
Used to automatically generate the primary key value.

It is commonly used for auto-increment IDs in database tables.

**Example:**

```java
package com.project.StudentManagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

    // getters and setters
}
```

Here, the `id` value is generated automatically by the database.

```text
users
--------------------------------
id | username | password
1  | bibek    | 12345
2  | ram      | 54321
```

**Common mistake:**

Writing the annotation name incorrectly.

Wrong:

```java
@GnenerateValue
private Long id;
```

Also wrong:

```java
@GenerateValue
private Long id;
```

Correct:

```java
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
```

**Simple meaning:**

```text
@GeneratedValue means:
Automatically create the ID value for each new row.
```
## `@Column`

**Annotation:**  
`@Column`

**Used in:**  
Field level.

**Purpose:**  
Used to customize the database column mapped with an entity field.

By default, JPA uses the field name as the column name.  
`@Column` is used when you want to define column name, length, uniqueness, nullable rules, etc.

**Example:**

```java
package com.project.StudentManagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    // getters and setters
}
```

Here, `username` is mapped to the `username` column in the database.

```text
users
--------------------------------
id | username | password
1  | bibek    | 12345
```

**Common mistake:**

Thinking `@Column(nullable = false)` is enough for validation.

Wrong assumption:

```java
@Column(nullable = false)
private String username;
```

This controls the database column, but it does not properly validate user input before saving.

Better:

```java
@Column(nullable = false)
private String username;
```

And use validation separately when needed:

```java
@NotBlank
private String username;
```

**Simple meaning:**

```text
@Column means:
Customize how this field is stored as a database column.
```
## `@RestController`

**Annotation:**  
`@RestController`

**Used in:**  
Class level.

**Purpose:**  
Used to create REST APIs in Spring Boot.

It tells Spring that this class will handle HTTP requests and return data directly as response, usually in JSON format.

`@RestController` is a combination of:

- `@Controller`
- `@ResponseBody`

So, you do not need to write `@ResponseBody` separately on every method.

**Example:**

```java
package com.project.StudentManagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello Spring Boot";
    }
}
```

When you visit:

```text
http://localhost:8080/hello
```

Output:

```text
Hello Spring Boot
```

**JSON Example:**

```java
@RestController
public class UserController {

    @GetMapping("/user")
    public User getUser() {
        return new User(1L, "bibek");
    }
}
```

Response:

```json
{
  "id": 1,
  "username": "bibek"
}
```

**Common mistake:**

Using `@RestController` when you want to return an HTML page.

Wrong:

```java
@RestController
public class PageController {

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
```

This returns plain text:

```text
home
```

It does not open `home.html`.

Correct for HTML page:

```java
@Controller
public class PageController {

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
```

Correct for API response:

```java
@RestController
public class UserController {

    @GetMapping("/api/users")
    public String users() {
        return "User data";
    }
}
```

**Simple meaning:**

```text
@RestController means:
This class handles API requests and returns data directly.
```

## `@RequestMapping`

**Annotation:**  
`@RequestMapping`

**Used in:**  
Class level and method level.

**Purpose:**  
Used to map HTTP requests to a controller class or controller method.

It can handle different HTTP methods like:

- `GET`
- `POST`
- `PUT`
- `DELETE`
- `PATCH`

But in real projects, we usually use shortcut annotations like:

- `@GetMapping`
- `@PostMapping`
- `@PutMapping`
- `@DeleteMapping`

**Example:**

```java
package com.project.StudentManagement.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello User";
    }
}
```

When you visit:

```text
http://localhost:8080/api/users/hello
```

Output:

```text
Hello User
```

**Example with HTTP method:**

```java
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String getAllUsers() {
        return "All users";
    }
}
```

Better modern way:

```java
@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/all")
    public String getAllUsers() {
        return "All users";
    }
}
```

**Common mistake:**

Writing `@requestMapping` instead of `@RequestMapping`.

Wrong:

```java
@requestMapping("/api/users")
public class UserController {
}
```

Java annotations are case-sensitive, so this is wrong.

Correct:

```java
@RequestMapping("/api/users")
public class UserController {
}
```

**Simple meaning:**

```text
@RequestMapping means:
Connect this URL path with this controller or method.
```

## `@GetMapping`

**Annotation:**  
`@GetMapping`

**Used in:**  
Method level.

**Purpose:**  
Used to handle HTTP `GET` requests.

`GET` is mainly used to fetch/read data from the server.

`@GetMapping` is a shortcut form of:

```java
@RequestMapping(value = "/path", method = RequestMethod.GET)
```

**Example:**

```java
package com.project.StudentManagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello Spring Boot";
    }
}
```

When you visit:

```text
http://localhost:8080/hello
```

Output:

```text
Hello Spring Boot
```

**Example with base path:**

```java
package com.project.StudentManagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/all")
    public String getAllUsers() {
        return "All users";
    }
}
```

Final URL:

```text
http://localhost:8080/api/users/all
```

**Common mistake:**

Using `@GetMapping` for saving data.

Wrong:

```java
@GetMapping("/save")
public String saveUser() {
    return "User saved";
}
```

`GET` should mainly be used to read/fetch data, not to save or modify data.

Correct:

```java
@PostMapping("/save")
public String saveUser() {
    return "User saved";
}
```

**Simple meaning:**

```text
@GetMapping means:
Run this method when a GET request comes to this URL.
```
## `@PostMapping`

**Annotation:**  
`@PostMapping`

**Used in:**  
Method level.

**Purpose:**  
Used to handle HTTP `POST` requests.

`POST` is mainly used to send/create/save data on the server.

`@PostMapping` is a shortcut form of:

```java
@RequestMapping(value = "/path", method = RequestMethod.POST)
```

**Example:**

```java
package com.project.StudentManagement.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/save")
    public String saveUser() {
        return "User saved successfully";
    }
}
```

When a `POST` request is sent to:

```text
http://localhost:8080/save
```

Output:

```text
User saved successfully
```

**Example with request body:**

```java
package com.project.StudentManagement.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/users")
    public String createUser(@RequestBody User user) {
        return "User created: " + user.getUsername();
    }
}
```

Request body:

```json
{
  "username": "bibek",
  "password": "12345"
}
```

**Common mistake:**

Writing `@postmapping` instead of `@PostMapping`.

Wrong:

```java
@postmapping("/users")
public String createUser() {
    return "User created";
}
```

Java annotations are case-sensitive, so this is wrong.

Correct:

```java
@PostMapping("/users")
public String createUser() {
    return "User created";
}
```

**Another common mistake:**

Using `@PostMapping` without `@RequestBody` when receiving JSON data.

Wrong:

```java
@PostMapping("/users")
public String createUser(User user) {
    return "User created";
}
```

Correct:

```java
@PostMapping("/users")
public String createUser(@RequestBody User user) {
    return "User created";
}
```

**Simple meaning:**

```text
@PostMapping means:
Run this method when a POST request comes to this URL.
```
## `@PutMapping`

**Annotation:**  
`@PutMapping`

**Used in:**  
Method level.

**Purpose:**  
Used to handle HTTP `PUT` requests.

`PUT` is mainly used to update existing data on the server.

`@PutMapping` is a shortcut form of:

```java
@RequestMapping(value = "/path", method = RequestMethod.PUT)
```

**Example:**

```java
package com.project.StudentManagement.controller;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PutMapping("/update")
    public String updateUser() {
        return "User updated successfully";
    }
}
```

When a `PUT` request is sent to:

```text
http://localhost:8080/update
```

Output:

```text
User updated successfully
```

**Example with path variable and request body:**

```java
package com.project.StudentManagement.controller;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PutMapping("/users/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody User user) {
        return "User with ID " + id + " updated";
    }
}
```

Request URL:

```text
http://localhost:8080/users/1
```

Request body:

```json
{
  "username": "bibek",
  "password": "newpassword"
}
```

**Common mistake:**

Writing `@putmapping` instead of `@PutMapping`.

Wrong:

```java
@putmapping("/users/{id}")
public String updateUser() {
    return "User updated";
}
```

Java annotations are case-sensitive, so this is wrong.

Correct:

```java
@PutMapping("/users/{id}")
public String updateUser() {
    return "User updated";
}
```

**Another common mistake:**

Using `@PostMapping` for updating data.

Not best:

```java
@PostMapping("/users/{id}")
public String updateUser(@PathVariable Long id) {
    return "User updated";
}
```

Better:

```java
@PutMapping("/users/{id}")
public String updateUser(@PathVariable Long id) {
    return "User updated";
}
```

**Simple meaning:**

```text
@PutMapping means:
Run this method when a PUT request comes to this URL.
```
## `@DeleteMapping`

**Annotation:**  
`@DeleteMapping`

**Used in:**  
Method level.

**Purpose:**  
Used to handle HTTP `DELETE` requests.

`DELETE` is mainly used to remove/delete existing data from the server.

`@DeleteMapping` is a shortcut form of:

```java
@RequestMapping(value = "/path", method = RequestMethod.DELETE)
```

**Example:**

```java
package com.project.StudentManagement.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        return "User with ID " + id + " deleted";
    }
}
```

Request URL:

```text
http://localhost:8080/users/1
```

Output:

```text
User with ID 1 deleted
```

**Example with base path:**

```java
package com.project.StudentManagement.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        return "Deleted user with ID: " + id;
    }
}
```

Final URL:

```text
http://localhost:8080/api/users/1
```

**Common mistake:**

Writing `@deletemapping` instead of `@DeleteMapping`.

Wrong:

```java
@deletemapping("/users/{id}")
public String deleteUser() {
    return "User deleted";
}
```

Java annotations are case-sensitive, so this is wrong.

Correct:

```java
@DeleteMapping("/users/{id}")
public String deleteUser() {
    return "User deleted";
}
```

**Another common mistake:**

Deleting without identifying which data to delete.

Wrong:

```java
@DeleteMapping("/users")
public String deleteUser() {
    return "User deleted";
}
```

Better:

```java
@DeleteMapping("/users/{id}")
public String deleteUser(@PathVariable Long id) {
    return "User deleted";
}
```

**Simple meaning:**

```text
@DeleteMapping means:
Run this method when a DELETE request comes to this URL.
```
## `@RequestBody`

**Annotation:**  
`@RequestBody`

**Used in:**  
Method parameter level.

**Purpose:**  
Used to receive data from the HTTP request body.

It is commonly used with `POST`, `PUT`, and `PATCH` requests when the client sends JSON data.

Spring automatically converts the JSON request body into a Java object.

**Example:**

```java
package com.project.StudentManagement.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/users")
    public String createUser(@RequestBody User user) {
        return "User created: " + user.getUsername();
    }
}
```

Request body:

```json
{
  "username": "bibek",
  "password": "12345"
}
```

Here, the JSON data is converted into a `User` object.

**Example with `PUT`:**

```java
@PutMapping("/users/{id}")
public String updateUser(@PathVariable Long id, @RequestBody User user) {
    return "User with ID " + id + " updated";
}
```

Request body:

```json
{
  "username": "bibek",
  "password": "newpassword"
}
```

**Common mistake:**

Writing `@requestbody` instead of `@RequestBody`.

Wrong:

```java
@PostMapping("/users")
public String createUser(@requestbody User user) {
    return "User created";
}
```

Java annotations are case-sensitive, so this is wrong.

Correct:

```java
@PostMapping("/users")
public String createUser(@RequestBody User user) {
    return "User created";
}
```

**Another common mistake:**

Using `@RequestBody` with form-data or URL parameters.

Wrong for URL parameter:

```java
@PostMapping("/login")
public String login(@RequestBody String username) {
    return "Login";
}
```

Better for URL/form parameter:

```java
@PostMapping("/login")
public String login(@RequestParam String username) {
    return "Login";
}
```

**Simple meaning:**

```text
@RequestBody means:
Take JSON/data from the request body and convert it into a Java object.
```
## `@PathVariable`

**Annotation:**  
`@PathVariable`

**Used in:**  
Method parameter level.

**Purpose:**  
Used to get value from the URL path.

It is commonly used when the URL contains dynamic values like `id`, `username`, `rollNo`, etc.

**Example:**

```java
package com.project.StudentManagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/users/{id}")
    public String getUserById(@PathVariable Long id) {
        return "User ID is: " + id;
    }
}
```

Request URL:

```text
http://localhost:8080/users/1
```

Output:

```text
User ID is: 1
```

Here, `{id}` from the URL is stored in the `id` variable.

**Example with `DELETE`:**

```java
@DeleteMapping("/users/{id}")
public String deleteUser(@PathVariable Long id) {
    return "User with ID " + id + " deleted";
}
```

Request URL:

```text
http://localhost:8080/users/5
```

Output:

```text
User with ID 5 deleted
```

**Example with different variable name:**

```java
@GetMapping("/users/{userId}")
public String getUser(@PathVariable("userId") Long id) {
    return "User ID: " + id;
}
```

Here, URL variable name is `userId`, but Java variable name is `id`.

**Common mistake:**

Writing `@PathVaraible` instead of `@PathVariable`.

Wrong:

```java
@GetMapping("/users/{id}")
public String getUser(@PathVaraible Long id) {
    return "User ID: " + id;
}
```

Correct:

```java
@GetMapping("/users/{id}")
public String getUser(@PathVariable Long id) {
    return "User ID: " + id;
}
```

**Another common mistake:**

Mismatch between URL variable and method parameter.

Wrong:

```java
@GetMapping("/users/{id}")
public String getUser(@PathVariable Long userId) {
    return "User ID: " + userId;
}
```

Better:

```java
@GetMapping("/users/{id}")
public String getUser(@PathVariable Long id) {
    return "User ID: " + id;
}
```

Or:

```java
@GetMapping("/users/{id}")
public String getUser(@PathVariable("id") Long userId) {
    return "User ID: " + userId;
}
```

**Simple meaning:**

```text
@PathVariable means:
Take value from the URL path and store it in a method parameter.
```

## `@RequestParam`

**Annotation:**  
`@RequestParam`

**Used in:**  
Method parameter level.

**Purpose:**  
Used to get value from query parameters or form parameters.

It is commonly used when data is sent in the URL after `?`.

**Example:**

```java
package com.project.StudentManagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/users")
    public String getUser(@RequestParam String username) {
        return "Username is: " + username;
    }
}
```

Request URL:

```text
http://localhost:8080/users?username=bibek
```

Output:

```text
Username is: bibek
```

Here, `username=bibek` from the URL is stored in the `username` variable.

**Example with multiple parameters:**

```java
@GetMapping("/search")
public String searchUser(@RequestParam String name, @RequestParam int age) {
    return "Name: " + name + ", Age: " + age;
}
```

Request URL:

```text
http://localhost:8080/search?name=bibek&age=21
```

**Example with optional parameter:**

```java
@GetMapping("/users")
public String getUser(@RequestParam(required = false) String username) {
    return "Username is: " + username;
}
```

Here, `username` is optional.

**Example with default value:**

```java
@GetMapping("/users")
public String getUsers(@RequestParam(defaultValue = "1") int page) {
    return "Page number: " + page;
}
```

Request URL:

```text
http://localhost:8080/users
```

Output:

```text
Page number: 1
```

**Common mistake:**

Writing `@Requestparam` instead of `@RequestParam`.

Wrong:

```java
@GetMapping("/users")
public String getUser(@Requestparam String username) {
    return "Username is: " + username;
}
```

Java annotations are case-sensitive, so this is wrong.

Correct:

```java
@GetMapping("/users")
public String getUser(@RequestParam String username) {
    return "Username is: " + username;
}
```

**Another common mistake:**

Confusing `@RequestParam` with `@PathVariable`.

For this URL:

```text
http://localhost:8080/users/1
```

Use:

```java
@GetMapping("/users/{id}")
public String getUser(@PathVariable Long id) {
    return "User ID: " + id;
}
```

For this URL:

```text
http://localhost:8080/users?id=1
```

Use:

```java
@GetMapping("/users")
public String getUser(@RequestParam Long id) {
    return "User ID: " + id;
}
```

**Simple meaning:**

```text
@RequestParam means:
Take value from URL query parameter or form parameter.
```

## `@Service`

**Annotation:**  
`@Service`

**Used in:**  
Class level.

**Purpose:**  
Used to mark a class as a service layer class.

The service layer contains business logic of the application.

In Spring Boot, controller should not directly contain heavy logic.  
Controller should receive request, call service, and return response.

**Example:**

```java
package com.project.StudentManagement.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public String getUserMessage() {
        return "User data from service layer";
    }
}
```

Using service inside controller:

```java
package com.project.StudentManagement.controller;

import com.project.StudentManagement.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user-message")
    public String getUserMessage() {
        return userService.getUserMessage();
    }
}
```

**Common mistake:**

Writing `@Services` instead of `@Service`.

Wrong:

```java
@Services
public class UserService {
}
```

Java annotations are case-sensitive and the correct annotation is singular.

Correct:

```java
@Service
public class UserService {
}
```

**Another common mistake:**

Putting business logic directly inside controller.

Not best:

```java
@RestController
public class UserController {

    @GetMapping("/users")
    public String getUsers() {
        // business logic directly inside controller
        return "Users list";
    }
}
```

Better:

```java
@Service
public class UserService {

    public String getUsers() {
        return "Users list";
    }
}
```

```java
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getUsers() {
        return userService.getUsers();
    }
}
```

**Simple meaning:**

```text
@Service means:
This class contains business logic and is managed by Spring.
## `@Repository`

**Annotation:**  
`@Repository`

**Used in:**  
Class level.

**Purpose:**  
Used to mark a class as a repository/data access layer class.

The repository layer is responsible for communicating with the database.

In Spring Boot, when using Spring Data JPA, we usually create an interface that extends `JpaRepository`. In that case, writing `@Repository` is optional because Spring automatically detects it.

**Example:**

```java
package com.project.StudentManagement.repository;

import com.project.StudentManagement.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    boolean existsByUsername(String username);
}
```

Here:

```java
JpaRepository<Users, Long>
```

means:

```text
Users = Entity class
Long = Type of primary key
```

You can now use built-in database methods like:

```java
save()
findById()
findAll()
deleteById()
existsById()
```

**Example usage in service:**

```java
package com.project.StudentManagement.service;

import com.project.StudentManagement.model.Users;
import com.project.StudentManagement.repository.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Users saveUser(Users user) {
        return usersRepository.save(user);
    }
}
```

**Common mistake:**

Writing database logic directly inside controller.

Wrong:

```java
@RestController
public class UserController {

    @PostMapping("/users")
    public String saveUser() {
        // database logic directly here
        return "User saved";
    }
}
```

Better structure:

```text
Controller  →  Service  →  Repository  →  Database
```

**Another common mistake:**

Writing `@Repository` on an entity class.

Wrong:

```java
@Repository
public class Users {
}
```

Correct:

```java
@Entity
public class Users {
}
```

Repository should be separate:

```java
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
}
```

**Simple meaning:**

```text
@Repository means:
This class/interface is responsible for database operations.
```
## `@Autowired`

**Annotation:**  
`@Autowired`

**Used in:**  
Field level, constructor level, or method level.

**Purpose:**  
Used to automatically inject a Spring-managed object into another class.

It helps one class use another class without manually creating an object using `new`.

**Example:**

```java
package com.project.StudentManagement.controller;

import com.project.StudentManagement.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getUsers() {
        return userService.getUsers();
    }
}
```

Here, Spring automatically provides the `UserService` object to `UserController`.

**Example with `@Autowired`:**

```java
package com.project.StudentManagement.controller;

import com.project.StudentManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
}
```

This works, but constructor injection is usually better.

**Better way:**

```java
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
}
```

In modern Spring Boot, if there is only one constructor, `@Autowired` is optional.

```java
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
}
```

**Common mistake:**

Creating object manually using `new`.

Wrong:

```java
@RestController
public class UserController {

    UserService userService = new UserService();
}
```

This object is not properly managed by Spring.

Correct:

```java
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
}
```

**Another common mistake:**

Trying to inject a class that is not managed by Spring.

Wrong:

```java
public class UserService {
}
```

Correct:

```java
@Service
public class UserService {
}
```

**Simple meaning:**

```text
@Autowired means:
Spring, please provide the needed object automatically.
```
