#  Repository Methods

---

## JpaRepository\<Entity, ID\>

**Used in:**  
Your repository interface declaration

**Purpose:**  
Gives your repository all basic CRUD methods for free — no need to write SQL

**Syntax:**
```java
public interface UsersRepository extends JpaRepository<Users, Long> { }
```

**Example:**
```java
// UsersRepository.java
@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    // JPA gives you save(), findAll(), findById(), delete(), etc. automatically
}
```

Return type: `—` (it's an interface, not a method)  
Common mistake: Forgetting to put the correct ID type — if your `Users` entity uses `Long id`, write `JpaRepository<Users, Long>`, not `JpaRepository<Users, Integer>`

---

## save()

**Used in:**  
Service layer, when creating or updating a user

**Purpose:**  
Saves a new record OR updates an existing one (if the ID already exists)

**Syntax:**
```java
repository.save(entity);
```

**Example:**
```java
// UsersService.java
public Users createUser(Users user) {
    return usersRepository.save(user);   // inserts new row in DB
}

public Users updateUser(Users user) {
    return usersRepository.save(user);   // updates row if user.id already exists
}
```

Return type: `Users` (the saved entity, with generated ID filled in)  
Common mistake: Not using the returned object — `save()` returns the saved entity with the auto-generated ID; ignoring it means you lose the ID

---

## findAll()

**Used in:**  
Service layer, when you need to list all users

**Purpose:**  
Fetches every row from the users table

**Syntax:**
```java
repository.findAll();
```

**Example:**
```java
// UsersService.java
public List<Users> getAllUsers() {
    return usersRepository.findAll();
}
```

Return type: `List<Users>`  
Common mistake: Using this on a large table without pagination — it loads everything into memory at once; use `findAll(Pageable pageable)` for large datasets

---

## findById()

**Used in:**  
Service layer, when fetching one specific user by their ID

**Purpose:**  
Looks up a single row by primary key

**Syntax:**
```java
repository.findById(id);
```

**Example:**
```java
// UsersService.java
public Users getUserById(Long id) {
    return usersRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
}
```

Return type: `Optional<Users>` — you must unwrap it (see `Optional<T>` section below)  
Common mistake: Calling `.get()` directly without checking — throws `NoSuchElementException` if the ID doesn't exist

---

## existsById()

**Used in:**  
Service layer, when you want to check if a user exists before doing something

**Purpose:**  
Returns `true` if a record with that ID exists, `false` otherwise

**Syntax:**
```java
repository.existsById(id);
```

**Example:**
```java
// UsersService.java
public void deleteUser(Long id) {
    if (!usersRepository.existsById(id)) {
        throw new RuntimeException("Cannot delete — user not found: " + id);
    }
    usersRepository.deleteById(id);
}
```

Return type: `boolean`  
Common mistake: Using `findById()` just to check existence — `existsById()` is cheaper because it doesn't load the full entity

---

## deleteById()

**Used in:**  
Service layer, when removing a user by ID

**Purpose:**  
Deletes the row with the given primary key

**Syntax:**
```java
repository.deleteById(id);
```

**Example:**
```java
// UsersService.java
public void deleteUser(Long id) {
    usersRepository.deleteById(id);
}
```

Return type: `void`  
Common mistake: Not handling the case where the ID doesn't exist — `deleteById()` throws `EmptyResultDataAccessException` if the ID is not found; always guard with `existsById()` first

---

## count()

**Used in:**  
Service layer or admin features, when you need the total number of users

**Purpose:**  
Returns the total number of rows in the table

**Syntax:**
```java
repository.count();
```

**Example:**
```java
// UsersService.java
public long getTotalUsers() {
    return usersRepository.count();
}
```

Return type: `long`  
Common mistake: Storing the result in an `int` — `count()` returns `long`; use `long` to avoid overflow on large tables

---

## Derived Query Methods

**Used in:**  
`UsersRepository` interface, when you need to search by a field other than ID

**Purpose:**  
Spring reads your method name and auto-generates the SQL — no `@Query` needed

**Syntax:**
```java
ReturnType findBy<FieldName>(FieldType value);
ReturnType existsBy<FieldName>(FieldType value);
```

**Example:**
```java
// UsersRepository.java
public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
    boolean existsByUsername(String username);
    List<Users> findByEmail(String email);
}
```

Return type: Depends on what you write — `Optional<Users>`, `List<Users>`, `boolean`, etc.  
Common mistake: Misspelling the field name in the method name — Spring will throw an error at startup if `username` in the method name doesn't match the actual field in your `Users` entity

---

## existsByUsername()

**Used in:**  
Service layer, typically during registration to check if a username is already taken

**Purpose:**  
Checks if any user has that username — returns `true` or `false`

**Syntax:**
```java
repository.existsByUsername(username);
```

**Example:**
```java
// UsersRepository.java
boolean existsByUsername(String username);

// UsersService.java
public Users registerUser(Users user) {
    if (usersRepository.existsByUsername(user.getUsername())) {
        throw new RuntimeException("Username already taken: " + user.getUsername());
    }
    return usersRepository.save(user);
}
```

Return type: `boolean`  
Common mistake: Using `findByUsername()` and checking `!= null` instead — `existsByUsername()` is cleaner and more efficient for a simple yes/no check

---

## findByUsername()

**Used in:**  
Service layer, during login or profile lookup

**Purpose:**  
Fetches the user record that matches the given username

**Syntax:**
```java
repository.findByUsername(username);
```

**Example:**
```java
// UsersRepository.java
Optional<Users> findByUsername(String username);

// UsersService.java
public Users getUserByUsername(String username) {
    return usersRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found: " + username));
}
```

Return type: `Optional<Users>` (recommended) or `Users` (risky — returns `null` if not found)  
Common mistake: Declaring the return type as `Users` instead of `Optional<Users>` — then you have to null-check manually and it's easy to forget

---

## Optional\<T\>

**Used in:**  
Anywhere a method might return "nothing" — `findById()`, `findByUsername()`, etc.

**Purpose:**  
A wrapper that forces you to handle the "not found" case instead of getting a surprise `NullPointerException`

**Syntax:**
```java
Optional<Users> result = repository.findById(id);

result.isPresent();               // true if found
result.get();                     // unwrap (risky if empty)
result.orElse(defaultValue);      // return fallback if empty
result.orElseThrow(() -> ...);    // throw exception if empty (most common in APIs)
```

**Example:**
```java
// UsersService.java

// Option 1 — throw a meaningful error
public Users getUser(Long id) {
    return usersRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found: " + id));
}

// Option 2 — return a fallback
public Users getUserOrDefault(Long id, Users defaultUser) {
    return usersRepository.findById(id).orElse(defaultUser);
}

// Option 3 — check before using
public void printUser(Long id) {
    Optional<Users> result = usersRepository.findById(id);
    if (result.isPresent()) {
        System.out.println(result.get().getUsername());
    }
}
```

Return type: `Optional<T>` wraps whatever type `T` is (e.g. `Optional<Users>`)  
Common mistake: Calling `.get()` without checking — if the `Optional` is empty, `.get()` throws `NoSuchElementException`; always use `.orElseThrow()` or `.orElse()`

---

## Common Repository Mistakes

| # | Mistake | Fix |
|---|---------|-----|
| 1 | Using wrong ID type in `JpaRepository<Users, Integer>` when entity uses `Long` | Match the exact ID field type from your entity |
| 2 | Ignoring the return value of `save()` | Always use `Users saved = repo.save(user)` to get the ID-populated object |
| 3 | Calling `.get()` on `Optional` without checking | Use `.orElseThrow()` or `.orElse()` |
| 4 | Using `findAll()` on huge tables | Use `findAll(Pageable pageable)` for pagination |
| 5 | Calling `deleteById()` without checking existence | Guard with `existsById()` first to avoid exceptions |
| 6 | Typo in derived query method name | Spring reads the method name — `findByUsernmae` will crash at startup |
| 7 | Storing `count()` result in `int` | Use `long` — `count()` returns `long` |

---

> 📌 **Quick Reminder:** You don't write any SQL for any of the methods above.  
> Spring Data JPA generates all queries automatically based on method names and `JpaRepository`.