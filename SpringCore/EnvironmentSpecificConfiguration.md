# Spring Profiles

### Context

Understand **environment-specific configuration**.

Different environments may need different beans or configurations:

```text
Development
Testing
Production
```

Spring Profiles allow us to activate specific beans depending on the environment.

### `@Profile`

Use:

```java
@Profile("dev")
```

for development-specific beans.

Use:

```java
@Profile("prod")
```

for production-specific beans.

Example:

```text
Development
    ↓
@Profile("dev")
    ↓
Development Bean
```

```text
Production
    ↓
@Profile("prod")
    ↓
Production Bean
```

Only beans matching the **active profile** are created by Spring.

### Why use Profiles?

Different environments may need different:

```text
Database
API configuration
Logging
Service implementations
Application settings
```

For example:

```text
dev  → local database
prod → production database
```

This allows us to change the environment without changing the application code.

### Activating Profiles

A profile must be **active** for Spring to load its beans.

Using `application.properties`:

```properties
spring.profiles.active=dev
```

Now Spring activates the `dev` profile.

To activate production:

```properties
spring.profiles.active=prod
```

You can also activate a profile when running the application:

```text
--spring.profiles.active=dev
```

or:

```text
--spring.profiles.active=prod
```

### Key Points

* `@Profile` controls when a bean is active.
* `@Profile("dev")` → active for development.
* `@Profile("prod")` → active for production.
* Only beans matching the active profile are loaded.
