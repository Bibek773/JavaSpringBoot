# `@Autowired`

## Context

`@Autowired` is used by Spring for **Dependency Injection**.

It tells Spring to find a suitable bean and provide it to another bean that needs it.

Instead of:

```java
ProductService service = new ProductService();
```

Spring creates and provides the `ProductService`.

---

## Constructor Injection

Dependency is injected through the constructor.

```java
@Component
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
}
```

With only one constructor, `@Autowired` is optional in modern Spring.

```java
@Autowired
public ProductController(ProductService productService) {
    this.productService = productService;
}
```

Both work when there is only one constructor.

**Preferred approach:** Constructor Injection.

---

## Setter Injection

Dependency is injected through a setter method.

```java
@Component
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
```

Useful when a dependency is optional or can be changed later.

---

## Field Injection

Dependency is injected directly into the field.

```java
@Component
public class ProductController {

    @Autowired
    private ProductService productService;
}
```

Short and simple, but generally avoid using it.

Problems:

* Dependency is hidden.
* Harder to unit test.
* Cannot use `final`.
* Class dependencies are less clear.

---

## Important

`@Autowired` does not create a normal Java object.

Spring must have a bean to inject.

```java
@Component
public class ProductService {
}
```

Then Spring can inject it:

```java
@Autowired
private ProductService productService;
```

---

## Quick Comparison

| Type        | Injection point | Recommended       |
| ----------- | --------------- | ----------------- |
| Constructor | Constructor     | ✅ Yes             |
| Setter      | Setter method   | Sometimes         |
| Field       | Field           | ❌ Generally avoid |

---

## Key Idea

```text
Class A needs Class B
        ↓
Spring manages Class B
        ↓
Spring injects B into A
```

`@Autowired` is one way Spring knows where to inject the dependency.

---
