#Bill management system of Telangana
Prerequisites:
##trigger

server configurations:
Java 21 and Maven
Mysql database server
CREATE DATABASE cv01;

CREATE TABLE bill_manage (
    id INT AUTO_INCREMENT PRIMARY KEY,
    bill_number INT NOT NULL,
    units INT NOT NULL,
    amount FLOAT NOT NULL,
    generated_at TIMESTAMP NOT NULL
);

Add database endpoint in src/main/java/com/cloud247/Demo.java

Package :
mvn package

Deployment:
Tomcat
