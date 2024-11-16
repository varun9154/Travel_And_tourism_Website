package travel.management.system;

import java.sql.*;

public class Conn {
    public Connection c;
    public Statement s;

    public Conn() {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection to the database
            c = DriverManager.getConnection("jdbc:mysql://localhost:3308/travelmangementsystem", "root", "Varun@24");

            // Create Statement object
            s = c.createStatement();

            System.out.println("Database connected successfully!");
        } catch (Exception e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }
    }

    // This method returns the connection
    public Connection getConnection() {
        return c;
    }
}
