# Introduction
The JDBC portion of the core Java project is meant as practice for using Java Database 
connectivity. It revolves around a psql database that contains various tables and the
Java code is used to CRUD that data. The code is all written in Java, docker is used to provision
a psql instance, and git was used for version control. 

# Implementation
The program uses the class DatabaseConnectionManager to connect to the psql instance. 
The CustomerDAO class is used for CRUD operations on the Customer table. And the JDBCExecutor 
contains the main class that can be used for various SQL operations. 
## ER Diagram
![alt text](Assets/img.png)

## Design Patterns
The project uses a couple well know jdbc patterns. The first of which is the DAO
or Data Access Object pattern. This pattern involves using an abstract api to simplify
CRUD operations on specific tables in a database. The other pattern used is the repository 
pattern. 

# Test
How you test y test data set up, query result)