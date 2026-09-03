# Multiple Implementations

## Context

Sometimes multiple classes implement the same interface.

In this project:

```text
PaymentService
      ↑
 ┌────┴──────────────┐
CashPaymentService   BankPaymentService
```

Both implementations are Spring beans because they use `@Component`.

When `PaymentController` requests:

```java
PaymentService
```

Spring finds two possible beans:

```text
cashPaymentService
bankPaymentService
```

So Spring cannot decide which one to inject.

This causes an **ambiguity error**.

---

## `@Primary`

`@Primary` tells Spring which implementation should be the **default**.

In this project, adding:

```java
@Primary
```

to `CashPaymentService` makes it the default implementation.

```text
PaymentService
      ↓
CashPaymentService
```

when no specific implementation is requested.

---

## `@Qualifier`

`@Qualifier` tells Spring which specific bean should be injected.

In this project, the controller uses:

```java
@Qualifier("bankPaymentService")
```

So Spring selects:

```text
PaymentService
      ↓
BankPaymentService
```

instead of `CashPaymentService`.

The default bean name comes from the class name:

```text
BankPaymentService
        ↓
bankPaymentService
```

---

## `@Primary` vs `@Qualifier`

### `@Primary`

Used when one implementation should be the **default**.

```text
PaymentService
      ↓
CashPaymentService
```

### `@Qualifier`

Used when a **specific implementation** should be selected.

```text
PaymentService
      ↓
BankPaymentService
```

---

## What I Tested

### Without `@Primary` or `@Qualifier`

```text
CashPaymentService
BankPaymentService
        ↓
PaymentService
        ↓
Ambiguity error
```

### With `@Primary`

```text
@Primary
CashPaymentService
        ↓
PaymentService
        ↓
CashPaymentService
```

Output:

```text
Paid by cash
```

### With `@Qualifier`

```text
@Qualifier("bankPaymentService")
        ↓
PaymentService
        ↓
BankPaymentService
```

Output:

```text
Paid by bank
```

---

## Key Points

* Multiple implementations of the same interface can create ambiguity.
* `@Primary` selects the default implementation.
* `@Qualifier` selects a specific implementation.
* Both implementations can still be Spring beans.
* `@Qualifier` is more explicit than `@Primary`.

---

## Key Idea

```text
One interface
     ↓
Multiple implementations
     ↓
Multiple Spring beans
     ↓
Spring cannot decide
     ↓
@Primary OR @Qualifier
```

---
