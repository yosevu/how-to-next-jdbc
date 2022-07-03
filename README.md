# next-jdbc-test

## Objective

Connect to a MySQL database with [next.jdbc](https://cljdoc.org/d/com.github.seancorfield/next.jdbc/1.2.780/doc/readme).

## Steps

1. Log in to mysql CLI.

  - `mysql -u root -p`
  - Enter root password.
2. Create database.

  - `CREATE DATABASE next_jdbc_test;`
3. Create [next-jdbc-test repository](https://github.com/yosevu/next-jdbc-test) repository.

- Add `next-jdbc` dependency.
- Create `db-spec` and `datasource`.
See [Getting Started with next.jdbc](https://cljdoc.org/d/com.github.seancorfield/next.jdbc/1.2.780/doc/getting-started) and [get-datasource](https://cljdoc.org/d/com.github.seancorfield/next.jdbc/1.2.780/api/next.jdbc#get-datasource).
4. See [next-jdbc-test.core](https://github.com/yosevu/next-jdbc-test/blob/main/src/next_jdbc_test/core.clj) for usage examples.


## Issues

### Execution error: No suitable driver found for jdbc:mysql:next-jdbc-test
```
Execution error (SQLException) at java.sql.DriverManager/getConnection (DriverManager.java:706).
No suitable driver found for jdbc:mysql://127.0.0.1:3306/next_jdbc_test
```
### Resolution 
  - Add mysql driver to dependencies
`mysql/mysql-connector-java {:mvn/version "8.0.19"}`
  - Ensure `db-spec` hash map is correct
```clojure
(def db-spec {:dbtype "mysql"
              :dbname "next_jdbc_test"
              :user "my username"
              :password "my password"
              :port 3306})
```

### Execution error: (InvalidConnectionAttributeException) 
```
Execution error (InvalidConnectionAttributeException) at jdk.internal.reflect.NativeConstructorAccessorImpl/newInstance0 (NativeConstructorAccessorImpl.java:-2).
The server time zone value 'KST' is unrecognized or represents more than one time zone. You must configure either the server or JDBC driver (via the 'serverTimezone' configuration property) to use a more specifc time zone value if you want to utilize time zone support.
```

### Resolution
  - Add `:serverTimezone "UTC"` to `db-spec`
  ```clojure
  (def db-spec {:dbtype "mysql"
                :dbname "next_jdbc_test"
                :user "my username"
                :password "my password"
                :port 3306
                :serverTimezone "UTC"})

```
