To implement distributed transactions using Java, Maven, and H2 database, you'll need to simulate a distributed transaction setup using multiple databases and manage the transactions across them. The setup would involve:

1. **H2 Database Setup:** You'll use two or more H2 databases to simulate the concept of a distributed system.
2. **Transaction Management:** You’ll manually manage transactions across these multiple databases using Java's JDBC.
3. **Maven Project Setup:** We'll configure Maven for dependency management.
4. **Transaction Logic:** Use Java code to manage transactions, including committing and rolling back across different databases.

### Project Structure

```
distributed-transaction/
├── pom.xml
└── src/
    └── main/
        └── java/
            └── org/
                └── example/
                    └── DistributedTransactionExample.java
```

### 1. **Maven Configuration (pom.xml)**

You need to configure Maven to include dependencies for H2 database, JDBC, and any necessary transaction management libraries.


### 2. **Java Code to Implement Distributed Transactions**

Now, we will create Java code to manage distributed transactions across two H2 databases.

#### **DistributedTransactionExample.java**


### 3. **H2 Database Setup:**

You can use the H2 console or configure the database schema manually. Here is the SQL schema for the tables in both databases.

You can execute this script in the H2 console by connecting to `insta1` on port `9091` and `insta2` on port `9092`.


### 4. **Expected Output:**

If the transaction is successful across both databases, you should see:

```
Transaction committed successfully!
```

In case of an error, such as one of the databases failing to insert data, the transaction will be rolled back, and you will see the error messages like:

```
Error during transaction: <Error Message>
Error during rollback: <Rollback Error Message>
```

### 5. **Notes:**

1. **Distributed Transaction:** This setup simulates a distributed transaction where two separate databases (`insta1` and `insta2`) participate in a transaction. If one fails, the transaction on both databases is rolled back.
   
2. **Commit and Rollback:** This example ensures both databases commit the transaction together, or neither of them does, which is an essential feature of distributed transactions.

3. **Transaction Management:** We manually handle the transaction by setting `setAutoCommit(false)` on both database connections and committing or rolling back the transactions based on success or failure.

This approach demonstrates a basic distributed transaction system using H2, Java, and Maven.
