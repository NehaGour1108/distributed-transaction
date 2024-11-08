package org.example;
import java.sql.*;
import java.lang.Exception;

import java.sql.*;

public class DistributedTransactionExample {

    public static void main(String[] args) {
        // JDBC connection URLs for two H2 databases running on different ports
        String dbUrl1 = "jdbc:h2:tcp://localhost:9091/~/insta1";
        String dbUrl2 = "jdbc:h2:tcp://localhost:9092/~/insta2";
        String username = "sa";
        String password = "";

        Connection conn1 = null;
        Connection conn2 = null;

        try {
            // Establish connections to both databases
            conn1 = DriverManager.getConnection(dbUrl1, username, password);
            conn2 = DriverManager.getConnection(dbUrl2, username, password);

            // Start transactions for both connections
            conn1.setAutoCommit(false); // Begin transaction on the first database
            conn2.setAutoCommit(false); // Begin transaction on the second database

            // Insert data into tables (simulating a distributed transaction)
            String insertSQL = "INSERT INTO posts (id, content) VALUES (?, ?)";

            try (PreparedStatement ps1 = conn1.prepareStatement(insertSQL);
                 PreparedStatement ps2 = conn2.prepareStatement(insertSQL)) {

                ps1.setInt(1, 1);
                ps1.setString(2, "Hello from insta1!");
                ps1.executeUpdate();

                ps2.setInt(1, 1);
                ps2.setString(2, "Hello from insta2!");
                ps2.executeUpdate();
            }

            // Commit both transactions if both operations succeed
            conn1.commit();
            conn2.commit();
            System.out.println("Transaction committed successfully!");

        } catch (SQLException e) {
            System.err.println("Error during transaction: " + e.getMessage());
            // Rollback both transactions in case of failure
            try {
                if (conn1 != null) conn1.rollback();
                if (conn2 != null) conn2.rollback();
            } catch (SQLException ex) {
                System.err.println("Error during rollback: " + ex.getMessage());
            }
        } finally {
            // Close the connections
            try {
                if (conn1 != null) conn1.close();
                if (conn2 != null) conn2.close();
            } catch (SQLException ex) {
                System.err.println("Error during closing connections: " + ex.getMessage());
            }
        }
    }
}
