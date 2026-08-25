
## What is Bean Scope?

**Bean Scope** determines:

* How many **instances (objects)** Spring creates for a bean.
* How long those instances **live inside the Spring container**.

The two basic scopes to understand first are:

1. **Singleton**
2. **Prototype**

---

# 1. Singleton Scope

**Singleton is the default bean scope in Spring.**

Spring creates **only one instance of a bean** inside a Spring container, and that same instance is reused wherever the bean is needed.

### Key points

* Default scope in Spring.
* One bean → **one object/instance**.
* The same object is shared wherever the bean is injected.
* By default, the singleton bean is created when the application context starts.
* Calling `getBean()` again returns the **same object**.

### Simple idea

```text
One Bean
    ↓
One Object
    ↓
Shared Everywhere
```

### Example

```java
@Component
public class Student {
}
```

By default, `Student` has singleton scope.

```java
Student s1 = context.getBean(Student.class);
Student s2 = context.getBean(Student.class);

System.out.println(s1 == s2);
```

Output:

```text
true
```

Why?

Because `s1` and `s2` refer to the **same object**.

```text
Spring Container
      |
      v
  Student Object
      |
   +--+--+
   |     |
  s1    s2
```

### Why does `getBean()` return the same object by default?

Because the default scope is **singleton**.

Spring creates the bean instance once and keeps it inside the container. When `getBean()` is called again, Spring returns the **existing instance** instead of creating a new one.

---

# 2. Prototype Scope

**Prototype scope creates a new object whenever the bean is requested.**

Unlike singleton, prototype objects are **not shared**.

### Key points

* Not the default scope.
* One bean → **multiple objects/instances**.
* A new object is created whenever the bean is requested.
* The object is not reused by the container like a singleton.
* Prototype objects are not created simply because the application starts; they are created when the bean is requested.

### Simple idea

```text
One Bean
    ↓
Multiple Objects
    ↓
New Object for Each Request
```

### Example

```java
@Component
@Scope("prototype")
public class Student {
}
```

Now:

```java
Student s1 = context.getBean(Student.class);
Student s2 = context.getBean(Student.class);

System.out.println(s1 == s2);
```

Output:

```text
false
```

Why?

Because Spring creates a **new `Student` object** for each `getBean()` request.

```text
Spring Container
      |
      +----> Student Object 1 ----> s1
      |
      +----> Student Object 2 ----> s2
```

---

# Singleton vs Prototype

| Feature                          | Singleton                   | Prototype      |
| -------------------------------- | --------------------------- | -------------- |
| Default?                         | Yes                         | No             |
| Objects per bean                 | One                         | Multiple       |
| Object shared?                   | Yes                         | No             |
| New object on every `getBean()`? | No                          | Yes            |
| Default creation                 | Application context startup | When requested |
| `s1 == s2`                       | `true`                      | `false`        |

---

# Important Experiment

## Singleton

```java
Student s1 = context.getBean(Student.class);
Student s2 = context.getBean(Student.class);

System.out.println(s1 == s2);
```

Output:

```text
true
```

## Prototype

```java
Student s1 = context.getBean(Student.class);
Student s2 = context.getBean(Student.class);

System.out.println(s1 == s2);
```

Output:

```text
false
```

The only difference is the bean scope.

---

# `@Scope("prototype")`

The `@Scope` annotation is used to specify the scope of a Spring bean.

```java
@Scope("prototype")
```

Here, `"prototype"` is a **String value** that tells Spring which scope to use.

The quotation marks are used because the annotation expects a string value.

