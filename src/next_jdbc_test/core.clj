(ns next-jdbc-test.core
  (:require [next.jdbc :as jdbc]))

;; (def jdbcUrl "jdbc:mysql://myusername:mypassword@localhost:3306/next_jdbc_test")

;; Use a jdbcUrl with additional options
;; (def db-spec {:jdbcUrl jdbcUrl
;;               :serverTimezone "UTC"})

;; Use a db-spec hash map
(def db-spec {:dbtype "mysql"
              :dbname "next_jdbc_test"
              :user "my username"
              :password "my password"
              :port 3306
              :serverTimezone "UTC"})

(def datasource (jdbc/get-datasource db-spec))

(comment
 datasource
 ;; create address table
 (jdbc/execute!
  datasource ["
create table address (
  id int auto_increment primary key,
  name varchar(32),
  email varchar(255)
)"])
 ;; insert address into address table
 (jdbc/execute! datasource ["
insert into address(name,email)
  values('My Name','my@name.com')"])
 ;; select all addresses from address table
 (jdbc/execute! datasource ["select * from address"]))
